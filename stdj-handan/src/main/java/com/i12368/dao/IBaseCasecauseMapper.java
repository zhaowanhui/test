package com.i12368.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.i12368.entity.BaseCasecause;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 基础数据 - 案由管理 Mapper 接口
 * </p>
 *
 * @author zzq
 * @since 2019-07-21
 */
@Mapper
public interface IBaseCasecauseMapper extends BaseMapper<BaseCasecause> {
    BaseCasecause getCCCode(@Param("ccname") String ccname);
}
