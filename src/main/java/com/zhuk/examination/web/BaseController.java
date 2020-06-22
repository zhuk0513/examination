package com.zhuk.examination.web;

import com.alibaba.fastjson.JSONObject;
import com.zhuk.examination.common.utils.GkStringUtils;
import com.zhuk.examination.common.utils.ResultJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author zhuk
 * @description: 公用请求层
 * @date 2020-05-28
 */
@Controller
@Slf4j
public class BaseController {
    /**
     * 页面跳转公用方法
     * @param model
     * @param url
     * @param params
     * @return
     */
    @GetMapping("goToUrl")
    public String goToUrl(Model model, String url, String params){
        if(!GkStringUtils.isEmpty(params)){
            JSONObject jsonObject=JSONObject.parseObject(params);
            for(String key:jsonObject.keySet()){
                model.addAttribute(key,jsonObject.get(key));
            }
        }
        return url;
    }

    /**
     * 获取后台定义枚举
     * @param enumNames 枚举类名，多个枚举类逗号隔开
     * @return
     */
    @GetMapping("getEnums")
    @ResponseBody
    public ResultJson goToUrl(String enumNames){
        ResultJson result = new ResultJson();
        try {
            String[] names = enumNames.split(",");
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
            result.setCode(ResultJson.Code.SUCCESS_CODE.toString());
            result.setResult(maps);
        } catch (Exception e) {
            result.setCode(ResultJson.Code.FAIL_CODE.toString());
            result.setMsg(ResultJson.Msg.FAIL_MSG.toString());
            log.error("查询{}枚举失败：" + e.getMessage(),enumNames);
        }
        return result;
    }
}
