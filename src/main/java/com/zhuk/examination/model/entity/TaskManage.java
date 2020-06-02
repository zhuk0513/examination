package com.zhuk.examination.model.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zhuk.examination.model.enums.Department;
import com.zhuk.examination.model.enums.TaskStatus;
import com.zhuk.examination.model.enums.TaskType;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author zhuk
 * @since 2020-04-11
 */
@Data
@TableName("task_manage")
public class TaskManage implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;
    /**
     * 任务名称
     */
    private String name;
    /**
     * 内容
     */
    private String content;
    /**
     * 任务类型 1 项目任务 2个人任务
     */
    private TaskType type;
    /**
     * 截止日期
     */
    private Date deadline;
    /**
     * 任务状态
     */
    private TaskStatus status;
    /**
     * 执行部门
     */
    private String department;
    /**
     * 发布时间
     */
    @TableField("create_time")
    private Date createTime;

}
