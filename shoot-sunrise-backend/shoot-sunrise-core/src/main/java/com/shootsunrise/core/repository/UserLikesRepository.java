package com.shootsunrise.core.repository;

import com.shootsunrise.common.api.request.PageRequest;
import com.shootsunrise.common.api.response.PageResult;
import com.shootsunrise.core.entity.UserLikes;

import java.util.List;

/**
 * 用户点赞记录表 Repository 接口
 * @author lyj
 * @since 2025-07-27
 */
public interface UserLikesRepository {

    /**
     * 根据ID查询点赞记录
     * @param id 点赞ID
     * @return 点赞记录信息
     */
    UserLikes findById(Long id);

    /**
     * 根据用户ID查询点赞列表
     * @param userId 用户ID
     * @return 点赞列表
     */
    List<UserLikes> findByUserId(Long userId);

    /**
     * 根据用户ID和点赞类型查询点赞列表
     * @param userId 用户ID
     * @param targetType 点赞类型
     * @return 点赞列表
     */
    List<UserLikes> findByUserIdAndTargetType(Long userId, String targetType);

    /**
     * 查询用户是否点赞了指定目标
     * @param userId 用户ID
     * @param targetType 点赞类型
     * @param targetId 目标ID
     * @return 点赞记录
     */
    UserLikes findByUserIdAndTarget(Long userId, String targetType, Long targetId);

    /**
     * 根据目标查询点赞列表
     * @param targetType 点赞类型
     * @param targetId 目标ID
     * @return 点赞列表
     */
    List<UserLikes> findByTarget(String targetType, Long targetId);

    /**
     * 分页查询点赞记录列表
     * @param pageRequest 分页请求
     * @return 分页结果
     */
    PageResult<UserLikes> findPage(PageRequest pageRequest);

    /**
     * 保存点赞记录
     * @param userLike 点赞记录信息
     * @return 保存后的点赞记录信息
     */
    UserLikes save(UserLikes userLike);

    /**
     * 更新点赞记录
     * @param userLike 点赞记录信息
     * @return 更新后的点赞记录信息
     */
    UserLikes update(UserLikes userLike);

    /**
     * 删除点赞记录
     * @param id 点赞ID
     * @return 是否删除成功
     */
    boolean deleteById(Long id);

    /**
     * 取消点赞
     * @param userId 用户ID
     * @param targetType 点赞类型
     * @param targetId 目标ID
     * @return 是否取消成功
     */
    boolean cancelLike(Long userId, String targetType, Long targetId);

    /**
     * 统计点赞记录总数
     * @return 点赞记录总数
     */
    long count();

    /**
     * 统计用户的点赞数量
     * @param userId 用户ID
     * @return 点赞数量
     */
    long countByUserId(Long userId);

    /**
     * 统计目标的点赞数量
     * @param targetType 点赞类型
     * @param targetId 目标ID
     * @return 点赞数量
     */
    long countByTarget(String targetType, Long targetId);

    /**
     * 检查用户是否点赞了指定目标
     * @param userId 用户ID
     * @param targetType 点赞类型
     * @param targetId 目标ID
     * @return 是否已点赞
     */
    boolean isLiked(Long userId, String targetType, Long targetId);
}
