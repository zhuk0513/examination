package com.zhuk.examination.web;

import com.alibaba.fastjson.JSONObject;
import com.zhuk.examination.service.TestService;
import com.zhuk.examination.service.impl.ESServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhuk
 * @since 2020-05-26
 */
@Controller
@RequestMapping("/test")
@Slf4j
public class TestController {
    @Resource
    private TestService testService;
    @Resource
    private ESServiceImpl esService ;

    @PostMapping("getTest")
    @ResponseBody
    public String  getTest(){
        JSONObject result=new JSONObject();
        try {
            result.put("code", 1);
            result.put("result", testService.list());
        }catch (Exception e){
            result.put("code", 0);
            result.put("msg", "出错了请联系管理员: "+e.getMessage());
        }
        return result.toString();
    }

    @PostMapping("esAdd")
    @ResponseBody
    public String  esAdd(){
        JSONObject result=new JSONObject();
        try {
            esService.add();
            result.put("code", 1);
        }catch (Exception e){
            result.put("code", 0);
            result.put("msg", "出错了请联系管理员: "+e.getMessage());
        }
        return result.toString();
    }


    @GetMapping("index")
    public String  index(){
        return "index";
    }

    @GetMapping("taskManage")
    public String  taskManage(){
        return "page/test/taskManage";
    }
}
