package com.shootsunrise.core.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shootsunrise.common.api.request.PageRequest;
import com.shootsunrise.common.api.response.PageResult;
import com.shootsunrise.core.entity.Promotions;
import com.shootsunrise.core.mapper.PromotionsMapper;
import com.shootsunrise.core.repository.PromotionsRepository;
import org.springframework.stereotype.Repository;

import jakarta.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 营销活动表 Repository 实现类
 * @author lyj
 * @since 2025-07-27
 */
@Repository
public class PromotionsRepositoryImpl implements PromotionsRepository {

    @Resource
    private PromotionsMapper promotionsMapper;

    @Override
    public Promotions findById(Long id) {
        return promotionsMapper.selectById(id);
    }

    @Override
    public List<Promotions> findByPhotographerId(Long photographerId) {
        LambdaQueryWrapper<Promotions> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Promotions::getPhotographerId, photographerId)
               .eq(Promotions::getIsDelete, 0)
               .orderByDesc(Promotions::getCreateTime);
        return promotionsMapper.selectList(wrapper);
    }

    @Override
    public List<Promotions> findByType(String type) {
        LambdaQueryWrapper<Promotions> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Promotions::getType, type)
               .eq(Promotions::getIsDelete, 0)
               .orderByDesc(Promotions::getCreateTime);
        return promotionsMapper.selectList(wrapper);
    }

    @Override
    public List<Promotions> findActivePromotions() {
        LambdaQueryWrapper<Promotions> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Promotions::getIsActive, true)
               .eq(Promotions::getIsDelete, 0)
               .orderByDesc(Promotions::getCreateTime);
        return promotionsMapper.selectList(wrapper);
    }

    @Override
    public List<Promotions> findOngoingPromotions() {
        LocalDateTime now = LocalDateTime.now();
        LambdaQueryWrapper<Promotions> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Promotions::getIsActive, true)
               .le(Promotions::getStartTime, now)
               .ge(Promotions::getEndTime, now)
               .eq(Promotions::getIsDelete, 0)
               .orderByDesc(Promotions::getCreateTime);
        return promotionsMapper.selectList(wrapper);
    }

    @Override
    public List<Promotions> findByTargetUsers(String targetUsers) {
        LambdaQueryWrapper<Promotions> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Promotions::getTargetUsers, targetUsers)
               .eq(Promotions::getIsDelete, 0)
               .orderByDesc(Promotions::getCreateTime);
        return promotionsMapper.selectList(wrapper);
    }

    @Override
    public List<Promotions> findByTimeRange(LocalDateTime startTime, LocalDateTime endTime) {
        LambdaQueryWrapper<Promotions> wrapper = new LambdaQueryWrapper<>();
        wrapper.ge(Promotions::getStartTime, startTime)
               .le(Promotions::getEndTime, endTime)
               .eq(Promotions::getIsDelete, 0)
               .orderByDesc(Promotions::getCreateTime);
        return promotionsMapper.selectList(wrapper);
    }

    @Override
    public PageResult<Promotions> findPage(PageRequest pageRequest) {
        LambdaQueryWrapper<Promotions> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Promotions::getIsDelete, 0)
               .orderByDesc(Promotions::getCreateTime);
        return promotionsMapper.selectPage(pageRequest, wrapper);
    }

    @Override
    public Promotions save(Promotions promotion) {
        promotionsMapper.insert(promotion);
        return promotion;
    }

    @Override
    public Promotions update(Promotions promotion) {
        promotionsMapper.updateById(promotion);
        return promotion;
    }

    @Override
    public boolean deleteById(Long id) {
        Promotions promotion = new Promotions();
        promotion.setId(id);
        promotion.setIsDelete(1);
        return promotionsMapper.updateById(promotion) > 0;
    }

    @Override
    public boolean setActive(Long id, Boolean isActive) {
        Promotions promotion = new Promotions();
        promotion.setId(id);
        promotion.setIsActive(isActive);
        return promotionsMapper.updateById(promotion) > 0;
    }

    @Override
    public boolean incrementParticipantCount(Long id) {
        // 1. 查询当前参与人数
        Promotions current = promotionsMapper.selectById(id);
        if (current == null) {
            return false;
        }
        
        // 2. 检查是否已达到限制
        if (current.getParticipantLimit() != null && 
            current.getParticipantCount() >= current.getParticipantLimit()) {
            return false;
        }
        
        // 3. 更新参与人数
        Promotions promotion = new Promotions();
        promotion.setId(id);
        promotion.setParticipantCount(current.getParticipantCount() + 1);
        return promotionsMapper.updateById(promotion) > 0;
    }

    @Override
    public boolean decrementParticipantCount(Long id) {
        // 1. 查询当前参与人数
        Promotions current = promotionsMapper.selectById(id);
        if (current == null || current.getParticipantCount() <= 0) {
            return false;
        }
        
        // 2. 更新参与人数
        Promotions promotion = new Promotions();
        promotion.setId(id);
        promotion.setParticipantCount(current.getParticipantCount() - 1);
        return promotionsMapper.updateById(promotion) > 0;
    }

    @Override
    public boolean isParticipantLimitReached(Long id) {
        Promotions promotion = promotionsMapper.selectById(id);
        if (promotion == null || promotion.getParticipantLimit() == null) {
            return false;
        }
        return promotion.getParticipantCount() >= promotion.getParticipantLimit();
    }

    @Override
    public long count() {
        LambdaQueryWrapper<Promotions> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Promotions::getIsDelete, 0);
        return promotionsMapper.selectCount(wrapper);
    }

    @Override
    public long countByPhotographerId(Long photographerId) {
        LambdaQueryWrapper<Promotions> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Promotions::getPhotographerId, photographerId)
               .eq(Promotions::getIsDelete, 0);
        return promotionsMapper.selectCount(wrapper);
    }

    @Override
    public long countByType(String type) {
        LambdaQueryWrapper<Promotions> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Promotions::getType, type)
               .eq(Promotions::getIsDelete, 0);
        return promotionsMapper.selectCount(wrapper);
    }

    @Override
    public long countActivePromotions() {
        LambdaQueryWrapper<Promotions> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Promotions::getIsActive, true)
               .eq(Promotions::getIsDelete, 0);
        return promotionsMapper.selectCount(wrapper);
    }
}
