package com.zhuk.examination.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TaskStatus {
    COMPLETE("COMPLETE","完成"),
    HANDLING("HANDLING","办理中"),
    NOT_HANDLE("NOT_HANDLE","未办理");

    @EnumValue
    @JsonValue
    private final String code;
    private final String descp;

    TaskStatus(String code,String descp) {
        this.code=code;
        this.descp = descp;
    }


}
