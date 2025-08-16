package com.shootsunrise.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.shootsunrise.core.common.mybatis.core.dataobject.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 营销活动统计表
 * @author lyj
 * @since 2025-07-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("promotion_statistics")
public class PromotionStatistics extends BaseEntity {

    /**
     * 统计ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 活动ID - 关联promotions.id
     */
    private Long promotionId;

    /**
     * 统计日期
     */
    private LocalDate statDate;

    /**
     * 浏览次数
     */
    private Integer viewCount;

    /**
     * 分享次数
     */
    private Integer shareCount;

    /**
     * 参与人数
     */
    private Integer participantCount;

    /**
     * 使用次数
     */
    private Integer usageCount;

    /**
     * 转化金额
     */
    private BigDecimal conversionAmount;

    /**
     * 转化率
     */
    private BigDecimal conversionRate;
}
