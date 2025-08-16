package com.shootsunrise.core.mapper;

import com.shootsunrise.core.common.mybatis.core.mapper.BaseMapperX;
import com.shootsunrise.core.entity.Favorites;
import org.apache.ibatis.annotations.Mapper;

/**
 * 收藏记录表 Mapper 接口
 * @author lyj
 * @since 2025-07-27
 */
@Mapper
public interface FavoritesMapper extends BaseMapperX<Favorites> {

}
