package com.shootsunrise.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.shootsunrise.core.common.mybatis.core.dataobject.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 营销活动表
 * @author lyj
 * @since 2025-07-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("promotions")
public class Promotions extends BaseEntity {

    /**
     * 活动ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 摄影师ID - 关联users.id
     */
    private Long photographerId;

    /**
     * 活动标题
     */
    private String title;

    /**
     * 活动描述
     */
    private String description;

    /**
     * 活动类型：DISCOUNT-折扣，FULL_REDUCTION-满减，GIFT-赠品，LIMITED_TIME-限时
     */
    private String type;

    /**
     * 折扣值
     */
    private BigDecimal discountValue;

    /**
     * 折扣类型：PERCENTAGE-百分比，AMOUNT-金额
     */
    private String discountType;

    /**
     * 最低消费金额
     */
    private BigDecimal minAmount;

    /**
     * 最大优惠金额
     */
    private BigDecimal maxDiscount;

    /**
     * 活动开始时间
     */
    private LocalDateTime startTime;

    /**
     * 活动结束时间
     */
    private LocalDateTime endTime;

    /**
     * 参与人数限制
     */
    private Integer participantLimit;

    /**
     * 已参与人数
     */
    private Integer participantCount;

    /**
     * 目标用户：ALL-所有用户，NEW-新用户，VIP-VIP用户
     */
    private String targetUsers;

    /**
     * 是否激活
     */
    private Boolean isActive;

    /**
     * 活动封面
     */
    private String coverImage;
}
