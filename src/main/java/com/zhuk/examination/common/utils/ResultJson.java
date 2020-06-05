package com.zhuk.examination.common.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zhuk
 * @description: 返回类型
 * @date 2020-04-27
 */
@Data
public class ResultJson<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 返回状态码
     */
    private String code;
    /**
     * 返回提示信息
     */
    private String msg;
    /**
     * 返回值
     */
    private T result;
    /**
     * 分页返回总数
     */
    private long totalSize;

    public ResultJson() {

    }
    /**
     * 自定义返回参数
     * @param code
     * @param msg
     */
    public ResultJson(String code,String msg,T result) {
        this.code = code;
        this.msg = msg;
        this.result = result;
    }

    public ResultJson(Code code,Msg msg,T result) {
        this.code = code.toString();
        this.msg = msg.toString();
        this.result = result;
    }

    public ResultJson(Code code,Msg msg,T result,long totalSzie) {
        this.code = code.toString();
        this.msg = msg.toString();
        this.result = result;
        this.totalSize=totalSzie;
    }

    public String toJSONString() {
        return JSONObject.toJSONString(this);
    }

    public  enum Code
    {

        /**
         * 成功
         */
        SUCCESS_CODE("1"),

        /**
         * 失败
         */
        FAIL_CODE("0");

        private String name;

        Code(String name)
        {
            this.name = name;
        }

        public String toString()
        {
            return name;
        }
    }

    public enum Msg
    {

        /**
         * 成功
         */
        SUCCESS_MSG("操作成功"),

        /**
         * 失败
         */
        FAIL_MSG("出错了请联系管理员");

        private String name;

        Msg(String name)
        {
            this.name = name;
        }

        public String toString()
        {
            return name;
        }
    }

}
