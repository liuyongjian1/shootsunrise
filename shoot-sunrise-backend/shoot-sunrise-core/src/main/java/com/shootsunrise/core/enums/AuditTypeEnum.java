package com.shootsunrise.core.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 审核类型枚举
 * @author lyj
 * @since 2025-07-27
 */
@Getter
@AllArgsConstructor
public enum AuditTypeEnum {
    
    /**
     * 作品审核
     */
    PORTFOLIO("PORTFOLIO", "作品"),
    
    /**
     * 摄影师审核
     */
    PHOTOGRAPHER("PHOTOGRAPHER", "摄影师"),
    
    /**
     * 营销活动审核
     */
    PROMOTION("PROMOTION", "营销活动");
    
    @JsonValue
    @EnumValue
    private final String code;
    private final String msg;
}
