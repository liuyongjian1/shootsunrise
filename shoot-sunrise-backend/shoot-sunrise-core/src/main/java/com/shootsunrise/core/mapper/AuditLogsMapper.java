package com.shootsunrise.core.mapper;

import com.shootsunrise.core.common.mybatis.core.mapper.BaseMapperX;
import com.shootsunrise.core.entity.AuditLogs;
import org.apache.ibatis.annotations.Mapper;

/**
 * 内容审核日志表 Mapper 接口
 * @author lyj
 * @since 2025-07-27
 */
@Mapper
public interface AuditLogsMapper extends BaseMapperX<AuditLogs> {

}
