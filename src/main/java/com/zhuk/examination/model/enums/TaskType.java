package com.zhuk.examination.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * @author zhuk
 */

@Getter
public enum TaskType implements BaseEnum{
    PROJECT("PROJECT","项目任务"),
    PERSONAL("PERSONAL","个人任务");

    @EnumValue
    private final String code;
    private final String desc;

    TaskType(String code,String desc) {
        this.code=code;
        this.desc = desc;
    }
}
