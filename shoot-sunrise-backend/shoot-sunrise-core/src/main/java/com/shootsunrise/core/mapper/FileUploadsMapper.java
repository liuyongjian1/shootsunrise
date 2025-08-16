package com.shootsunrise.core.mapper;

import com.shootsunrise.core.common.mybatis.core.mapper.BaseMapperX;
import com.shootsunrise.core.entity.FileUploads;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文件上传记录表 Mapper 接口
 * @author lyj
 * @since 2025-07-27
 */
@Mapper
public interface FileUploadsMapper extends BaseMapperX<FileUploads> {

}
