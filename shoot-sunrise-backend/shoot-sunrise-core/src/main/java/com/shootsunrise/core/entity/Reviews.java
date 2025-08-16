package com.shootsunrise.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.shootsunrise.core.common.mybatis.core.dataobject.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 评价表
 * @author lyj
 * @since 2025-07-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("reviews")
public class Reviews extends BaseEntity {

    /**
     * 评价ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 预约ID - 关联bookings.id
     */
    private Long bookingId;

    /**
     * 客户ID - 关联users.id
     */
    private Long clientId;

    /**
     * 摄影师ID - 关联users.id
     */
    private Long photographerId;

    /**
     * 评分(1.0-5.0)
     */
    private BigDecimal rating;

    /**
     * 评价内容
     */
    private String content;

    /**
     * 评价图片 JSON格式
     */
    private String images;

    /**
     * 是否匿名
     */
    private Boolean isAnonymous;

    /**
     * 是否显示
     */
    private Boolean isVisible;

    /**
     * 点赞数
     */
    private Integer likesCount;

    /**
     * 摄影师回复
     */
    private String reply;

    /**
     * 回复时间
     */
    private java.time.LocalDateTime repliedAt;
}
