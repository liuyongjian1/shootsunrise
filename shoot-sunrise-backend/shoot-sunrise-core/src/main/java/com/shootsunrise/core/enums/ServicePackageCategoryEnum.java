package com.shootsunrise.core.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 服务套餐类型枚举
 * @author lyj
 * @since 2025-07-27
 */
@Getter
@AllArgsConstructor
public enum ServicePackageCategoryEnum {
    
    /**
     * 婚纱摄影
     */
    WEDDING(0, "婚纱摄影"),
    
    /**
     * 人像摄影
     */
    PORTRAIT(1, "人像摄影"),
    
    /**
     * 商业摄影
     */
    COMMERCIAL(2, "商业摄影"),
    
    /**
     * 活动摄影
     */
    EVENT(3, "活动摄影"),
    
    /**
     * 其他
     */
    OTHER(4, "其他");
    
    @JsonValue
    @EnumValue
    private final int code;
    private final String msg;
}
