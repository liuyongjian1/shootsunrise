package com.shootsunrise.core.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shootsunrise.common.api.request.PageRequest;
import com.shootsunrise.common.api.response.PageResult;
import com.shootsunrise.core.entity.UserLikes;
import com.shootsunrise.core.mapper.UserLikesMapper;
import com.shootsunrise.core.repository.UserLikesRepository;
import org.springframework.stereotype.Repository;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * 用户点赞记录表 Repository 实现类
 * @author lyj
 * @since 2025-07-27
 */
@Repository
public class UserLikesRepositoryImpl implements UserLikesRepository {

    @Resource
    private UserLikesMapper userLikesMapper;

    @Override
    public UserLikes findById(Long id) {
        return userLikesMapper.selectById(id);
    }

    @Override
    public List<UserLikes> findByUserId(Long userId) {
        LambdaQueryWrapper<UserLikes> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserLikes::getUserId, userId)
               .eq(UserLikes::getIsDelete, 0)
               .orderByDesc(UserLikes::getCreateTime);
        return userLikesMapper.selectList(wrapper);
    }

    @Override
    public List<UserLikes> findByUserIdAndTargetType(Long userId, String targetType) {
        LambdaQueryWrapper<UserLikes> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserLikes::getUserId, userId)
               .eq(UserLikes::getTargetType, targetType)
               .eq(UserLikes::getIsDelete, 0)
               .orderByDesc(UserLikes::getCreateTime);
        return userLikesMapper.selectList(wrapper);
    }

    @Override
    public UserLikes findByUserIdAndTarget(Long userId, String targetType, Long targetId) {
        LambdaQueryWrapper<UserLikes> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserLikes::getUserId, userId)
               .eq(UserLikes::getTargetType, targetType)
               .eq(UserLikes::getTargetId, targetId)
               .eq(UserLikes::getIsDelete, 0);
        return userLikesMapper.selectOne(wrapper);
    }

    @Override
    public List<UserLikes> findByTarget(String targetType, Long targetId) {
        LambdaQueryWrapper<UserLikes> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserLikes::getTargetType, targetType)
               .eq(UserLikes::getTargetId, targetId)
               .eq(UserLikes::getIsDelete, 0)
               .orderByDesc(UserLikes::getCreateTime);
        return userLikesMapper.selectList(wrapper);
    }

    @Override
    public PageResult<UserLikes> findPage(PageRequest pageRequest) {
        LambdaQueryWrapper<UserLikes> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserLikes::getIsDelete, 0)
               .orderByDesc(UserLikes::getCreateTime);
        return userLikesMapper.selectPage(pageRequest, wrapper);
    }

    @Override
    public UserLikes save(UserLikes userLike) {
        userLikesMapper.insert(userLike);
        return userLike;
    }

    @Override
    public UserLikes update(UserLikes userLike) {
        userLikesMapper.updateById(userLike);
        return userLike;
    }

    @Override
    public boolean deleteById(Long id) {
        UserLikes userLike = new UserLikes();
        userLike.setId(id);
        userLike.setIsDelete(1);
        return userLikesMapper.updateById(userLike) > 0;
    }

    @Override
    public boolean cancelLike(Long userId, String targetType, Long targetId) {
        UserLikes userLike = findByUserIdAndTarget(userId, targetType, targetId);
        if (userLike == null) {
            return false;
        }
        return deleteById(userLike.getId());
    }

    @Override
    public long count() {
        LambdaQueryWrapper<UserLikes> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserLikes::getIsDelete, 0);
        return userLikesMapper.selectCount(wrapper);
    }

    @Override
    public long countByUserId(Long userId) {
        LambdaQueryWrapper<UserLikes> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserLikes::getUserId, userId)
               .eq(UserLikes::getIsDelete, 0);
        return userLikesMapper.selectCount(wrapper);
    }

    @Override
    public long countByTarget(String targetType, Long targetId) {
        LambdaQueryWrapper<UserLikes> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserLikes::getTargetType, targetType)
               .eq(UserLikes::getTargetId, targetId)
               .eq(UserLikes::getIsDelete, 0);
        return userLikesMapper.selectCount(wrapper);
    }

    @Override
    public boolean isLiked(Long userId, String targetType, Long targetId) {
        return findByUserIdAndTarget(userId, targetType, targetId) != null;
    }
}
