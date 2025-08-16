package com.shootsunrise.core.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 作品状态枚举
 * @author lyj
 * @since 2025-07-27
 */
@Getter
@AllArgsConstructor
public enum PortfolioStatusEnum {
    
    /**
     * 草稿
     */
    DRAFT(0, "草稿"),
    
    /**
     * 已发布
     */
    PUBLISHED(1, "已发布"),
    
    /**
     * 已隐藏
     */
    HIDDEN(2, "已隐藏");
    
    @JsonValue
    @EnumValue
    private final int code;
    private final String msg;
}
