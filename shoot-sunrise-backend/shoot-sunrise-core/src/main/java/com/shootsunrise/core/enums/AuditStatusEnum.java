package com.shootsunrise.core.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 审核状态枚举
 * @author lyj
 * @since 2025-07-27
 */
@Getter
@AllArgsConstructor
public enum AuditStatusEnum {
    
    /**
     * 待审核
     */
    PENDING("PENDING", "待审核"),
    
    /**
     * 已通过
     */
    APPROVED("APPROVED", "已通过"),
    
    /**
     * 已拒绝
     */
    REJECTED("REJECTED", "已拒绝");
    
    @JsonValue
    @EnumValue
    private final String code;
    private final String msg;
}
