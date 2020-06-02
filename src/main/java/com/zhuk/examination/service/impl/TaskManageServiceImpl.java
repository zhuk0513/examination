package com.zhuk.examination.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
    public JSONObject findPageTaskManage(String name,String type,String status,String createTime, int pageSize, int curPage) {
        JSONObject result = new JSONObject();
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
            IPage<TaskManage> pages = taskManageMapper.selectPage(new Page<>(curPage, curPage), wrapper);

            result.put("code", 1);
            result.put("result", pages.getRecords());
            result.put("totalSize", this.count(wrapper));
        } catch (Exception e) {
            result.put("code", 0);
            result.put("msg", "出错了请联系管理员");
        }

        return result;
    }

    @Override
    public JSONObject saveTaskManage(String params, String userId) {
        JSONObject result = new JSONObject();
        try{
            TaskManage taskManage =JSONObject.parseObject(params, TaskManage.class);
            taskManage.setCreateTime(new Date());
            taskManageMapper.insert(taskManage);

            result.put("code", 1);
            result.put("msg", "保存成功");
        } catch (Exception e) {
            result.put("code", 0);
            result.put("msg", "出错了请联系管理员");
            log.error("新建告警策略失败" + e.getMessage());
        }
        return result;
    }

    @Override
    public JSONObject updateTaskManageById(String params, String userId) {
        JSONObject result = new JSONObject();
        try{
            TaskManage taskManage =JSONObject.parseObject(params, TaskManage.class);
            taskManageMapper.updateById(taskManage);

            result.put("code", 1);
            result.put("msg", "修改成功");
        } catch (Exception e) {
            result.put("code", 0);
            result.put("msg", "出错了请联系管理员");
            log.error("修改告警策略失败" + e.getMessage());
        }
        return result;
    }

    @Override
    public JSONObject getOneTaskManage(String id) {
        JSONObject result = new JSONObject();
        try{
            result.put("code", 1);
            result.put("result", taskManageMapper.selectById(id));
        } catch (Exception e) {
            result.put("code", 0);
            result.put("msg", "出错了请联系管理员");
            log.error("修改告警策略失败" + e.getMessage());
        }
        return result;
    }

    @Override
    public JSONObject deleteTaskManage(String[] ids, String userId) {
        JSONObject result = new JSONObject();
        try{
            taskManageMapper.deleteBatchIds(Arrays.asList(ids));
            result.put("code", 1);
            result.put("msg", "删除成功");
        } catch (Exception e) {
            result.put("code", 0);
            result.put("msg", "出错了请联系管理员");
            log.error("修改告警策略失败" + e.getMessage());
        }
        return result;

    }
}
