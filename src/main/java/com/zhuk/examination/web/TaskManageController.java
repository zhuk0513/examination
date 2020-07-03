package com.zhuk.examination.web;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aspose.cells.Cells;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.WorksheetCollection;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhuk.examination.common.utils.ExcelUtil;
import com.zhuk.examination.common.utils.ResultJson;
import com.zhuk.examination.model.entity.TaskManage;
import com.zhuk.examination.service.TaskManageService;
import com.zhuk.examination.common.utils.GkStringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhuk
 * @since 2020-04-11
 */
@RestController
@Slf4j
@RequestMapping("/taskManage")
public class TaskManageController {
    @Resource
    private TaskManageService taskManageService;
    @Resource
    private JdbcTemplate jdbcTemplate;

    /**
     * 表格分页展示
     * @param name
     * @param pageSize
     * @param curPage
     * @return
     */
    @GetMapping("/findPageTaskManage")
    public ResultJson findPageAlarmStrtegy(String name, String type, String status, String createTime, int pageSize, int curPage){
        return taskManageService.findPageTaskManage(name,type,status,createTime,pageSize,curPage);
    }

    /**
     * 保存
     * @param params
     * @return
     */
    @PostMapping("/saveTaskManage")
    public ResultJson saveAlarmStrtegy(String params,String userId){
        return taskManageService.saveTaskManage(params,userId);
    }

    /**
     * 根据id修改
     * @param params
     * @return
     */
    @PutMapping("/updateTaskManageById")
    public ResultJson updateAlarmStrtegyById(String params,String userId){
        return taskManageService.updateTaskManageById(params,userId);
    }

    /**
     * 根绝id查询
     * @param id
     * @return
     */
    @GetMapping("/getOneTaskManage")
    public ResultJson getOneAlarmStrtegy(String id){

        return taskManageService.getOneTaskManage(id);
    }

    /**
     * 根据id批量删除
     * @param ids
     * @param userId
     * @return
     */
    @DeleteMapping("/deleteTaskManage")
    public ResultJson deleteTaskManage(String ids, String userId){
        return taskManageService.deleteTaskManage(ids.split(","),userId);
    }

