package com.shootsunrise.core.repository;

import com.shootsunrise.common.api.request.PageRequest;
import com.shootsunrise.common.api.response.PageResult;
import com.shootsunrise.core.entity.AuditLogs;
import com.shootsunrise.core.enums.AuditTypeEnum;
import com.shootsunrise.core.enums.AuditStatusEnum;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 内容审核日志表 Repository 接口
 * @author lyj
 * @since 2025-07-27
 */
public interface AuditLogsRepository {

    /**
     * 根据ID查询审核日志
     * @param id 审核日志ID
     * @return 审核日志信息
     */
    AuditLogs findById(Long id);

    /**
     * 根据审核类型查询审核日志列表
     * @param auditType 审核类型
     * @return 审核日志列表
     */
    List<AuditLogs> findByAuditType(AuditTypeEnum auditType);

    /**
     * 根据目标ID和类型查询审核日志列表
     * @param targetId 目标ID
     * @param targetType 目标类型
     * @return 审核日志列表
     */
    List<AuditLogs> findByTargetIdAndType(Long targetId, String targetType);

    /**
     * 根据审核状态查询审核日志列表
     * @param auditStatus 审核状态
     * @return 审核日志列表
     */
    List<AuditLogs> findByAuditStatus(AuditStatusEnum auditStatus);

    /**
     * 根据审核员ID查询审核日志列表
     * @param auditorId 审核员ID
     * @return 审核日志列表
     */
    List<AuditLogs> findByAuditorId(Long auditorId);

    /**
     * 根据时间范围查询审核日志列表
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 审核日志列表
     */
    List<AuditLogs> findByTimeRange(LocalDateTime startTime, LocalDateTime endTime);

    /**
     * 查询待审核的日志列表
     * @return 待审核日志列表
     */
    List<AuditLogs> findPendingAudits();

    /**
     * 分页查询审核日志列表
     * @param pageRequest 分页请求
     * @return 分页结果
     */
    PageResult<AuditLogs> findPage(PageRequest pageRequest);

    /**
     * 保存审核日志
     * @param auditLog 审核日志信息
     * @return 保存后的审核日志信息
     */
    AuditLogs save(AuditLogs auditLog);

    /**
     * 更新审核日志
     * @param auditLog 审核日志信息
     * @return 更新后的审核日志信息
     */
    AuditLogs update(AuditLogs auditLog);

    /**
     * 删除审核日志
     * @param id 审核日志ID
     * @return 是否删除成功
     */
    boolean deleteById(Long id);

    /**
     * 更新审核状态
     * @param id 审核日志ID
     * @param auditStatus 审核状态
     * @param auditorId 审核员ID
     * @param auditComment 审核意见
     * @return 是否更新成功
     */
    boolean updateAuditStatus(Long id, AuditStatusEnum auditStatus, Long auditorId, String auditComment);

    /**
     * 批量更新审核状态
     * @param ids 审核日志ID列表
     * @param auditStatus 审核状态
     * @param auditorId 审核员ID
     * @param auditComment 审核意见
     * @return 更新成功的数量
     */
    int batchUpdateAuditStatus(List<Long> ids, AuditStatusEnum auditStatus, Long auditorId, String auditComment);

    /**
     * 统计审核日志总数
     * @return 审核日志总数
     */
    long count();

    /**
     * 统计指定状态的审核日志数量
     * @param auditStatus 审核状态
     * @return 审核日志数量
     */
    long countByAuditStatus(AuditStatusEnum auditStatus);

    /**
     * 统计审核员的审核数量
     * @param auditorId 审核员ID
     * @return 审核数量
     */
    long countByAuditorId(Long auditorId);

    /**
     * 统计指定类型的审核日志数量
     * @param auditType 审核类型
     * @return 审核日志数量
     */
    long countByAuditType(AuditTypeEnum auditType);

    /**
     * 统计待审核的数量
     * @return 待审核数量
     */
    long countPendingAudits();
}
