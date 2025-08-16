package com.shootsunrise.core.repository;

import com.shootsunrise.common.api.request.PageRequest;
import com.shootsunrise.common.api.response.PageResult;
import com.shootsunrise.core.entity.SystemStatistics;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * 系统统计数据表 Repository 接口
 * @author lyj
 * @since 2025-07-27
 */
public interface SystemStatisticsRepository {

    /**
     * 根据ID查询统计数据
     * @param id 统计ID
     * @return 统计数据信息
     */
    SystemStatistics findById(Long id);

    /**
     * 根据统计日期和类型查询统计数据
     * @param statDate 统计日期
     * @param statType 统计类型
     * @return 统计数据信息
     */
    SystemStatistics findByStatDateAndType(LocalDate statDate, String statType);

    /**
     * 根据统计日期查询统计数据列表
     * @param statDate 统计日期
     * @return 统计数据列表
     */
    List<SystemStatistics> findByStatDate(LocalDate statDate);

    /**
     * 根据统计类型查询统计数据列表
     * @param statType 统计类型
     * @return 统计数据列表
     */
    List<SystemStatistics> findByStatType(String statType);

    /**
     * 根据日期范围查询统计数据列表
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 统计数据列表
     */
    List<SystemStatistics> findByDateRange(LocalDate startDate, LocalDate endDate);

    /**
     * 根据日期范围和类型查询统计数据列表
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @param statType 统计类型
     * @return 统计数据列表
     */
    List<SystemStatistics> findByDateRangeAndType(LocalDate startDate, LocalDate endDate, String statType);

    /**
     * 查询最新的统计数据
     * @param statType 统计类型
     * @return 最新统计数据
     */
    SystemStatistics findLatestByStatType(String statType);

    /**
     * 分页查询统计数据列表
     * @param pageRequest 分页请求
     * @return 分页结果
     */
    PageResult<SystemStatistics> findPage(PageRequest pageRequest);

    /**
     * 保存统计数据
     * @param statistics 统计数据信息
     * @return 保存后的统计数据信息
     */
    SystemStatistics save(SystemStatistics statistics);

    /**
     * 更新统计数据
     * @param statistics 统计数据信息
     * @return 更新后的统计数据信息
     */
    SystemStatistics update(SystemStatistics statistics);

    /**
     * 删除统计数据
     * @param id 统计ID
     * @return 是否删除成功
     */
    boolean deleteById(Long id);

    /**
     * 删除过期统计数据
     * @param expiredDate 过期日期
     * @return 删除的数量
     */
    int deleteExpiredStatistics(LocalDate expiredDate);

    /**
     * 统计数据总数
     * @return 统计数据总数
     */
    long count();

    /**
     * 统计指定类型的数据数量
     * @param statType 统计类型
     * @return 数据数量
     */
    long countByStatType(String statType);

    /**
     * 计算用户增长趋势
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 用户增长数据列表
     */
    List<SystemStatistics> calculateUserGrowthTrend(LocalDate startDate, LocalDate endDate);

    /**
     * 计算收入增长趋势
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 收入增长数据列表
     */
    List<SystemStatistics> calculateRevenueGrowthTrend(LocalDate startDate, LocalDate endDate);

    /**
     * 计算总用户数
     * @param statDate 统计日期
     * @return 总用户数
     */
    Integer getTotalUserCount(LocalDate statDate);

    /**
     * 计算总摄影师数
     * @param statDate 统计日期
     * @return 总摄影师数
     */
    Integer getTotalPhotographerCount(LocalDate statDate);

    /**
     * 计算总收入
     * @param statDate 统计日期
     * @return 总收入
     */
    BigDecimal getTotalRevenue(LocalDate statDate);

    /**
     * 计算活跃用户数
     * @param statDate 统计日期
     * @return 活跃用户数
     */
    Integer getActiveUserCount(LocalDate statDate);

    /**
     * 计算指定时间段的平均日活跃用户数
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 平均日活跃用户数
     */
    Double calculateAverageDailyActiveUsers(LocalDate startDate, LocalDate endDate);

    /**
     * 计算指定时间段的总收入
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 总收入
     */
    BigDecimal calculateTotalRevenueInPeriod(LocalDate startDate, LocalDate endDate);
}
