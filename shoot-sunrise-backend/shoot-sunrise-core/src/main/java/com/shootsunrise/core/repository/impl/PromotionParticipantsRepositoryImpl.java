package com.shootsunrise.core.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shootsunrise.common.api.request.PageRequest;
import com.shootsunrise.common.api.response.PageResult;
import com.shootsunrise.core.entity.PromotionParticipants;
import com.shootsunrise.core.mapper.PromotionParticipantsMapper;
import com.shootsunrise.core.repository.PromotionParticipantsRepository;
import org.springframework.stereotype.Repository;

import jakarta.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 营销活动参与记录表 Repository 实现类
 * @author lyj
 * @since 2025-07-27
 */
@Repository
public class PromotionParticipantsRepositoryImpl implements PromotionParticipantsRepository {

    @Resource
    private PromotionParticipantsMapper promotionParticipantsMapper;

    @Override
    public PromotionParticipants findById(Long id) {
        return promotionParticipantsMapper.selectById(id);
    }

    @Override
    public List<PromotionParticipants> findByPromotionId(Long promotionId) {
        LambdaQueryWrapper<PromotionParticipants> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PromotionParticipants::getPromotionId, promotionId)
               .eq(PromotionParticipants::getIsDelete, 0)
               .orderByDesc(PromotionParticipants::getCreateTime);
        return promotionParticipantsMapper.selectList(wrapper);
    }

    @Override
    public List<PromotionParticipants> findByUserId(Long userId) {
        LambdaQueryWrapper<PromotionParticipants> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PromotionParticipants::getUserId, userId)
               .eq(PromotionParticipants::getIsDelete, 0)
               .orderByDesc(PromotionParticipants::getCreateTime);
        return promotionParticipantsMapper.selectList(wrapper);
    }

    @Override
    public PromotionParticipants findByCouponCode(String couponCode) {
        LambdaQueryWrapper<PromotionParticipants> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PromotionParticipants::getCouponCode, couponCode)
               .eq(PromotionParticipants::getIsDelete, 0);
        return promotionParticipantsMapper.selectOne(wrapper);
    }

    @Override
    public PromotionParticipants findByPromotionIdAndUserId(Long promotionId, Long userId) {
        LambdaQueryWrapper<PromotionParticipants> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PromotionParticipants::getPromotionId, promotionId)
               .eq(PromotionParticipants::getUserId, userId)
               .eq(PromotionParticipants::getIsDelete, 0);
        return promotionParticipantsMapper.selectOne(wrapper);
    }

    @Override
    public PromotionParticipants findByOrderId(Long orderId) {
        LambdaQueryWrapper<PromotionParticipants> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PromotionParticipants::getOrderId, orderId)
               .eq(PromotionParticipants::getIsDelete, 0);
        return promotionParticipantsMapper.selectOne(wrapper);
    }

    @Override
    public List<PromotionParticipants> findUsedParticipants(Long promotionId) {
        LambdaQueryWrapper<PromotionParticipants> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PromotionParticipants::getPromotionId, promotionId)
               .isNotNull(PromotionParticipants::getUsedAt)
               .eq(PromotionParticipants::getIsDelete, 0)
               .orderByDesc(PromotionParticipants::getUsedAt);
        return promotionParticipantsMapper.selectList(wrapper);
    }

    @Override
    public List<PromotionParticipants> findUnusedParticipants(Long promotionId) {
        LambdaQueryWrapper<PromotionParticipants> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PromotionParticipants::getPromotionId, promotionId)
               .isNull(PromotionParticipants::getUsedAt)
               .eq(PromotionParticipants::getIsDelete, 0)
               .orderByDesc(PromotionParticipants::getCreateTime);
        return promotionParticipantsMapper.selectList(wrapper);
    }

    @Override
    public List<PromotionParticipants> findByTimeRange(LocalDateTime startTime, LocalDateTime endTime) {
        LambdaQueryWrapper<PromotionParticipants> wrapper = new LambdaQueryWrapper<>();
        wrapper.ge(PromotionParticipants::getCreateTime, startTime)
               .le(PromotionParticipants::getCreateTime, endTime)
               .eq(PromotionParticipants::getIsDelete, 0)
               .orderByDesc(PromotionParticipants::getCreateTime);
        return promotionParticipantsMapper.selectList(wrapper);
    }

    @Override
    public PageResult<PromotionParticipants> findPage(PageRequest pageRequest) {
        LambdaQueryWrapper<PromotionParticipants> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PromotionParticipants::getIsDelete, 0)
               .orderByDesc(PromotionParticipants::getCreateTime);
        return promotionParticipantsMapper.selectPage(pageRequest, wrapper);
    }

    @Override
    public PromotionParticipants save(PromotionParticipants participant) {
        promotionParticipantsMapper.insert(participant);
        return participant;
    }

    @Override
    public PromotionParticipants update(PromotionParticipants participant) {
        promotionParticipantsMapper.updateById(participant);
        return participant;
    }

    @Override
    public boolean deleteById(Long id) {
        PromotionParticipants participant = new PromotionParticipants();
        participant.setId(id);
        participant.setIsDelete(1);
        return promotionParticipantsMapper.updateById(participant) > 0;
    }

    @Override
    public boolean useCoupon(Long id, Long orderId) {
        PromotionParticipants participant = new PromotionParticipants();
        participant.setId(id);
        participant.setOrderId(orderId);
        participant.setUsedAt(LocalDateTime.now());
        return promotionParticipantsMapper.updateById(participant) > 0;
    }

    @Override
    public boolean hasParticipated(Long promotionId, Long userId) {
        return findByPromotionIdAndUserId(promotionId, userId) != null;
    }

    @Override
    public boolean isCouponUsed(String couponCode) {
        PromotionParticipants participant = findByCouponCode(couponCode);
        return participant != null && participant.getUsedAt() != null;
    }

    @Override
    public long count() {
        LambdaQueryWrapper<PromotionParticipants> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PromotionParticipants::getIsDelete, 0);
        return promotionParticipantsMapper.selectCount(wrapper);
    }

    @Override
    public long countByPromotionId(Long promotionId) {
        LambdaQueryWrapper<PromotionParticipants> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PromotionParticipants::getPromotionId, promotionId)
               .eq(PromotionParticipants::getIsDelete, 0);
        return promotionParticipantsMapper.selectCount(wrapper);
    }

    @Override
    public long countByUserId(Long userId) {
        LambdaQueryWrapper<PromotionParticipants> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PromotionParticipants::getUserId, userId)
               .eq(PromotionParticipants::getIsDelete, 0);
        return promotionParticipantsMapper.selectCount(wrapper);
    }

    @Override
    public long countUsedByPromotionId(Long promotionId) {
        LambdaQueryWrapper<PromotionParticipants> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PromotionParticipants::getPromotionId, promotionId)
               .isNotNull(PromotionParticipants::getUsedAt)
               .eq(PromotionParticipants::getIsDelete, 0);
        return promotionParticipantsMapper.selectCount(wrapper);
    }
}
