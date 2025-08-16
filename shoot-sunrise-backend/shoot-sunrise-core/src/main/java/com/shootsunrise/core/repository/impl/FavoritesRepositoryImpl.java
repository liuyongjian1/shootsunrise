package com.shootsunrise.core.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shootsunrise.common.api.request.PageRequest;
import com.shootsunrise.common.api.response.PageResult;
import com.shootsunrise.core.entity.Favorites;
import com.shootsunrise.core.mapper.FavoritesMapper;
import com.shootsunrise.core.repository.FavoritesRepository;
import org.springframework.stereotype.Repository;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * 收藏记录表 Repository 实现类
 * @author lyj
 * @since 2025-07-27
 */
@Repository
public class FavoritesRepositoryImpl implements FavoritesRepository {

    @Resource
    private FavoritesMapper favoritesMapper;

    @Override
    public Favorites findById(Long id) {
        return favoritesMapper.selectById(id);
    }

    @Override
    public List<Favorites> findByUserId(Long userId) {
        LambdaQueryWrapper<Favorites> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Favorites::getUserId, userId)
               .eq(Favorites::getIsDelete, 0)
               .orderByDesc(Favorites::getCreateTime);
        return favoritesMapper.selectList(wrapper);
    }

    @Override
    public List<Favorites> findByUserIdAndTargetType(Long userId, String targetType) {
        LambdaQueryWrapper<Favorites> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Favorites::getUserId, userId)
               .eq(Favorites::getTargetType, targetType)
               .eq(Favorites::getIsDelete, 0)
               .orderByDesc(Favorites::getCreateTime);
        return favoritesMapper.selectList(wrapper);
    }

    @Override
    public Favorites findByUserIdAndTarget(Long userId, String targetType, Long targetId) {
        LambdaQueryWrapper<Favorites> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Favorites::getUserId, userId)
               .eq(Favorites::getTargetType, targetType)
               .eq(Favorites::getTargetId, targetId)
               .eq(Favorites::getIsDelete, 0);
        return favoritesMapper.selectOne(wrapper);
    }

    @Override
    public List<Favorites> findByTarget(String targetType, Long targetId) {
        LambdaQueryWrapper<Favorites> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Favorites::getTargetType, targetType)
               .eq(Favorites::getTargetId, targetId)
               .eq(Favorites::getIsDelete, 0)
               .orderByDesc(Favorites::getCreateTime);
        return favoritesMapper.selectList(wrapper);
    }

    @Override
    public PageResult<Favorites> findPage(PageRequest pageRequest) {
        LambdaQueryWrapper<Favorites> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Favorites::getIsDelete, 0)
               .orderByDesc(Favorites::getCreateTime);
        return favoritesMapper.selectPage(pageRequest, wrapper);
    }

    @Override
    public Favorites save(Favorites favorite) {
        favoritesMapper.insert(favorite);
        return favorite;
    }

    @Override
    public Favorites update(Favorites favorite) {
        favoritesMapper.updateById(favorite);
        return favorite;
    }

    @Override
    public boolean deleteById(Long id) {
        Favorites favorite = new Favorites();
        favorite.setId(id);
        favorite.setIsDelete(1);
        return favoritesMapper.updateById(favorite) > 0;
    }

    @Override
    public boolean cancelFavorite(Long userId, String targetType, Long targetId) {
        Favorites favorite = findByUserIdAndTarget(userId, targetType, targetId);
        if (favorite == null) {
            return false;
        }
        return deleteById(favorite.getId());
    }

    @Override
    public long count() {
        LambdaQueryWrapper<Favorites> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Favorites::getIsDelete, 0);
        return favoritesMapper.selectCount(wrapper);
    }

    @Override
    public long countByUserId(Long userId) {
        LambdaQueryWrapper<Favorites> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Favorites::getUserId, userId)
               .eq(Favorites::getIsDelete, 0);
        return favoritesMapper.selectCount(wrapper);
    }

    @Override
    public long countByTarget(String targetType, Long targetId) {
        LambdaQueryWrapper<Favorites> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Favorites::getTargetType, targetType)
               .eq(Favorites::getTargetId, targetId)
               .eq(Favorites::getIsDelete, 0);
        return favoritesMapper.selectCount(wrapper);
    }

    @Override
    public boolean isFavorited(Long userId, String targetType, Long targetId) {
        return findByUserIdAndTarget(userId, targetType, targetId) != null;
    }
}
