package com.shootsunrise.core.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 管理员角色枚举
 * @author lyj
 * @since 2025-07-27
 */
@Getter
@AllArgsConstructor
public enum AdminRoleEnum {
    
    /**
     * 超级管理员
     */
    SUPER_ADMIN(0, "超级管理员"),
    
    /**
     * 系统管理员
     */
    SYSTEM_ADMIN(1, "系统管理员");
    
    @JsonValue
    @EnumValue
    private final int code;
    private final String msg;
}
