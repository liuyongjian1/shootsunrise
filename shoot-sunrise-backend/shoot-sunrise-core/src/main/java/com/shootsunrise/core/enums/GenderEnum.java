package com.shootsunrise.core.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户性别枚举
 * @author lyj
 * @since 2025-07-27
 */
@Getter
@AllArgsConstructor
public enum GenderEnum {
    
    /**
     * 未知
     */
    UNKNOWN(0, "未知"),
    
    /**
     * 男性
     */
    MALE(1, "男"),
    
    /**
     * 女性
     */
    FEMALE(2, "女");
    
    @JsonValue
    @EnumValue
    private final int code;
    private final String msg;
}
