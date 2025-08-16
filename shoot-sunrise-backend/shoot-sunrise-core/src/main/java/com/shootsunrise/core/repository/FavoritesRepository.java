package com.shootsunrise.core.repository;

import com.shootsunrise.common.api.request.PageRequest;
import com.shootsunrise.common.api.response.PageResult;
import com.shootsunrise.core.entity.Favorites;

import java.util.List;

/**
 * 收藏记录表 Repository 接口
 * @author lyj
 * @since 2025-07-27
 */
public interface FavoritesRepository {

    /**
     * 根据ID查询收藏记录
     * @param id 收藏ID
     * @return 收藏记录信息
     */
    Favorites findById(Long id);

    /**
     * 根据用户ID查询收藏列表
     * @param userId 用户ID
     * @return 收藏列表
     */
    List<Favorites> findByUserId(Long userId);

    /**
     * 根据用户ID和收藏类型查询收藏列表
     * @param userId 用户ID
     * @param targetType 收藏类型
     * @return 收藏列表
     */
    List<Favorites> findByUserIdAndTargetType(Long userId, String targetType);

    /**
     * 查询用户是否收藏了指定目标
     * @param userId 用户ID
     * @param targetType 收藏类型
     * @param targetId 目标ID
     * @return 收藏记录
     */
    Favorites findByUserIdAndTarget(Long userId, String targetType, Long targetId);

    /**
     * 根据目标查询收藏列表
     * @param targetType 收藏类型
     * @param targetId 目标ID
     * @return 收藏列表
     */
    List<Favorites> findByTarget(String targetType, Long targetId);

    /**
     * 分页查询收藏记录列表
     * @param pageRequest 分页请求
     * @return 分页结果
     */
    PageResult<Favorites> findPage(PageRequest pageRequest);

    /**
     * 保存收藏记录
     * @param favorite 收藏记录信息
     * @return 保存后的收藏记录信息
     */
    Favorites save(Favorites favorite);

    /**
     * 更新收藏记录
     * @param favorite 收藏记录信息
     * @return 更新后的收藏记录信息
     */
    Favorites update(Favorites favorite);

    /**
     * 删除收藏记录
     * @param id 收藏ID
     * @return 是否删除成功
     */
    boolean deleteById(Long id);

    /**
     * 取消收藏
     * @param userId 用户ID
     * @param targetType 收藏类型
     * @param targetId 目标ID
     * @return 是否取消成功
     */
    boolean cancelFavorite(Long userId, String targetType, Long targetId);

    /**
     * 统计收藏记录总数
     * @return 收藏记录总数
     */
    long count();

    /**
     * 统计用户的收藏数量
     * @param userId 用户ID
     * @return 收藏数量
     */
    long countByUserId(Long userId);

    /**
     * 统计目标的收藏数量
     * @param targetType 收藏类型
     * @param targetId 目标ID
     * @return 收藏数量
     */
    long countByTarget(String targetType, Long targetId);

    /**
     * 检查用户是否收藏了指定目标
     * @param userId 用户ID
     * @param targetType 收藏类型
     * @param targetId 目标ID
     * @return 是否已收藏
     */
    boolean isFavorited(Long userId, String targetType, Long targetId);
}
