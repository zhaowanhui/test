package com.zwh.util;

import org.apache.http.client.config.RequestConfig;

/**
 * httpclient工具类
 */
public class HttpClientUtil {

    private static final int timeOut = 30000;

    public static final String defaultCharSet = "UTF-8";

    public static RequestConfig getRequestConfig(){
        return RequestConfig.custom().setConnectTimeout(timeOut)
                .setSocketTimeout(timeOut).setConnectionRequestTimeout(timeOut).build();
    }
}
