package com.i12368.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.i12368.dao.IPiUserinfosMapper;
import com.i12368.entity.PiUserinfos;
import com.i12368.service.IPiUserinfosService;
import org.springframework.stereotype.Service;
import java.util.Collection;

/**
 * <p>
 * 当事人数据信息 服务实现类
 * </p>
 *
 * @author zhaowanhui
 * @since 2019-8-22
 */
@Service
public class PiUserinfosServiceImpl extends ServiceImpl<IPiUserinfosMapper, PiUserinfos> implements IPiUserinfosService {
    @Override
    public boolean save(PiUserinfos entity) {
        return super.save(entity);
    }

}
