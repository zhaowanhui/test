package com.zwh.schedule;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zwh.entity.AcctCourt;
import com.zwh.service.IAcctCourtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Task {
    @Autowired
    private IAcctCourtService iAcctCourtService;

    @Scheduled(cron = "* * * * * ?")
    public void action() {
        System.out.println("开始了");
        AcctCourt acctCourt = new AcctCourt();
        acctCourt.setCourtName("石家庄市桥西区人民法院");
        AcctCourt courtName = iAcctCourtService.getOne(new QueryWrapper<>(acctCourt, "CourtName"));
        System.out.println(courtName);
    }
}
