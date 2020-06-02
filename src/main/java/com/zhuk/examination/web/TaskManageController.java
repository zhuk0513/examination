package com.zhuk.examination.web;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhuk.examination.model.entity.TaskManage;
import com.zhuk.examination.service.TaskManageService;
import com.zhuk.examination.common.utils.GkStringUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhuk
 * @since 2020-04-11
 */
@RestController
@RequestMapping("/taskManage")
public class TaskManageController {
    @Resource
    private TaskManageService taskManageService;

    /**
     * 表格分页展示
     * @param name
     * @param pageSize
     * @param curPage
     * @return
     */
    @GetMapping("/findPageTaskManage")
    public JSONObject findPageAlarmStrtegy(String name,String type,String status,String createTime, int pageSize, int curPage){
        return taskManageService.findPageTaskManage(name,type,status,createTime,pageSize,curPage);
    }

    /**
     * 保存
     * @param params
     * @return
     */
    @PostMapping("/saveTaskManage")
    public JSONObject saveAlarmStrtegy(String params,String userId){
        return taskManageService.saveTaskManage(params,userId);
    }

    /**
     * 根据id修改
     * @param params
     * @return
     */
    @PutMapping("/updateTaskManageById")
    public JSONObject updateAlarmStrtegyById(String params,String userId){
        return taskManageService.updateTaskManageById(params,userId);
    }

    /**
     * 根绝id查询
     * @param id
     * @return
     */
    @GetMapping("/getOneTaskManage")
    public JSONObject getOneAlarmStrtegy(String id){

        return taskManageService.getOneTaskManage(id);
    }

    /**
     * 根据id批量删除
     * @param ids
     * @param userId
     * @return
     */
    @DeleteMapping("/deleteTaskManage")
    public JSONObject deleteTaskManage(String ids, String userId){
        return taskManageService.deleteTaskManage(ids.split(","),userId);
    }


}

