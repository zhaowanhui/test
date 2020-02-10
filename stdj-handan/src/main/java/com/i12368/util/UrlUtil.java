package com.i12368.util;

import com.i12368.entity.UrlVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UrlUtil {
    @Value("${LoginUrl}")
    private String loginUrl;
    @Value("${QueryUrl}")
    private String queryUrl;
    @Value("${Host}")
    private String host;

    public UrlVO getUrlVO(){
        UrlVO urlVO = new UrlVO();
        urlVO.setLoginUrl(this.loginUrl);
        urlVO.setQueryUrl(this.queryUrl);
        urlVO.setHost(this.host);
        return urlVO;
    }
}
