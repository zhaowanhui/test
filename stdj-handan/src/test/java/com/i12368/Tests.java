package com.i12368;

import com.i12368.entity.PiCaseinfo;
import com.i12368.util.DateUtil;
import com.i12368.util.ErrorUtil;
import com.i12368.util.LoginInfoUtil;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalField;

public class Tests {
    public static void main(String[] args){
        /*LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        LocalDateTime localDateTime = DateUtil.dateStr2Time("2019-01-01 00:00:00");
        LocalDateTime nextDay = localDateTime.plus(1, ChronoUnit.DAYS);
        LocalDateTime of = LocalDateTime.of(2019, 1, 1, 0, 0, 0);
        System.out.println(of.plus(232,ChronoUnit.DAYS));
        System.out.println(nextDay.toString().substring(0,10));
        int dayOfYear = localDateTime.getDayOfYear();
        System.out.println(dayOfYear);
        System.out.println(now.getDayOfYear());*/
       /* PiCaseinfo piCaseinfo = new PiCaseinfo();
        PiCaseinfo piCaseinfo2 = new PiCaseinfo();

        System.out.println(piCaseinfo==piCaseinfo2);*/
        LoginInfoUtil loginInfoUtil = new LoginInfoUtil();
        System.out.println(loginInfoUtil.getLoginInfo());

        LocalDateTime localDateTime = LocalDateTime.now();
        ErrorUtil.outLog("this is error");
    }

}
