package com.shootsunrise.core.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 支付状态枚举
 * @author lyj
 * @since 2025-07-27
 */
@Getter
@AllArgsConstructor
public enum PaymentStatusEnum {
    
    /**
     * 待支付
     */
    PENDING(0, "待支付"),
    
    /**
     * 支付成功
     */
    SUCCESS(1, "支付成功"),
    
    /**
     * 支付失败
     */
    FAILED(2, "支付失败"),
    
    /**
     * 已退款
     */
    REFUNDED(3, "已退款");
    
    @JsonValue
    @EnumValue
    private final int code;
    private final String msg;
}
