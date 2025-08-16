package com.shootsunrise.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.shootsunrise.core.common.mybatis.core.dataobject.BaseEntity;
import com.shootsunrise.core.enums.AuditTypeEnum;
import com.shootsunrise.core.enums.AuditStatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 内容审核日志表
 * @author lyj
 * @since 2025-07-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("audit_logs")
public class AuditLogs extends BaseEntity {

    /**
     * 审核日志ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 审核类型：PORTFOLIO-作品，PHOTOGRAPHER-摄影师，PROMOTION-营销活动
     */
    private AuditTypeEnum auditType;

    /**
     * 目标ID
     */
    private Long targetId;

    /**
     * 目标类型
     */
    private String targetType;

    /**
     * 审核状态：PENDING-待审核，APPROVED-已通过，REJECTED-已拒绝
     */
    private AuditStatusEnum auditStatus;

    /**
     * 审核员ID - 关联admin_users.id
     */
    private Long auditorId;

    /**
     * 审核时间
     */
    private LocalDateTime auditTime;

    /**
     * 审核意见
     */
    private String auditComment;

    /**
     * 拒绝原因
     */
    private String rejectReason;

    /**
     * 审核前内容快照 JSON格式
     */
    private String beforeSnapshot;

    /**
     * 审核后内容快照 JSON格式
     */
    private String afterSnapshot;
}
