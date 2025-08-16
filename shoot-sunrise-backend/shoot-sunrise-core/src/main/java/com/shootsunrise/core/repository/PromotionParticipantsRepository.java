package com.shootsunrise.core.repository;

import com.shootsunrise.common.api.request.PageRequest;
import com.shootsunrise.common.api.response.PageResult;
import com.shootsunrise.core.entity.PromotionParticipants;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 营销活动参与记录表 Repository 接口
 * @author lyj
 * @since 2025-07-27
 */
public interface PromotionParticipantsRepository {

    /**
     * 根据ID查询参与记录
     * @param id 参与记录ID
     * @return 参与记录信息
     */
    PromotionParticipants findById(Long id);

    /**
     * 根据活动ID查询参与记录列表
     * @param promotionId 活动ID
     * @return 参与记录列表
     */
    List<PromotionParticipants> findByPromotionId(Long promotionId);

    /**
     * 根据用户ID查询参与记录列表
     * @param userId 用户ID
     * @return 参与记录列表
     */
    List<PromotionParticipants> findByUserId(Long userId);

    /**
     * 根据优惠券码查询参与记录
     * @param couponCode 优惠券码
     * @return 参与记录信息
     */
    PromotionParticipants findByCouponCode(String couponCode);

    /**
     * 查询用户在指定活动的参与记录
     * @param promotionId 活动ID
     * @param userId 用户ID
     * @return 参与记录信息
     */
    PromotionParticipants findByPromotionIdAndUserId(Long promotionId, Long userId);

    /**
     * 根据订单ID查询参与记录
     * @param orderId 订单ID
     * @return 参与记录信息
     */
    PromotionParticipants findByOrderId(Long orderId);

    /**
     * 查询已使用的参与记录列表
     * @param promotionId 活动ID
     * @return 已使用的参与记录列表
     */
    List<PromotionParticipants> findUsedParticipants(Long promotionId);

    /**
     * 查询未使用的参与记录列表
     * @param promotionId 活动ID
     * @return 未使用的参与记录列表
     */
    List<PromotionParticipants> findUnusedParticipants(Long promotionId);

    /**
     * 根据时间范围查询参与记录列表
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 参与记录列表
     */
    List<PromotionParticipants> findByTimeRange(LocalDateTime startTime, LocalDateTime endTime);

    /**
     * 分页查询参与记录列表
     * @param pageRequest 分页请求
     * @return 分页结果
     */
    PageResult<PromotionParticipants> findPage(PageRequest pageRequest);

    /**
     * 保存参与记录
     * @param participant 参与记录信息
     * @return 保存后的参与记录信息
     */
    PromotionParticipants save(PromotionParticipants participant);

    /**
     * 更新参与记录
     * @param participant 参与记录信息
     * @return 更新后的参与记录信息
     */
    PromotionParticipants update(PromotionParticipants participant);

    /**
     * 删除参与记录
     * @param id 参与记录ID
     * @return 是否删除成功
     */
    boolean deleteById(Long id);

    /**
     * 使用优惠券
     * @param id 参与记录ID
     * @param orderId 订单ID
     * @return 是否使用成功
     */
    boolean useCoupon(Long id, Long orderId);

    /**
     * 检查用户是否已参与活动
     * @param promotionId 活动ID
     * @param userId 用户ID
     * @return 是否已参与
     */
    boolean hasParticipated(Long promotionId, Long userId);

    /**
     * 检查优惠券是否已使用
     * @param couponCode 优惠券码
     * @return 是否已使用
     */
    boolean isCouponUsed(String couponCode);

    /**
     * 统计参与记录总数
     * @return 参与记录总数
     */
    long count();

    /**
     * 统计活动的参与人数
     * @param promotionId 活动ID
     * @return 参与人数
     */
    long countByPromotionId(Long promotionId);

    /**
     * 统计用户的参与次数
     * @param userId 用户ID
     * @return 参与次数
     */
    long countByUserId(Long userId);

    /**
     * 统计活动的使用次数
     * @param promotionId 活动ID
     * @return 使用次数
     */
    long countUsedByPromotionId(Long promotionId);
}
