package com.i12368.enums;

public enum CardTypeEnum {
    IDENTITY(1, "身份证"),
    PASSPORT(4, "护照"),
    OTHER(3,"其他证件"),
    CREDIT(2,"统一社会信用代码");
    private Integer code;
    private String msg;

    CardTypeEnum(Integer code, String msg) {
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
