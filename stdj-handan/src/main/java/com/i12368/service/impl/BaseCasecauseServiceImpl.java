package com.i12368.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.i12368.dao.IBaseCasecauseMapper;
import com.i12368.entity.BaseCasecause;
import com.i12368.service.IBaseCasecauseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 基础数据 - 案由管理 服务实现类
 * </p>
 *
 * @author zhaowanhui
 * @since 2019-8-22
 *
 */
@Service
public class BaseCasecauseServiceImpl extends ServiceImpl<IBaseCasecauseMapper, BaseCasecause> implements IBaseCasecauseService {
    @Resource
    private IBaseCasecauseMapper iBaseCasecauseMapper;
    @Override
    public List<BaseCasecause> list(Wrapper<BaseCasecause> queryWrapper) {
        return super.list(queryWrapper);
    }

    @Override
    public BaseCasecause getCCCode(String ccname) {
        return iBaseCasecauseMapper.getCCCode(ccname);
    }
}
