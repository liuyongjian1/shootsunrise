package com.shootsunrise.core.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shootsunrise.common.api.request.PageRequest;
import com.shootsunrise.common.api.response.PageResult;
import com.shootsunrise.core.entity.Reviews;
import com.shootsunrise.core.mapper.ReviewsMapper;
import com.shootsunrise.core.repository.ReviewsRepository;
import org.springframework.stereotype.Repository;

import jakarta.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * 评价表 Repository 实现类
 * @author lyj
 * @since 2025-07-27
 */
@Repository
public class ReviewsRepositoryImpl implements ReviewsRepository {

    @Resource
    private ReviewsMapper reviewsMapper;

    @Override
    public Reviews findById(Long id) {
        return reviewsMapper.selectById(id);
    }

    @Override
    public Reviews findByBookingId(Long bookingId) {
        LambdaQueryWrapper<Reviews> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Reviews::getBookingId, bookingId)
               .eq(Reviews::getIsDelete, 0);
        return reviewsMapper.selectOne(wrapper);
    }

    @Override
    public List<Reviews> findByClientId(Long clientId) {
        LambdaQueryWrapper<Reviews> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Reviews::getClientId, clientId)
               .eq(Reviews::getIsDelete, 0)
               .orderByDesc(Reviews::getCreateTime);
        return reviewsMapper.selectList(wrapper);
    }

    @Override
    public List<Reviews> findByPhotographerId(Long photographerId) {
        LambdaQueryWrapper<Reviews> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Reviews::getPhotographerId, photographerId)
               .eq(Reviews::getIsDelete, 0)
               .orderByDesc(Reviews::getCreateTime);
        return reviewsMapper.selectList(wrapper);
    }

    @Override
    public PageResult<Reviews> findPage(PageRequest pageRequest) {
        LambdaQueryWrapper<Reviews> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Reviews::getIsDelete, 0)
               .orderByDesc(Reviews::getCreateTime);
        return reviewsMapper.selectPage(pageRequest, wrapper);
    }

    @Override
    public List<Reviews> findVisibleReviewsByPhotographerId(Long photographerId) {
        LambdaQueryWrapper<Reviews> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Reviews::getPhotographerId, photographerId)
               .eq(Reviews::getIsVisible, true)
               .eq(Reviews::getIsDelete, 0)
               .orderByDesc(Reviews::getRating)
               .orderByDesc(Reviews::getCreateTime);
        return reviewsMapper.selectList(wrapper);
    }

    @Override
    public List<Reviews> findByRatingRange(BigDecimal minRating, BigDecimal maxRating) {
        LambdaQueryWrapper<Reviews> wrapper = new LambdaQueryWrapper<>();
        wrapper.ge(Reviews::getRating, minRating)
               .le(Reviews::getRating, maxRating)
               .eq(Reviews::getIsVisible, true)
               .eq(Reviews::getIsDelete, 0)
               .orderByDesc(Reviews::getRating);
        return reviewsMapper.selectList(wrapper);
    }

    @Override
    public Reviews save(Reviews review) {
        reviewsMapper.insert(review);
        return review;
    }

    @Override
    public Reviews update(Reviews review) {
        reviewsMapper.updateById(review);
        return review;
    }

    @Override
    public boolean deleteById(Long id) {
        Reviews review = new Reviews();
        review.setId(id);
        review.setIsDelete(1);
        return reviewsMapper.updateById(review) > 0;
    }

    @Override
    public boolean setVisibility(Long id, Boolean isVisible) {
        Reviews review = new Reviews();
        review.setId(id);
        review.setIsVisible(isVisible);
        return reviewsMapper.updateById(review) > 0;
    }

    @Override
    public boolean incrementLikeCount(Long id) {
        // 1. 查询当前点赞数
        Reviews current = reviewsMapper.selectById(id);
        if (current == null) {
            return false;
        }
        
        // 2. 更新点赞数
        Reviews review = new Reviews();
        review.setId(id);
        review.setLikesCount(current.getLikesCount() + 1);
        return reviewsMapper.updateById(review) > 0;
    }

    @Override
    public long count() {
        LambdaQueryWrapper<Reviews> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Reviews::getIsDelete, 0);
        return reviewsMapper.selectCount(wrapper);
    }

    @Override
    public long countByPhotographerId(Long photographerId) {
        LambdaQueryWrapper<Reviews> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Reviews::getPhotographerId, photographerId)
               .eq(Reviews::getIsDelete, 0);
        return reviewsMapper.selectCount(wrapper);
    }

    @Override
    public BigDecimal calculateAverageRating(Long photographerId) {
        // 1. 查询摄影师的所有评价
        List<Reviews> reviews = findByPhotographerId(photographerId);
        if (reviews.isEmpty()) {
            return BigDecimal.ZERO;
        }
        
        // 2. 计算平均评分
        BigDecimal totalRating = reviews.stream()
                .map(Reviews::getRating)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        return totalRating.divide(BigDecimal.valueOf(reviews.size()), 2, BigDecimal.ROUND_HALF_UP);
    }
}
