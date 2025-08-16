package com.shootsunrise.core.mapper;

import com.shootsunrise.core.common.mybatis.core.mapper.BaseMapperX;
import com.shootsunrise.core.entity.UserLikes;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户点赞记录表 Mapper 接口
 * @author lyj
 * @since 2025-07-27
 */
@Mapper
public interface UserLikesMapper extends BaseMapperX<UserLikes> {

}