    /**
     * 数据导入
     * @param request
     */
    @PostMapping("importTaskManage")
    public ResultJson importTaskManage(HttpServletRequest request){
        ResultJson result = new ResultJson();
        MultipartHttpServletRequest multipartRequest=(MultipartHttpServletRequest) request;
        List<MultipartFile> files = multipartRequest.getFiles("files");

        try {
            int fileNum=0;
            List<Object []> dataList=new ArrayList<>();

            //查询表字段信息
            String tableName="task_manage";
            String sql="SELECT COLUMN_NAME,COLUMN_COMMENT FROM information_schema.COLUMNS WHERE TABLE_NAME=?";
            List<Map<String,Object>> tableCols=jdbcTemplate.queryForList(sql,new Object[]{tableName});

            //默认字段处理
            String insertSql="insert into "+tableName+" ( id,create_time,deadline";
            String values="values (?,?,?";

            for(MultipartFile fileItem:files){
                String filename = fileItem.getOriginalFilename();
                String extensionName = filename.substring(filename.lastIndexOf(".") + 1);

                Workbook wb = new Workbook(fileItem.getInputStream());
                WorksheetCollection sheets = wb.getWorksheets();
                //多个
                int sheetNum=0;
                for(Object sheet : sheets) {
                    Worksheet workSheet = (Worksheet)sheet;
                    Cells cells = workSheet.getCells();
                    for (int i = 0; i < cells.getMaxRow() + 1; i++)
                    {
                        List<Object> objs=new ArrayList<>();
                        objs.add(UUID.randomUUID().toString());
                        objs.add(new Date());
                        objs.add(new Date());
                        for (int j = 0; j < cells.getMaxColumn() + 1; j++)
                        {
                            String str = cells.get(i, j).getStringValueWithoutFormat().trim();
                            if(fileNum==0&&sheetNum==0&&i == 0){
                                for(Map<String,Object> map:tableCols){
                                    String colName=map.get("COLUMN_NAME").toString();
                                    String colComment=map.get("COLUMN_COMMENT")==null?"":map.get("COLUMN_COMMENT").toString();
                                    if(str.equals(colComment)){
                                        insertSql+=","+colName;
                                        values+=",?";
                                        break;
                                    }
                                }
                            }else{
                                objs.add(str);
                            }
                        }
                        if(i != 0){
                            dataList.add(objs.toArray());
                        }
                        //每1000条提交1次
                        if(dataList.size()==1000){
                            String sqlStr=insertSql+") "+values+") ";
                            System.out.println(sqlStr);
                            jdbcTemplate.batchUpdate(sqlStr,dataList);
                            dataList.clear();
                        }
                    }
                    sheetNum++;
                }
                fileNum++;
            }

            if(dataList.size()>0){
                String sqlStr=insertSql+") "+values+") ";
                jdbcTemplate.batchUpdate(sqlStr,dataList);
                dataList.clear();
            }
            result.setCode(ResultJson.Code.SUCCESS_CODE.toString());
            result.setMsg(ResultJson.Msg.SUCCESS_MSG.toString());
        } catch (Exception e) {
            result.setCode(ResultJson.Code.FAIL_CODE.toString());
            result.setMsg(ResultJson.Msg.FAIL_MSG.toString());
            log.error("修改任务管理失败" + e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 数据导出
     * @param response
     * @param name
     * @param type
     * @param status
     * @param createTime
     */
    @PostMapping("exportTaskManage")
    public void exportTaskManage(HttpServletResponse response,String name,String type,String status,String createTime){
        String fileName="导出文件.csv";
        JSONArray dataList = new JSONArray();
        //列表数据
        JSONObject singleDataObj = new JSONObject();
        try{
            //查询表字段信息(去掉排除不需要导出的字段)
            String tableName="task_manage";
            String colSql="SELECT COLUMN_NAME,COLUMN_COMMENT FROM information_schema.COLUMNS WHERE TABLE_NAME=? and COLUMN_NAME not in (?,?,?)";
            List<Map<String,Object>> tableCols=jdbcTemplate.queryForList(colSql,new Object[]{tableName,"id","create_time","deadline"});

            String sql1="";
            //获取列表表头
            JSONArray titleArr = new JSONArray();
            for(Map<String,Object> map:tableCols){
                String colName=map.get("COLUMN_NAME").toString();
                String colComment=map.get("COLUMN_COMMENT")==null?"":map.get("COLUMN_COMMENT").toString();
                titleArr.add(colComment);
                sql1+=","+colName;
            }

            //组装数据
            String sql=" select "+sql1.substring(1)+"  from task_manage where 1=1";
            List<Object> objs=new ArrayList<>();
            if (!GkStringUtils.isEmpty(name)) {
                sql+=" and name like ? ";
                objs.add("%"+name+"%");
            }
            if (!GkStringUtils.isEmpty(type)) {
                sql+=" and type = ? ";
            }
            if (!GkStringUtils.isEmpty(status)) {
                sql+=" and status = ? ";
            }
            if (!GkStringUtils.isEmpty(createTime)) {
                String [] time=createTime.split(",");
                String startTime=time[0];
                String endTime=time[1];
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                sql+=" and create_time >= ?  and create_time<= ?";
                objs.add(format.parse(startTime));
                objs.add(format.parse(endTime));
            }

            List<Map<String,Object>> datas=jdbcTemplate.queryForList(sql,objs.toArray());
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
        }catch (IOException | ParseException e){
            e.printStackTrace();
        }
    }

    @PostMapping("localECharts")
    public ResultJson localECharts(){
        ResultJson result = new ResultJson();
        try{
            JSONObject obj=new JSONObject();
            String sql=" select deadline,count(1) sl   from task_manage group by deadline ";
            List<Map<String,Object>> deadlines=jdbcTemplate.queryForList(sql);
            obj.put("deadlines",deadlines);

            String sql1=" select status,count(1) sl  from task_manage group by status ";
            List<Map<String,Object>> statuses=jdbcTemplate.queryForList(sql1);
            obj.put("statuses",statuses);
            result.setCode(ResultJson.Code.SUCCESS_CODE.toString());
            result.setResult(obj);
        } catch (Exception e) {
            result.setCode(ResultJson.Code.FAIL_CODE.toString());
            result.setMsg(ResultJson.Msg.FAIL_MSG.toString());
            log.error("修改任务管理失败" + e.getMessage());
        }
        return result;
    }
}

