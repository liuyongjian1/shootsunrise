package com.shootsunrise.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.shootsunrise.core.common.mybatis.core.dataobject.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 系统通知表
 * @author lyj
 * @since 2025-07-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("notifications")
public class Notifications extends BaseEntity {

    /**
     * 通知ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID - 关联users.id
     */
    private Long userId;

    /**
     * 通知类型
     */
    private String type;

    /**
     * 通知标题
     */
    private String title;

    /**
     * 通知内容
     */
    private String content;

    /**
     * 跳转链接
     */
    private String link;

    /**
     * 是否已读
     */
    private Boolean isRead;

    /**
     * 阅读时间
     */
    private LocalDateTime readAt;

    /**
     * 优先级：LOW-低，NORMAL-普通，HIGH-高，URGENT-紧急
     */
    private String priority;

    /**
     * 过期时间
     */
    private LocalDateTime expiredAt;
}
