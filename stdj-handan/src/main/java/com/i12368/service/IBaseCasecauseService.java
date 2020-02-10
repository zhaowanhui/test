package com.i12368.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.i12368.entity.BaseCasecause;

/**
 * <p>
 * 基础数据 - 案由管理 服务类
 * </p>
 *
 * @author zhaowanhui
 * @since 2019-8-22
 */
public interface IBaseCasecauseService extends IService<BaseCasecause> {
    BaseCasecause getCCCode(String ccname);
}
