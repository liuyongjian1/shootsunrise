package com.shootsunrise.core.repository;

import com.shootsunrise.common.api.request.PageRequest;
import com.shootsunrise.common.api.response.PageResult;
import com.shootsunrise.core.entity.UserFollows;

import java.util.List;

/**
 * 用户关注关系表 Repository 接口
 * @author lyj
 * @since 2025-07-27
 */
public interface UserFollowsRepository {

    /**
     * 根据ID查询关注关系
     * @param id 关注ID
     * @return 关注关系信息
     */
    UserFollows findById(Long id);

    /**
     * 根据关注者ID查询关注列表
     * @param followerId 关注者ID
     * @return 关注列表
     */
    List<UserFollows> findByFollowerId(Long followerId);

    /**
     * 根据被关注者ID查询粉丝列表
     * @param followingId 被关注者ID
     * @return 粉丝列表
     */
    List<UserFollows> findByFollowingId(Long followingId);

    /**
     * 查询关注关系
     * @param followerId 关注者ID
     * @param followingId 被关注者ID
     * @return 关注关系
     */
    UserFollows findByFollowerIdAndFollowingId(Long followerId, Long followingId);

    /**
     * 分页查询关注关系列表
     * @param pageRequest 分页请求
     * @return 分页结果
     */
    PageResult<UserFollows> findPage(PageRequest pageRequest);

    /**
     * 分页查询用户的关注列表
     * @param followerId 关注者ID
     * @param pageRequest 分页请求
     * @return 分页结果
     */
    PageResult<UserFollows> findFollowingsByFollowerId(Long followerId, PageRequest pageRequest);

    /**
     * 分页查询用户的粉丝列表
     * @param followingId 被关注者ID
     * @param pageRequest 分页请求
     * @return 分页结果
     */
    PageResult<UserFollows> findFollowersByFollowingId(Long followingId, PageRequest pageRequest);

    /**
     * 保存关注关系
     * @param userFollow 关注关系信息
     * @return 保存后的关注关系信息
     */
    UserFollows save(UserFollows userFollow);

    /**
     * 更新关注关系
     * @param userFollow 关注关系信息
     * @return 更新后的关注关系信息
     */
    UserFollows update(UserFollows userFollow);

    /**
     * 删除关注关系
     * @param id 关注ID
     * @return 是否删除成功
     */
    boolean deleteById(Long id);

    /**
     * 取消关注
     * @param followerId 关注者ID
     * @param followingId 被关注者ID
     * @return 是否取消成功
     */
    boolean unfollow(Long followerId, Long followingId);

    /**
     * 检查是否已关注
     * @param followerId 关注者ID
     * @param followingId 被关注者ID
     * @return 是否已关注
     */
    boolean isFollowing(Long followerId, Long followingId);

    /**
     * 统计关注关系总数
     * @return 关注关系总数
     */
    long count();

    /**
     * 统计用户的关注数量
     * @param followerId 关注者ID
     * @return 关注数量
     */
    long countFollowingsByFollowerId(Long followerId);

    /**
     * 统计用户的粉丝数量
     * @param followingId 被关注者ID
     * @return 粉丝数量
     */
    long countFollowersByFollowingId(Long followingId);

    /**
     * 查询互相关注的用户列表
     * @param userId 用户ID
     * @return 互相关注的用户ID列表
     */
    List<Long> findMutualFollowings(Long userId);
}
