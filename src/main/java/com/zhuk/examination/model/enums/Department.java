package com.zhuk.examination.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public enum Department implements BaseEnum{
    DEVELOPMENT("DEVELOPMENT","开发部"),
    TEST("TEST","测试部"),
    AFTER_SALE_SERVICE("AFTER_SALE_SERVICE","售后服务部"),
    SALE("SALE","销售部");


    @EnumValue
    @JsonValue
    private final String code;
    private final String descp;

    Department(String code,String descp) {
        this.code=code;
        this.descp = descp;
    }

    public static void main(String[] args) {
        try {
            String name="Department";
            String[] names = name.split(",");
            Map<String, List<Object>> maps = new HashMap<>();
            for (String nm : names) {
                String enumPackage = "com.zhuk.examination.model.enums";
                Class<Enum> clazz = (Class<Enum>) Class.forName(enumPackage + "." + nm);
                Enum[] objs = clazz.getEnumConstants();
                List<Object> ls = new ArrayList<>();
                for (Enum obj : objs) {
                    ls.add(obj);
                }
                maps.put(nm, ls);
            }
            System.out.println(maps);
        } catch (Exception e) {
           e.printStackTrace();
        }

    }
}
