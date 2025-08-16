package com.shootsunrise.core.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shootsunrise.common.api.request.PageRequest;
import com.shootsunrise.common.api.response.PageResult;
import com.shootsunrise.core.entity.OperationLogs;
import com.shootsunrise.core.mapper.OperationLogsMapper;
import com.shootsunrise.core.repository.OperationLogsRepository;
import org.springframework.stereotype.Repository;

import jakarta.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 操作日志表 Repository 实现类
 * @author lyj
 * @since 2025-07-27
 */
@Repository
public class OperationLogsRepositoryImpl implements OperationLogsRepository {

    @Resource
    private OperationLogsMapper operationLogsMapper;

    @Override
    public OperationLogs findById(Long id) {
        return operationLogsMapper.selectById(id);
    }

    @Override
    public List<OperationLogs> findByUserId(Long userId) {
        LambdaQueryWrapper<OperationLogs> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OperationLogs::getUserId, userId)
               .eq(OperationLogs::getIsDelete, 0)
               .orderByDesc(OperationLogs::getOperationTime);
        return operationLogsMapper.selectList(wrapper);
    }

    @Override
    public List<OperationLogs> findByOperationType(String operationType) {
        LambdaQueryWrapper<OperationLogs> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OperationLogs::getOperationType, operationType)
               .eq(OperationLogs::getIsDelete, 0)
               .orderByDesc(OperationLogs::getOperationTime);
        return operationLogsMapper.selectList(wrapper);
    }

    @Override
    public List<OperationLogs> findByRequestMethod(String requestMethod) {
        LambdaQueryWrapper<OperationLogs> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OperationLogs::getRequestMethod, requestMethod)
               .eq(OperationLogs::getIsDelete, 0)
               .orderByDesc(OperationLogs::getOperationTime);
        return operationLogsMapper.selectList(wrapper);
    }

    @Override
    public List<OperationLogs> findByStatus(String status) {
        LambdaQueryWrapper<OperationLogs> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OperationLogs::getStatus, status)
               .eq(OperationLogs::getIsDelete, 0)
               .orderByDesc(OperationLogs::getOperationTime);
        return operationLogsMapper.selectList(wrapper);
    }

    @Override
    public List<OperationLogs> findByOperationIp(String operationIp) {
        LambdaQueryWrapper<OperationLogs> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OperationLogs::getOperationIp, operationIp)
               .eq(OperationLogs::getIsDelete, 0)
               .orderByDesc(OperationLogs::getOperationTime);
        return operationLogsMapper.selectList(wrapper);
    }

    @Override
    public List<OperationLogs> findByTimeRange(LocalDateTime startTime, LocalDateTime endTime) {
        LambdaQueryWrapper<OperationLogs> wrapper = new LambdaQueryWrapper<>();
        wrapper.ge(OperationLogs::getOperationTime, startTime)
               .le(OperationLogs::getOperationTime, endTime)
               .eq(OperationLogs::getIsDelete, 0)
               .orderByDesc(OperationLogs::getOperationTime);
        return operationLogsMapper.selectList(wrapper);
    }

    @Override
    public List<OperationLogs> findFailedOperations() {
        LambdaQueryWrapper<OperationLogs> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OperationLogs::getStatus, "FAILED")
               .eq(OperationLogs::getIsDelete, 0)
               .orderByDesc(OperationLogs::getOperationTime);
        return operationLogsMapper.selectList(wrapper);
    }

    @Override
    public List<OperationLogs> findSlowOperations(Long minExecutionTime) {
        LambdaQueryWrapper<OperationLogs> wrapper = new LambdaQueryWrapper<>();
        wrapper.ge(OperationLogs::getExecutionTime, minExecutionTime)
               .eq(OperationLogs::getIsDelete, 0)
               .orderByDesc(OperationLogs::getExecutionTime);
        return operationLogsMapper.selectList(wrapper);
    }

    @Override
    public PageResult<OperationLogs> findPage(PageRequest pageRequest) {
        LambdaQueryWrapper<OperationLogs> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OperationLogs::getIsDelete, 0)
               .orderByDesc(OperationLogs::getOperationTime);
        return operationLogsMapper.selectPage(pageRequest, wrapper);
    }

    @Override
    public OperationLogs save(OperationLogs operationLog) {
        operationLogsMapper.insert(operationLog);
        return operationLog;
    }

    @Override
    public OperationLogs update(OperationLogs operationLog) {
        operationLogsMapper.updateById(operationLog);
        return operationLog;
    }

    @Override
    public boolean deleteById(Long id) {
        OperationLogs operationLog = new OperationLogs();
        operationLog.setId(id);
        operationLog.setIsDelete(1);
        return operationLogsMapper.updateById(operationLog) > 0;
    }

    @Override
    public int deleteExpiredLogs(LocalDateTime expiredTime) {
        LambdaQueryWrapper<OperationLogs> wrapper = new LambdaQueryWrapper<>();
        wrapper.lt(OperationLogs::getOperationTime, expiredTime)
               .eq(OperationLogs::getIsDelete, 0);
        
        List<OperationLogs> expiredLogs = operationLogsMapper.selectList(wrapper);
        int count = 0;
        for (OperationLogs log : expiredLogs) {
            if (deleteById(log.getId())) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int cleanupOldLogs(int days) {
        LocalDateTime expiredTime = LocalDateTime.now().minusDays(days);
        return deleteExpiredLogs(expiredTime);
    }

    @Override
    public long count() {
        LambdaQueryWrapper<OperationLogs> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OperationLogs::getIsDelete, 0);
        return operationLogsMapper.selectCount(wrapper);
    }

    @Override
    public long countByUserId(Long userId) {
        LambdaQueryWrapper<OperationLogs> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OperationLogs::getUserId, userId)
               .eq(OperationLogs::getIsDelete, 0);
        return operationLogsMapper.selectCount(wrapper);
    }

    @Override
    public long countByStatus(String status) {
        LambdaQueryWrapper<OperationLogs> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OperationLogs::getStatus, status)
               .eq(OperationLogs::getIsDelete, 0);
        return operationLogsMapper.selectCount(wrapper);
    }

    @Override
    public long countByOperationType(String operationType) {
        LambdaQueryWrapper<OperationLogs> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OperationLogs::getOperationType, operationType)
               .eq(OperationLogs::getIsDelete, 0);
        return operationLogsMapper.selectCount(wrapper);
    }

    @Override
    public long countFailedOperations() {
        return countByStatus("FAILED");
    }

    @Override
    public Double calculateAverageExecutionTime(LocalDateTime startTime, LocalDateTime endTime) {
        List<OperationLogs> logs = findByTimeRange(startTime, endTime);
        if (logs.isEmpty()) {
            return 0.0;
        }
        
        long totalExecutionTime = logs.stream()
                .mapToLong(OperationLogs::getExecutionTime)
                .sum();
        
        return (double) totalExecutionTime / logs.size();
    }
}
