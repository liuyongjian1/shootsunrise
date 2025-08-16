package com.shootsunrise.core.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shootsunrise.common.api.request.PageRequest;
import com.shootsunrise.common.api.response.PageResult;
import com.shootsunrise.core.entity.UserFollows;
import com.shootsunrise.core.mapper.UserFollowsMapper;
import com.shootsunrise.core.repository.UserFollowsRepository;
import org.springframework.stereotype.Repository;

import jakarta.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户关注关系表 Repository 实现类
 * @author lyj
 * @since 2025-07-27
 */
@Repository
public class UserFollowsRepositoryImpl implements UserFollowsRepository {

    @Resource
    private UserFollowsMapper userFollowsMapper;

    @Override
    public UserFollows findById(Long id) {
        return userFollowsMapper.selectById(id);
    }

    @Override
    public List<UserFollows> findByFollowerId(Long followerId) {
        LambdaQueryWrapper<UserFollows> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserFollows::getFollowerId, followerId)
               .eq(UserFollows::getIsDelete, 0)
               .orderByDesc(UserFollows::getCreateTime);
        return userFollowsMapper.selectList(wrapper);
    }

    @Override
    public List<UserFollows> findByFollowingId(Long followingId) {
        LambdaQueryWrapper<UserFollows> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserFollows::getFollowingId, followingId)
               .eq(UserFollows::getIsDelete, 0)
               .orderByDesc(UserFollows::getCreateTime);
        return userFollowsMapper.selectList(wrapper);
    }

    @Override
    public UserFollows findByFollowerIdAndFollowingId(Long followerId, Long followingId) {
        LambdaQueryWrapper<UserFollows> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserFollows::getFollowerId, followerId)
               .eq(UserFollows::getFollowingId, followingId)
               .eq(UserFollows::getIsDelete, 0);
        return userFollowsMapper.selectOne(wrapper);
    }

    @Override
    public PageResult<UserFollows> findPage(PageRequest pageRequest) {
        LambdaQueryWrapper<UserFollows> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserFollows::getIsDelete, 0)
               .orderByDesc(UserFollows::getCreateTime);
        return userFollowsMapper.selectPage(pageRequest, wrapper);
    }

    @Override
    public PageResult<UserFollows> findFollowingsByFollowerId(Long followerId, PageRequest pageRequest) {
        LambdaQueryWrapper<UserFollows> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserFollows::getFollowerId, followerId)
               .eq(UserFollows::getIsDelete, 0)
               .orderByDesc(UserFollows::getCreateTime);
        return userFollowsMapper.selectPage(pageRequest, wrapper);
    }

    @Override
    public PageResult<UserFollows> findFollowersByFollowingId(Long followingId, PageRequest pageRequest) {
        LambdaQueryWrapper<UserFollows> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserFollows::getFollowingId, followingId)
               .eq(UserFollows::getIsDelete, 0)
               .orderByDesc(UserFollows::getCreateTime);
        return userFollowsMapper.selectPage(pageRequest, wrapper);
    }

    @Override
    public UserFollows save(UserFollows userFollow) {
        userFollowsMapper.insert(userFollow);
        return userFollow;
    }

    @Override
    public UserFollows update(UserFollows userFollow) {
        userFollowsMapper.updateById(userFollow);
        return userFollow;
    }

    @Override
    public boolean deleteById(Long id) {
        UserFollows userFollow = new UserFollows();
        userFollow.setId(id);
        userFollow.setIsDelete(1);
        return userFollowsMapper.updateById(userFollow) > 0;
    }

    @Override
    public boolean unfollow(Long followerId, Long followingId) {
        UserFollows userFollow = findByFollowerIdAndFollowingId(followerId, followingId);
        if (userFollow == null) {
            return false;
        }
        return deleteById(userFollow.getId());
    }

    @Override
    public boolean isFollowing(Long followerId, Long followingId) {
        return findByFollowerIdAndFollowingId(followerId, followingId) != null;
    }

    @Override
    public long count() {
        LambdaQueryWrapper<UserFollows> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserFollows::getIsDelete, 0);
        return userFollowsMapper.selectCount(wrapper);
    }

    @Override
    public long countFollowingsByFollowerId(Long followerId) {
        LambdaQueryWrapper<UserFollows> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserFollows::getFollowerId, followerId)
               .eq(UserFollows::getIsDelete, 0);
        return userFollowsMapper.selectCount(wrapper);
    }

    @Override
    public long countFollowersByFollowingId(Long followingId) {
        LambdaQueryWrapper<UserFollows> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserFollows::getFollowingId, followingId)
               .eq(UserFollows::getIsDelete, 0);
        return userFollowsMapper.selectCount(wrapper);
    }

    @Override
    public List<Long> findMutualFollowings(Long userId) {
        // 1. 查询用户关注的人
        List<UserFollows> followings = findByFollowerId(userId);
        List<Long> followingIds = followings.stream()
                .map(UserFollows::getFollowingId)
                .collect(Collectors.toList());
        
        if (followingIds.isEmpty()) {
            return List.of();
        }
        
        // 2. 查询这些人中也关注了该用户的
        LambdaQueryWrapper<UserFollows> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(UserFollows::getFollowerId, followingIds)
               .eq(UserFollows::getFollowingId, userId)
               .eq(UserFollows::getIsDelete, 0);
        
        List<UserFollows> mutualFollows = userFollowsMapper.selectList(wrapper);
        return mutualFollows.stream()
                .map(UserFollows::getFollowerId)
                .collect(Collectors.toList());
    }
}
