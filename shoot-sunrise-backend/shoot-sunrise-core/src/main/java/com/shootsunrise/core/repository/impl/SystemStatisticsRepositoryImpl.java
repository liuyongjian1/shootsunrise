package com.shootsunrise.core.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shootsunrise.common.api.request.PageRequest;
import com.shootsunrise.common.api.response.PageResult;
import com.shootsunrise.core.entity.SystemStatistics;
import com.shootsunrise.core.mapper.SystemStatisticsMapper;
import com.shootsunrise.core.repository.SystemStatisticsRepository;
import org.springframework.stereotype.Repository;

import jakarta.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * 系统统计数据表 Repository 实现类
 * @author lyj
 * @since 2025-07-27
 */
@Repository
public class SystemStatisticsRepositoryImpl implements SystemStatisticsRepository {

    @Resource
    private SystemStatisticsMapper systemStatisticsMapper;

    @Override
    public SystemStatistics findById(Long id) {
        return systemStatisticsMapper.selectById(id);
    }

    @Override
    public SystemStatistics findByStatDateAndType(LocalDate statDate, String statType) {
        LambdaQueryWrapper<SystemStatistics> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SystemStatistics::getStatDate, statDate)
               .eq(SystemStatistics::getStatType, statType)
               .eq(SystemStatistics::getIsDelete, 0);
        return systemStatisticsMapper.selectOne(wrapper);
    }

    @Override
    public List<SystemStatistics> findByStatDate(LocalDate statDate) {
        LambdaQueryWrapper<SystemStatistics> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SystemStatistics::getStatDate, statDate)
               .eq(SystemStatistics::getIsDelete, 0)
               .orderByAsc(SystemStatistics::getStatType);
        return systemStatisticsMapper.selectList(wrapper);
    }

    @Override
    public List<SystemStatistics> findByStatType(String statType) {
        LambdaQueryWrapper<SystemStatistics> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SystemStatistics::getStatType, statType)
               .eq(SystemStatistics::getIsDelete, 0)
               .orderByDesc(SystemStatistics::getStatDate);
        return systemStatisticsMapper.selectList(wrapper);
    }

    @Override
    public List<SystemStatistics> findByDateRange(LocalDate startDate, LocalDate endDate) {
        LambdaQueryWrapper<SystemStatistics> wrapper = new LambdaQueryWrapper<>();
        wrapper.ge(SystemStatistics::getStatDate, startDate)
               .le(SystemStatistics::getStatDate, endDate)
               .eq(SystemStatistics::getIsDelete, 0)
               .orderByAsc(SystemStatistics::getStatDate)
               .orderByAsc(SystemStatistics::getStatType);
        return systemStatisticsMapper.selectList(wrapper);
    }

    @Override
    public List<SystemStatistics> findByDateRangeAndType(LocalDate startDate, LocalDate endDate, String statType) {
        LambdaQueryWrapper<SystemStatistics> wrapper = new LambdaQueryWrapper<>();
        wrapper.ge(SystemStatistics::getStatDate, startDate)
               .le(SystemStatistics::getStatDate, endDate)
               .eq(SystemStatistics::getStatType, statType)
               .eq(SystemStatistics::getIsDelete, 0)
               .orderByAsc(SystemStatistics::getStatDate);
        return systemStatisticsMapper.selectList(wrapper);
    }

    @Override
    public SystemStatistics findLatestByStatType(String statType) {
        LambdaQueryWrapper<SystemStatistics> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SystemStatistics::getStatType, statType)
               .eq(SystemStatistics::getIsDelete, 0)
               .orderByDesc(SystemStatistics::getStatDate)
               .last("LIMIT 1");
        return systemStatisticsMapper.selectOne(wrapper);
    }

    @Override
    public PageResult<SystemStatistics> findPage(PageRequest pageRequest) {
        LambdaQueryWrapper<SystemStatistics> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SystemStatistics::getIsDelete, 0)
               .orderByDesc(SystemStatistics::getStatDate)
               .orderByAsc(SystemStatistics::getStatType);
        return systemStatisticsMapper.selectPage(pageRequest, wrapper);
    }

    @Override
    public SystemStatistics save(SystemStatistics statistics) {
        systemStatisticsMapper.insert(statistics);
        return statistics;
    }

    @Override
    public SystemStatistics update(SystemStatistics statistics) {
        systemStatisticsMapper.updateById(statistics);
        return statistics;
    }

    @Override
    public boolean deleteById(Long id) {
        SystemStatistics statistics = new SystemStatistics();
        statistics.setId(id);
        statistics.setIsDelete(1);
        return systemStatisticsMapper.updateById(statistics) > 0;
    }

    @Override
    public int deleteExpiredStatistics(LocalDate expiredDate) {
        LambdaQueryWrapper<SystemStatistics> wrapper = new LambdaQueryWrapper<>();
        wrapper.lt(SystemStatistics::getStatDate, expiredDate)
               .eq(SystemStatistics::getIsDelete, 0);
        
        List<SystemStatistics> expiredStatistics = systemStatisticsMapper.selectList(wrapper);
        int count = 0;
        for (SystemStatistics statistics : expiredStatistics) {
            if (deleteById(statistics.getId())) {
                count++;
            }
        }
        return count;
    }

    @Override
    public long count() {
        LambdaQueryWrapper<SystemStatistics> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SystemStatistics::getIsDelete, 0);
        return systemStatisticsMapper.selectCount(wrapper);
    }

    @Override
    public long countByStatType(String statType) {
        LambdaQueryWrapper<SystemStatistics> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SystemStatistics::getStatType, statType)
               .eq(SystemStatistics::getIsDelete, 0);
        return systemStatisticsMapper.selectCount(wrapper);
    }

    @Override
    public List<SystemStatistics> calculateUserGrowthTrend(LocalDate startDate, LocalDate endDate) {
        return findByDateRangeAndType(startDate, endDate, "DAILY");
    }

    @Override
    public List<SystemStatistics> calculateRevenueGrowthTrend(LocalDate startDate, LocalDate endDate) {
        return findByDateRangeAndType(startDate, endDate, "DAILY");
    }

    @Override
    public Integer getTotalUserCount(LocalDate statDate) {
        SystemStatistics statistics = findByStatDateAndType(statDate, "DAILY");
        return statistics != null ? statistics.getUserCount() : 0;
    }

    @Override
    public Integer getTotalPhotographerCount(LocalDate statDate) {
        SystemStatistics statistics = findByStatDateAndType(statDate, "DAILY");
        return statistics != null ? statistics.getPhotographerCount() : 0;
    }

    @Override
    public BigDecimal getTotalRevenue(LocalDate statDate) {
        SystemStatistics statistics = findByStatDateAndType(statDate, "DAILY");
        return statistics != null ? statistics.getTotalRevenue() : BigDecimal.ZERO;
    }

    @Override
    public Integer getActiveUserCount(LocalDate statDate) {
        SystemStatistics statistics = findByStatDateAndType(statDate, "DAILY");
        return statistics != null ? statistics.getActiveUserCount() : 0;
    }

    @Override
    public Double calculateAverageDailyActiveUsers(LocalDate startDate, LocalDate endDate) {
        List<SystemStatistics> statisticsList = findByDateRangeAndType(startDate, endDate, "DAILY");
        if (statisticsList.isEmpty()) {
            return 0.0;
        }
        
        int totalActiveUsers = statisticsList.stream()
                .mapToInt(SystemStatistics::getActiveUserCount)
                .sum();
        
        return (double) totalActiveUsers / statisticsList.size();
    }

    @Override
    public BigDecimal calculateTotalRevenueInPeriod(LocalDate startDate, LocalDate endDate) {
        List<SystemStatistics> statisticsList = findByDateRangeAndType(startDate, endDate, "DAILY");
        return statisticsList.stream()
                .map(SystemStatistics::getNewRevenue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
