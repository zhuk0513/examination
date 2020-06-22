package com.zhuk.examination.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 朱楷
 */

@Getter
public enum Department implements BaseEnum{
    DEVELOPMENT("DEVELOPMENT","开发部"),
    TEST("TEST","测试部"),
    AFTER_SALE_SERVICE("AFTER_SALE_SERVICE","售后服务部"),
    SALE("SALE","销售部");

    /**
     * 序列化标记响应json值
     */
//    @JsonValue
    /**
     * 标记数据库存的值是code
     */
    @EnumValue
    private final String code;

    private final String desc;

    Department(String code,String desc) {
        this.code=code;
        this.desc = desc;
    }

}
