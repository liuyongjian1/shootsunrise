package com.shootsunrise.core.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户角色枚举
 * @author lyj
 * @since 2025-07-27
 */
@Getter
@AllArgsConstructor
public enum UserRoleEnum {
    
    /**
     * 客户
     */
    CLIENT(0, "客户"),
    
    /**
     * 摄影师
     */
    PHOTOGRAPHER(1, "摄影师");
    
    @JsonValue
    @EnumValue
    private final int code;
    private final String msg;
}
