package com.zhuk.examination.web;

import com.alibaba.fastjson.JSONObject;
import com.zhuk.examination.common.utils.GkStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * @author zhuk
 * @description: 地址跳转页面
 * @date 2020-05-28
 */
@Controller
public class BaseController {
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
}
