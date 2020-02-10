package com.i12368;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication(exclude={DruidDataSourceAutoConfigure.class})
public class StdjHandanApplication {
    public static void main(String[] args) {
        SpringApplication.run(StdjHandanApplication.class, args);
    }

}
