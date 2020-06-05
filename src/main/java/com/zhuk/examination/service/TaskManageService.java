package com.zhuk.examination.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhuk.examination.common.utils.ResultJson;
import com.zhuk.examination.model.entity.TaskManage;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhuk
 * @since 2020-04-11
 */
public interface TaskManageService extends IService<TaskManage> {
    /**
     * 表格分页展示
     * @param name
     * @param pageSize
     * @param curPage
     * @return
     */
    ResultJson findPageTaskManage(String name, String type, String status, String createTime, int pageSize, int curPage);

    /**
     * 批量保存
     * @param params
     * @return
     */
    ResultJson saveTaskManage(String params,String userId);

    /**
     * 根据id修改
     * @param params
     * @return
     */
    ResultJson updateTaskManageById(String params,String userId);

    /**
     * 根绝id查询
     * @param id
     * @return
     */
    ResultJson getOneTaskManage(String id);

    /**
     * 根据id批量删除
     * @param ids
     * @param userId
     * @return
     */
    ResultJson deleteTaskManage(String[] ids, String userId);


}
