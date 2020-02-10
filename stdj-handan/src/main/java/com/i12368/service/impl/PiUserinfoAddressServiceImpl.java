package com.i12368.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.i12368.dao.IPiUserinfoAddressMapper;
import com.i12368.entity.PiUserinfoAddress;
import com.i12368.service.IPiUserinfoAddressService;
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
public class PiUserinfoAddressServiceImpl extends ServiceImpl<IPiUserinfoAddressMapper, PiUserinfoAddress> implements IPiUserinfoAddressService {
    @Override
    public boolean save(PiUserinfoAddress entity) {
        return super.save(entity);
    }

}
