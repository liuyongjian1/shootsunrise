package com.shootsunrise.core.mapper;

import com.shootsunrise.core.common.mybatis.core.mapper.BaseMapperX;
import com.shootsunrise.core.entity.Payments;
import org.apache.ibatis.annotations.Mapper;

/**
 * 支付记录表 Mapper 接口
 * @author lyj
 * @since 2025-07-27
 */
@Mapper
public interface PaymentsMapper extends BaseMapperX<Payments> {

}
