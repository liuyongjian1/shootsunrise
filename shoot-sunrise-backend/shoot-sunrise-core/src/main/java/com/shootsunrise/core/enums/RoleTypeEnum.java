package com.shootsunrise.core.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 角色类型枚举
 * @author lyj
 * @since 2025-07-27
 */
@Getter
@AllArgsConstructor
public enum RoleTypeEnum {
    
    /**
     * 普通用户
     */
    NORMAL_USER(0, "普通用户"),
    
    /**
     * 摄影师
     */
    PHOTOGRAPHER(1, "摄影师"),
    
    /**
     * 管理员
     */
    ADMIN(2, "管理员");
    
    @JsonValue
    @EnumValue
    private final int code;
    private final String msg;
}
