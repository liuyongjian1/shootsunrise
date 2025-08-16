package com.shootsunrise.core.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 预约状态枚举
 * @author lyj
 * @since 2025-07-27
 */
@Getter
@AllArgsConstructor
public enum BookingStatusEnum {
    
    /**
     * 待确认
     */
    PENDING(0, "待确认"),
    
    /**
     * 已确认
     */
    CONFIRMED(1, "已确认"),
    
    /**
     * 已拒绝
     */
    REJECTED(2, "已拒绝"),
    
    /**
     * 已取消
     */
    CANCELLED(3, "已取消"),
    
    /**
     * 已完成
     */
    COMPLETED(4, "已完成");
    
    @JsonValue
    @EnumValue
    private final int code;
    private final String msg;
}
