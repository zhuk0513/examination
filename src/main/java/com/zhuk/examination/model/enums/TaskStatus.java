package com.zhuk.examination.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * @author zhuk
 */

@Getter
public enum TaskStatus implements BaseEnum{
    COMPLETE("COMPLETE","完成"),
    HANDLING("HANDLING","办理中"),
    NOT_HANDLE("NOT_HANDLE","未办理");

    @EnumValue
    private final String code;
    private final String desc;

    TaskStatus(String code,String desc) {
        this.code=code;
        this.desc = desc;
    }


}
