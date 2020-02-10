package com.i12368.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.i12368.dao.IPiCaseinfoMapper;
import com.i12368.entity.PiCaseinfo;
import com.i12368.service.IPiCaseinfoService;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * <p>
 * 案件信息数据 服务实现类
 * </p>
 *
 * @author zhaowanhui
 * @since 2019-8-22
 */
@Service
public class PiCaseinfoServiceImpl extends ServiceImpl<IPiCaseinfoMapper, PiCaseinfo> implements IPiCaseinfoService {
    @Override
    public boolean save(PiCaseinfo entity) {
        return super.save(entity);
    }
}
