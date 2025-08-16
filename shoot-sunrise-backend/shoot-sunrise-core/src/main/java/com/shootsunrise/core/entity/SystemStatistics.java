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
 * 系统统计数据表
 * @author lyj
 * @since 2025-07-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("system_statistics")
public class SystemStatistics extends BaseEntity {

    /**
     * 统计ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 统计日期
     */
    private LocalDate statDate;

    /**
     * 统计类型：DAILY-日统计，WEEKLY-周统计，MONTHLY-月统计
     */
    private String statType;

    /**
     * 用户总数
     */
    private Integer userCount;

    /**
     * 新增用户数
     */
    private Integer newUserCount;

    /**
     * 摄影师总数
     */
    private Integer photographerCount;

    /**
     * 新增摄影师数
     */
    private Integer newPhotographerCount;

    /**
     * 作品总数
     */
    private Integer portfolioCount;

    /**
     * 新增作品数
     */
    private Integer newPortfolioCount;

    /**
     * 预约总数
     */
    private Integer bookingCount;

    /**
     * 新增预约数
     */
    private Integer newBookingCount;

    /**
     * 交易总金额
     */
    private BigDecimal totalRevenue;

    /**
     * 新增交易金额
     */
    private BigDecimal newRevenue;

    /**
     * 活跃用户数
     */
    private Integer activeUserCount;
}
