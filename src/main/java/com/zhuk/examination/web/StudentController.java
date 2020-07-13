package com.zhuk.examination.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhuk.examination.common.utils.ExcelUtil;
import com.zhuk.examination.common.utils.GkStringUtils;
import com.zhuk.examination.common.utils.ResultJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.*;

/**
 * @author zhuk
 * @description: 学生信息控制层
 * @date 2020-07-13
 */
@RestController
@Slf4j
@RequestMapping("/student")
public class StudentController {
    @Resource
    private JdbcTemplate jdbcTemplate;
    @GetMapping("/add")
    public void add(){
        String sql=" insert into student(id,name,sex,grade,class,date,card,address) values(?,?,?,?,?,?,?,?)";
        List<Object[]> list=new ArrayList<>();
        Random rm=new Random();
        Random rm1=new Random();
        for(int i=0;i<100;i++){
            int num=rm.nextInt(10);
            int num1=rm1.nextInt(6)+1;
            String sex=(num%3)>0?"男":"女";
            list.add(new Object[]{UUID.randomUUID().toString(),"王小二"+i,sex,num1,num,"1995年10月","34022219921032123"+num,"王小二家"+i});
        }
        jdbcTemplate.batchUpdate(sql, list);
    }

    @GetMapping("getAll")
    public ResultJson getAll(String name,String sex,String grade){
        ResultJson result = new ResultJson();
        try{
            List<Object> params=new ArrayList<>();
            String sql=" select *  from student where 1=1 ";
            if(!GkStringUtils.isEmpty(name)){
                sql+=" and name like ?";
                params.add("%"+name+"%");
            }
            if(!GkStringUtils.isEmpty(sex)){
                sql+=" and sex = ? ";
                params.add(sex);
            }
            if(!GkStringUtils.isEmpty(grade)){
                sql+=" and grade = ? ";
                params.add(grade);
            }
            List<Map<String,Object>> data=jdbcTemplate.queryForList(sql,params.toArray());

            result.setCode(ResultJson.Code.SUCCESS_CODE.toString());
            result.setResult(data);
        } catch (Exception e) {
            result.setCode(ResultJson.Code.FAIL_CODE.toString());
            result.setMsg(ResultJson.Msg.FAIL_MSG.toString());
            log.error("修改任务管理失败" + e.getMessage());
        }
        return result;
    }

    @GetMapping("localECharts")
    public ResultJson localECharts(){
        ResultJson result = new ResultJson();
        try{
            JSONObject obj=new JSONObject();
            String sql=" select grade,sex,count(1) sl   from student group by grade,sex order by  grade";
            List<Map<String,Object>> sex=jdbcTemplate.queryForList(sql);
            obj.put("sex",sex);

            String sql1=" select grade,count(1) sl  from student group by grade  order by  grade";
            List<Map<String,Object>> grade=jdbcTemplate.queryForList(sql1);
            obj.put("grade",grade);
            result.setCode(ResultJson.Code.SUCCESS_CODE.toString());
            result.setResult(obj);
        } catch (Exception e) {
            result.setCode(ResultJson.Code.FAIL_CODE.toString());
            result.setMsg(ResultJson.Msg.FAIL_MSG.toString());
        }
        return result;
    }

    @PostMapping("/exportData")
    public void exportData(HttpServletResponse response,String name, String sex, String grade){

        String fileName="导出文件.csv";
        JSONArray dataList = new JSONArray();
        //列表数据
        JSONObject singleDataObj = new JSONObject();
        try{
            //查询表字段信息(去掉排除不需要导出的字段)
            String tableName="student";
            String colSql="SELECT COLUMN_NAME,COLUMN_COMMENT FROM information_schema.COLUMNS WHERE TABLE_NAME=? and COLUMN_NAME not in (?)";
            List<Map<String,Object>> tableCols=jdbcTemplate.queryForList(colSql,new Object[]{tableName,"id"});

            String sql1="";
            //获取列表表头
            JSONArray titleArr = new JSONArray();
            for(Map<String,Object> map:tableCols){
                String colName=map.get("COLUMN_NAME").toString();
                String colComment=map.get("COLUMN_COMMENT")==null?"":map.get("COLUMN_COMMENT").toString();
                titleArr.add(colComment);
                sql1+=","+colName;
            }

        List<Object> params=new ArrayList<>();
        String sql=" select "+sql1.substring(1)+"  from student where 1=1 ";
        if(!GkStringUtils.isEmpty(name)){
            sql+=" and name like ?";
            params.add("%"+name+"%");
        }
        if(!GkStringUtils.isEmpty(sex)){
            sql+=" and sex = ? ";
            params.add(sex);
        }
        if(!GkStringUtils.isEmpty(grade)){
            sql+=" and grade = ? ";
            params.add(grade);
        }
        List<Map<String,Object>> datas=jdbcTemplate.queryForList(sql,params.toArray());

        JSONArray dataArr=new JSONArray();
        for(Map<String,Object> map:datas){
            JSONArray jsonMap=new JSONArray();
            for(String key:map.keySet()){
                //这里可以根据需要处理字段值
                jsonMap.add(map.get(key));
            }
            dataArr.add(jsonMap);
        }
        singleDataObj.put("title", titleArr);
        singleDataObj.put("dataList", dataArr);

        dataList.add(singleDataObj);
        JSONArray jArr=(JSONArray) JSONArray.parse(dataList.toString());
        JSONObject outData=new JSONObject();
        outData.put("dataList", jArr);
        response.setContentType("application/csv; charset=utf-8");
        response.setHeader(
                "Content-Disposition",
                "attachment; filename="
                        + new String(fileName.getBytes("utf-8"),
                        "iso-8859-1"));

        OutputStream outputStream = response.getOutputStream();
        ExcelUtil.exportExcel(outData.toString(), outputStream);
        outputStream.flush();
        outputStream.close();
    }catch (IOException e){
        e.printStackTrace();
    }
    }
}
