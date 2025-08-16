package com.shootsunrise.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.shootsunrise.core.common.mybatis.core.dataobject.BaseEntity;
import com.shootsunrise.core.enums.BookingStatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * 预约表
 * @author lyj
 * @since 2025-07-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("bookings")
public class Bookings extends BaseEntity {

    /**
     * 预约ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 预约编号
     */
    private String bookingNo;

    /**
     * 客户ID - 关联users.id
     */
    private Long clientId;

    /**
     * 摄影师ID - 关联users.id
     */
    private Long photographerId;

    /**
     * 套餐ID - 关联service_packages.id
     */
    private Long packageId;

    /**
     * 拍摄日期
     */
    private LocalDate shootDate;

    /**
     * 拍摄时间
     */
    private LocalTime shootTime;

    /**
     * 拍摄地点
     */
    private String location;

    /**
     * 拍摄主题
     */
    private String theme;

    /**
     * 特殊要求
     */
    private String requirements;

    /**
     * 联系人姓名
     */
    private String contactName;

    /**
     * 联系电话
     */
    private String contactPhone;

    /**
     * 总金额
     */
    private BigDecimal totalAmount;

    /**
     * 定金金额
     */
    private BigDecimal depositAmount;

    /**
     * 尾款金额
     */
    private BigDecimal finalAmount;

    /**
     * 预约状态：0-PENDING，1-CONFIRMED，2-REJECTED，3-CANCELLED，4-COMPLETED
     */
    private BookingStatusEnum status;

    /**
     * 确认时间
     */
    private LocalDateTime confirmedAt;

    /**
     * 取消时间
     */
    private LocalDateTime cancelledAt;

    /**
     * 取消原因
     */
    private String cancelReason;

    /**
     * 备注
     */
    private String remarks;
}
