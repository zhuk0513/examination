package com.zhuk.examination.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhuk.examination.common.utils.ResultJson;
import com.zhuk.examination.model.entity.TaskManage;
import com.zhuk.examination.dao.TaskManageMapper;
import com.zhuk.examination.service.TaskManageService;
import com.zhuk.examination.common.utils.GkStringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhuk
 * @since 2020-04-11
 */
@Service
public class TaskManageServiceImpl extends ServiceImpl<TaskManageMapper, TaskManage> implements TaskManageService {

    @Resource
    private TaskManageMapper taskManageMapper;

    @Override
    public ResultJson findPageTaskManage(String name,String type,String status,String createTime, int pageSize, int curPage) {
        ResultJson result=new ResultJson();
        try {

            QueryWrapper<TaskManage> wrapper = new QueryWrapper();

            if (!GkStringUtils.isEmpty(name)) {
                wrapper.like("name", "%" + name + "%");
            }
            if (!GkStringUtils.isEmpty(type)) {
                wrapper.eq("type", type);
            }
            if (!GkStringUtils.isEmpty(status)) {
                wrapper.eq("status",  status );
            }
            if (!GkStringUtils.isEmpty(createTime)) {
                String [] time=createTime.split(",");
                String startTime=time[0];
                String endTime=time[1];
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                wrapper.between("create_time",format.parse(startTime),format.parse(endTime));
            }

            wrapper.orderByDesc("create_time");
            IPage<TaskManage> pages = taskManageMapper.selectPage(new Page<>(curPage, pageSize), wrapper);

            result.setCode(ResultJson.Code.SUCCESS_CODE.toString());
            result.setResult(pages.getRecords());
            result.setTotalSize(pages.getTotal());
        } catch (Exception e) {
            result.setCode(ResultJson.Code.FAIL_CODE.toString());
            result.setMsg(ResultJson.Msg.FAIL_MSG.toString());
        }

        return result;
    }

    @Override
    public ResultJson saveTaskManage(String params, String userId) {
        ResultJson result=new ResultJson();
        try{
            TaskManage taskManage =JSONObject.parseObject(params, TaskManage.class);
            taskManage.setCreateTime(new Date());
            taskManageMapper.insert(taskManage);

            result.setCode(ResultJson.Code.SUCCESS_CODE.toString());
            result.setMsg(ResultJson.Msg.SUCCESS_MSG.toString());
        } catch (Exception e) {
            result.setCode(ResultJson.Code.FAIL_CODE.toString());
            result.setMsg(ResultJson.Msg.FAIL_MSG.toString());
            log.error("新建任务管理失败" + e.getMessage());
        }
        return result;
    }

    @Override
    public ResultJson updateTaskManageById(String params, String userId) {
        ResultJson result = new ResultJson();
        try{
            TaskManage taskManage =JSONObject.parseObject(params, TaskManage.class);
            taskManageMapper.updateById(taskManage);

            result.setCode(ResultJson.Code.SUCCESS_CODE.toString());
            result.setMsg(ResultJson.Msg.SUCCESS_MSG.toString());
        } catch (Exception e) {
            result.setCode(ResultJson.Code.FAIL_CODE.toString());
            result.setMsg(ResultJson.Msg.FAIL_MSG.toString());
            log.error("修改任务管理失败" + e.getMessage());
        }
        return result;
    }

    @Override
    public ResultJson getOneTaskManage(String id) {
        ResultJson result = new ResultJson();
        try{
            result.setCode(ResultJson.Code.SUCCESS_CODE.toString());
            result.setResult(taskManageMapper.selectById(id));
        } catch (Exception e) {
            result.setCode(ResultJson.Code.FAIL_CODE.toString());
            result.setMsg(ResultJson.Msg.FAIL_MSG.toString());
            log.error("修改任务管理失败" + e.getMessage());
        }
        return result;
    }

    @Override
    public ResultJson deleteTaskManage(String[] ids, String userId) {
        ResultJson result = new ResultJson();
        try{
            taskManageMapper.deleteBatchIds(Arrays.asList(ids));
            result.setCode(ResultJson.Code.SUCCESS_CODE.toString());
            result.setMsg(ResultJson.Msg.SUCCESS_MSG.toString());
        } catch (Exception e) {
            result.setCode(ResultJson.Code.FAIL_CODE.toString());
            result.setMsg(ResultJson.Msg.FAIL_MSG.toString());
            log.error("修改任务管理失败" + e.getMessage());
        }
        return result;

    }
}
