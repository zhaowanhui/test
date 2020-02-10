package com.i12368.enums;

/**
 * @Auther: zhaowanhui
 * @Date: 2019-8-21
 * @Description: 当事人枚举
 */
public enum PiUserEnum {
    NATURAL(1,"自然人"),
    LEGAL(2,"法人"),
    UNINCORPORATED(3,"非法然组织"),
    AGENT(0,"代理人");
    private Integer code;
    private String msg;
    PiUserEnum(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
