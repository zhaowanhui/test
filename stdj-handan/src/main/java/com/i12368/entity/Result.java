package com.i12368.entity;

import lombok.Data;

/**
 * 用于接收查询列表页面，它是json格式字符串
 */
@Data
public class Result {
    private String style;
    private String tbody;
    private PageVO pageInfo;
    private String customData;
    private String script;
    private Boolean success;
}
