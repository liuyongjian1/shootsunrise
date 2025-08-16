package com.shootsunrise.core.mapper;

import com.shootsunrise.core.common.mybatis.core.mapper.BaseMapperX;
import com.shootsunrise.core.entity.UserFollows;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户关注关系表 Mapper 接口
 * @author lyj
 * @since 2025-07-27
 */
@Mapper
public interface UserFollowsMapper extends BaseMapperX<UserFollows> {

}
