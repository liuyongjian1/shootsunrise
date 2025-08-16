package com.shootsunrise.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.shootsunrise.core.common.mybatis.core.dataobject.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 营销活动参与记录表
 * @author lyj
 * @since 2025-07-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("promotion_participants")
public class PromotionParticipants extends BaseEntity {

    /**
     * 参与记录ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 活动ID - 关联promotions.id
     */
    private Long promotionId;

    /**
     * 用户ID - 关联users.id
     */
    private Long userId;

    /**
     * 优惠券码
     */
    private String couponCode;

    /**
     * 使用时间
     */
    private LocalDateTime usedAt;

    /**
     * 订单ID - 关联bookings.id
     */
    private Long orderId;
}
