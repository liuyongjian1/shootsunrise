package com.shootsunrise.core.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 通知优先级枚举
 * @author lyj
 * @since 2025-07-27
 */
@Getter
@AllArgsConstructor
public enum NotificationPriorityEnum {
    
    /**
     * 低优先级
     */
    LOW("LOW", "低"),
    
    /**
     * 普通优先级
     */
    NORMAL("NORMAL", "普通"),
    
    /**
     * 高优先级
     */
    HIGH("HIGH", "高"),
    
    /**
     * 紧急
     */
    URGENT("URGENT", "紧急");
    
    @JsonValue
    @EnumValue
    private final String code;
    private final String msg;
}
