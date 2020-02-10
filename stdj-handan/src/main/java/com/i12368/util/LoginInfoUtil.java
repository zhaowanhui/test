package com.i12368.util;

import com.alibaba.fastjson.JSONObject;
import com.i12368.entity.LoginInfo;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 用来获取用户信息以及属于哪个法院
 */
@Data
@Component
public class LoginInfoUtil {
    @Value("${LoginInfo}")
    private String loginInfo;
    @Bean
    public LoginInfo getUserInfo() {
        LoginInfo loginInfo = JSONObject.parseObject(this.loginInfo, LoginInfo.class);

        return loginInfo;
    }
    public List<LoginInfo> getLoginInfos(){
        List<LoginInfo> loginInfos = new ArrayList<>();
        loginInfos.add(new LoginInfo("admin","stdj8900510..","邯郸市邯山区人民法院","154", "130402"));

        loginInfos.add(new LoginInfo("guoxiaofei","123","石家庄市桥西区人民法院","104","130104"));

        loginInfos.add(new LoginInfo("heyingxin104","142536","石家庄市桥西区人民法院","104","130104"));

        loginInfos.add(new LoginInfo("yuanyulan","1234567","石家庄市桥西区人民法院","104","130104"));

        loginInfos.add(new LoginInfo("haohongyan104","123","石家庄市桥西区人民法院","104","130104"));

        loginInfos.add(new LoginInfo("guoyali104","760119","石家庄市桥西区人民法院","104","130104"));
        return loginInfos;
    }
}
