package com.zhuk.examination.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum TaskType {
    PROJECT("PROJECT","项目任务"),
    PERSONAL("PERSONAL","个人项目");

    @EnumValue
    @JsonValue
    private final String code;
    private final String descp;

    TaskType(String code,String descp) {
        this.code=code;
        this.descp = descp;
    }
}
