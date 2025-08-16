package com.shootsunrise.core.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shootsunrise.common.api.request.PageRequest;
import com.shootsunrise.common.api.response.PageResult;
import com.shootsunrise.core.entity.AuditLogs;
import com.shootsunrise.core.enums.AuditStatusEnum;
import com.shootsunrise.core.enums.AuditTypeEnum;
import com.shootsunrise.core.mapper.AuditLogsMapper;
import com.shootsunrise.core.repository.AuditLogsRepository;
import org.springframework.stereotype.Repository;

import jakarta.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;



/**
 * 内容审核日志表 Repository 实现类
 * @author lyj
 * @since 2025-07-27
 */
@Repository
public class AuditLogsRepositoryImpl implements AuditLogsRepository {

    @Resource
    private AuditLogsMapper auditLogsMapper;

    @Override
    public AuditLogs findById(Long id) {
        return auditLogsMapper.selectById(id);
    }

    @Override
    public List<AuditLogs> findByAuditType(AuditTypeEnum auditType) {
        LambdaQueryWrapper<AuditLogs> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AuditLogs::getAuditType, auditType)
               .eq(AuditLogs::getIsDelete, 0)
               .orderByDesc(AuditLogs::getCreateTime);
        return auditLogsMapper.selectList(wrapper);
    }

    @Override
    public List<AuditLogs> findByTargetIdAndType(Long targetId, String targetType) {
        LambdaQueryWrapper<AuditLogs> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AuditLogs::getTargetId, targetId)
               .eq(AuditLogs::getTargetType, targetType)
               .eq(AuditLogs::getIsDelete, 0)
               .orderByDesc(AuditLogs::getCreateTime);
        return auditLogsMapper.selectList(wrapper);
    }

    @Override
    public List<AuditLogs> findByAuditStatus(AuditStatusEnum auditStatus) {
        LambdaQueryWrapper<AuditLogs> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AuditLogs::getAuditStatus, auditStatus)
               .eq(AuditLogs::getIsDelete, 0)
               .orderByDesc(AuditLogs::getCreateTime);
        return auditLogsMapper.selectList(wrapper);
    }

    @Override
    public List<AuditLogs> findByAuditorId(Long auditorId) {
        LambdaQueryWrapper<AuditLogs> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AuditLogs::getAuditorId, auditorId)
               .eq(AuditLogs::getIsDelete, 0)
               .orderByDesc(AuditLogs::getCreateTime);
        return auditLogsMapper.selectList(wrapper);
    }

    @Override
    public List<AuditLogs> findByTimeRange(LocalDateTime startTime, LocalDateTime endTime) {
        LambdaQueryWrapper<AuditLogs> wrapper = new LambdaQueryWrapper<>();
        wrapper.ge(AuditLogs::getCreateTime, startTime)
               .le(AuditLogs::getCreateTime, endTime)
               .eq(AuditLogs::getIsDelete, 0)
               .orderByDesc(AuditLogs::getCreateTime);
        return auditLogsMapper.selectList(wrapper);
    }

    @Override
    public List<AuditLogs> findPendingAudits() {
        LambdaQueryWrapper<AuditLogs> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AuditLogs::getAuditStatus, AuditStatusEnum.PENDING)
               .eq(AuditLogs::getIsDelete, 0)
               .orderByAsc(AuditLogs::getCreateTime);
        return auditLogsMapper.selectList(wrapper);
    }

    @Override
    public PageResult<AuditLogs> findPage(PageRequest pageRequest) {
        LambdaQueryWrapper<AuditLogs> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AuditLogs::getIsDelete, 0)
               .orderByDesc(AuditLogs::getCreateTime);
        return auditLogsMapper.selectPage(pageRequest, wrapper);
    }

    @Override
    public AuditLogs save(AuditLogs auditLog) {
        auditLogsMapper.insert(auditLog);
        return auditLog;
    }

    @Override
    public AuditLogs update(AuditLogs auditLog) {
        auditLogsMapper.updateById(auditLog);
        return auditLog;
    }

    @Override
    public boolean deleteById(Long id) {
        AuditLogs auditLog = new AuditLogs();
        auditLog.setId(id);
        auditLog.setIsDelete(1);
        return auditLogsMapper.updateById(auditLog) > 0;
    }

    @Override
    public boolean updateAuditStatus(Long id, AuditStatusEnum auditStatus, Long auditorId, String auditComment) {
        AuditLogs auditLog = new AuditLogs();
        auditLog.setId(id);
        auditLog.setAuditStatus(auditStatus);
        auditLog.setAuditorId(auditorId);
        auditLog.setAuditComment(auditComment);
        auditLog.setAuditTime(LocalDateTime.now());
        
        if (AuditStatusEnum.REJECTED.equals(auditStatus)) {
            auditLog.setRejectReason(auditComment);
        }
        
        return auditLogsMapper.updateById(auditLog) > 0;
    }

    @Override
    public int batchUpdateAuditStatus(List<Long> ids, AuditStatusEnum auditStatus, Long auditorId, String auditComment) {
        if (ids == null || ids.isEmpty()) {
            return 0;
        }
        
        int count = 0;
        for (Long id : ids) {
            if (updateAuditStatus(id, auditStatus, auditorId, auditComment)) {
                count++;
            }
        }
        return count;
    }

    @Override
    public long count() {
        LambdaQueryWrapper<AuditLogs> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AuditLogs::getIsDelete, 0);
        return auditLogsMapper.selectCount(wrapper);
    }

    @Override
    public long countByAuditStatus(AuditStatusEnum auditStatus) {
        LambdaQueryWrapper<AuditLogs> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AuditLogs::getAuditStatus, auditStatus)
               .eq(AuditLogs::getIsDelete, 0);
        return auditLogsMapper.selectCount(wrapper);
    }

    @Override
    public long countByAuditorId(Long auditorId) {
        LambdaQueryWrapper<AuditLogs> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AuditLogs::getAuditorId, auditorId)
               .eq(AuditLogs::getIsDelete, 0);
        return auditLogsMapper.selectCount(wrapper);
    }

    @Override
    public long countByAuditType(AuditTypeEnum auditType) {
        LambdaQueryWrapper<AuditLogs> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AuditLogs::getAuditType, auditType)
               .eq(AuditLogs::getIsDelete, 0);
        return auditLogsMapper.selectCount(wrapper);
    }

    @Override
    public long countPendingAudits() {
        return countByAuditStatus(AuditStatusEnum.PENDING);
    }
}
