package com.shootsunrise.core.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 支付类型枚举
 * @author lyj
 * @since 2025-07-27
 */
@Getter
@AllArgsConstructor
public enum PaymentTypeEnum {
    
    /**
     * 定金
     */
    DEPOSIT(0, "定金"),
    
    /**
     * 尾款
     */
    FINAL(1, "尾款"),
    
    /**
     * 全款
     */
    FULL(2, "全款");
    
    @JsonValue
    @EnumValue
    private final int code;
    private final String msg;
}
