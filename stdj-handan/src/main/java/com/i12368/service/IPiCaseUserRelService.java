package com.i12368.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.i12368.entity.PiCaseUserRel;

/**
 * <p>
 * 案件信息数据 服务类
 * </p>
 *
 * @author zhaowanhui
 * @since 2019-8-22
 */
public interface IPiCaseUserRelService extends IService<PiCaseUserRel> {
    @Override
    boolean save(PiCaseUserRel entity);
}
