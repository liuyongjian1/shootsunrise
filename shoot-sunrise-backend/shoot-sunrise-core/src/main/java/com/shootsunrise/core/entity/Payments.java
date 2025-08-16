package com.shootsunrise.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.shootsunrise.core.common.mybatis.core.dataobject.BaseEntity;
import com.shootsunrise.core.enums.PaymentTypeEnum;
import com.shootsunrise.core.enums.PaymentMethodEnum;
import com.shootsunrise.core.enums.PaymentStatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 支付记录表
 * @author lyj
 * @since 2025-07-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("payments")
public class Payments extends BaseEntity {

    /**
     * 支付ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 支付编号
     */
    private String paymentNo;

    /**
     * 预约ID - 关联bookings.id
     */
    private Long bookingId;

    /**
     * 用户ID - 关联users.id
     */
    private Long userId;

    /**
     * 支付类型：0-DEPOSIT（定金），1-FINAL（尾款），2-FULL（全款）
     */
    private PaymentTypeEnum paymentType;

    /**
     * 支付方式：0-WECHAT（微信），1-ALIPAY（支付宝），2-BANK（银行卡）
     */
    private PaymentMethodEnum paymentMethod;

    /**
     * 支付金额
     */
    private BigDecimal amount;

    /**
     * 第三方交易号
     */
    private String transactionId;

    /**
     * 支付状态：0-PENDING（待支付），1-SUCCESS（成功），2-FAILED（失败），3-REFUNDED（已退款）
     */
    private PaymentStatusEnum status;

    /**
     * 支付时间
     */
    private LocalDateTime paidAt;

    /**
     * 退款金额
     */
    private BigDecimal refundAmount;

    /**
     * 退款原因
     */
    private String refundReason;

    /**
     * 退款时间
     */
    private LocalDateTime refundedAt;
}
