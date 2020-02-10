package com.i12368.schedule;

import com.i12368.service.STDJService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class STDJTask {

    @Autowired
    private STDJService stdjService;
    /**
     * 抓取任务
     */
    //@Scheduled(cron = "0 0 0 * * ?")
    public void captureCase(){
        long start = new Date().getTime();
        System.out.println("开始抓取任务..............开始抓取任务...............开始抓取任务..............!"+new Date());
        stdjService.action();
        long end = new Date().getTime();
        long time = end - start;
        int day = (int) time/1000/60/60/24;
        int hour = (int) time/1000/60/60%24;
        int min = (int)time/1000/60%60;
        int sec = (int)time/1000%60;
        System.out.println("任务结束，用时:"+day+"天"+hour+"时"+min+"分"+sec+"秒");
    }
}
