package com.shootsunrise.core.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 支付方式枚举
 * @author lyj
 * @since 2025-07-27
 */
@Getter
@AllArgsConstructor
public enum PaymentMethodEnum {
    
    /**
     * 微信支付
     */
    WECHAT(0, "微信支付"),
    
    /**
     * 支付宝
     */
    ALIPAY(1, "支付宝"),
    
    /**
     * 银行卡
     */
    BANK(2, "银行卡");
    
    @JsonValue
    @EnumValue
    private final int code;
    private final String msg;
}
