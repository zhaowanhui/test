package com.i12368.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.i12368.dao.IPiUserinfoPhoneMapper;
import com.i12368.entity.PiUserinfoPhone;
import com.i12368.service.IPiUserinfoPhoneService;
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
public class PiUserinfoPhoneServiceImpl extends ServiceImpl<IPiUserinfoPhoneMapper, PiUserinfoPhone> implements IPiUserinfoPhoneService {
    @Override
    public boolean save(PiUserinfoPhone entity) {
        return super.save(entity);
    }

}
