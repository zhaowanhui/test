package com.i12368.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.i12368.dao.IPiCaseUserRelMapper;
import com.i12368.entity.PiCaseUserRel;
import com.i12368.service.IPiCaseUserRelService;
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
public class PiCaseUserRelServiceImpl extends ServiceImpl<IPiCaseUserRelMapper, PiCaseUserRel> implements IPiCaseUserRelService {
    @Override
    public boolean save(PiCaseUserRel entity) {
        return super.save(entity);
    }
}
