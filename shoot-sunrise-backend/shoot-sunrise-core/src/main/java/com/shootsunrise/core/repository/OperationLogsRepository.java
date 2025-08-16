package com.shootsunrise.core.repository;

import com.shootsunrise.common.api.request.PageRequest;
import com.shootsunrise.common.api.response.PageResult;
import com.shootsunrise.core.entity.OperationLogs;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 操作日志表 Repository 接口
 * @author lyj
 * @since 2025-07-27
 */
public interface OperationLogsRepository {

    /**
     * 根据ID查询操作日志
     * @param id 操作日志ID
     * @return 操作日志信息
     */
    OperationLogs findById(Long id);

    /**
     * 根据用户ID查询操作日志列表
     * @param userId 用户ID
     * @return 操作日志列表
     */
    List<OperationLogs> findByUserId(Long userId);

    /**
     * 根据操作类型查询操作日志列表
     * @param operationType 操作类型
     * @return 操作日志列表
     */
    List<OperationLogs> findByOperationType(String operationType);

    /**
     * 根据请求方法查询操作日志列表
     * @param requestMethod 请求方法
     * @return 操作日志列表
     */
    List<OperationLogs> findByRequestMethod(String requestMethod);

    /**
     * 根据操作状态查询操作日志列表
     * @param status 操作状态
     * @return 操作日志列表
     */
    List<OperationLogs> findByStatus(String status);

    /**
     * 根据IP地址查询操作日志列表
     * @param operationIp 操作IP
     * @return 操作日志列表
     */
    List<OperationLogs> findByOperationIp(String operationIp);

    /**
     * 根据时间范围查询操作日志列表
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 操作日志列表
     */
    List<OperationLogs> findByTimeRange(LocalDateTime startTime, LocalDateTime endTime);

    /**
     * 查询失败的操作日志列表
     * @return 失败的操作日志列表
     */
    List<OperationLogs> findFailedOperations();

    /**
     * 查询慢操作日志列表
     * @param minExecutionTime 最小执行时间（毫秒）
     * @return 慢操作日志列表
     */
    List<OperationLogs> findSlowOperations(Long minExecutionTime);

    /**
     * 分页查询操作日志列表
     * @param pageRequest 分页请求
     * @return 分页结果
     */
    PageResult<OperationLogs> findPage(PageRequest pageRequest);

    /**
     * 保存操作日志
     * @param operationLog 操作日志信息
     * @return 保存后的操作日志信息
     */
    OperationLogs save(OperationLogs operationLog);

    /**
     * 更新操作日志
     * @param operationLog 操作日志信息
     * @return 更新后的操作日志信息
     */
    OperationLogs update(OperationLogs operationLog);

    /**
     * 删除操作日志
     * @param id 操作日志ID
     * @return 是否删除成功
     */
    boolean deleteById(Long id);

    /**
     * 批量删除过期日志
     * @param expiredTime 过期时间
     * @return 删除的数量
     */
    int deleteExpiredLogs(LocalDateTime expiredTime);

    /**
     * 清理指定天数前的日志
     * @param days 保留天数
     * @return 删除的数量
     */
    int cleanupOldLogs(int days);

    /**
     * 统计操作日志总数
     * @return 操作日志总数
     */
    long count();

    /**
     * 统计用户的操作数量
     * @param userId 用户ID
     * @return 操作数量
     */
    long countByUserId(Long userId);

    /**
     * 统计指定状态的操作数量
     * @param status 操作状态
     * @return 操作数量
     */
    long countByStatus(String status);

    /**
     * 统计指定类型的操作数量
     * @param operationType 操作类型
     * @return 操作数量
     */
    long countByOperationType(String operationType);

    /**
     * 统计失败操作数量
     * @return 失败操作数量
     */
    long countFailedOperations();

    /**
     * 计算平均执行时间
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 平均执行时间（毫秒）
     */
    Double calculateAverageExecutionTime(LocalDateTime startTime, LocalDateTime endTime);
}
