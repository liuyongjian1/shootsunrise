package com.shootsunrise.core.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shootsunrise.common.api.request.PageRequest;
import com.shootsunrise.common.api.response.PageResult;
import com.shootsunrise.core.entity.PromotionStatistics;
import com.shootsunrise.core.mapper.PromotionStatisticsMapper;
import com.shootsunrise.core.repository.PromotionStatisticsRepository;
import org.springframework.stereotype.Repository;

import jakarta.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

/**
 * 营销活动统计表 Repository 实现类
 * @author lyj
 * @since 2025-07-27
 */
@Repository
public class PromotionStatisticsRepositoryImpl implements PromotionStatisticsRepository {

    @Resource
    private PromotionStatisticsMapper promotionStatisticsMapper;

    @Override
    public PromotionStatistics findById(Long id) {
        return promotionStatisticsMapper.selectById(id);
    }

    @Override
    public List<PromotionStatistics> findByPromotionId(Long promotionId) {
        LambdaQueryWrapper<PromotionStatistics> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PromotionStatistics::getPromotionId, promotionId)
               .eq(PromotionStatistics::getIsDelete, 0)
               .orderByDesc(PromotionStatistics::getStatDate);
        return promotionStatisticsMapper.selectList(wrapper);
    }

    @Override
    public PromotionStatistics findByPromotionIdAndStatDate(Long promotionId, LocalDate statDate) {
        LambdaQueryWrapper<PromotionStatistics> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PromotionStatistics::getPromotionId, promotionId)
               .eq(PromotionStatistics::getStatDate, statDate)
               .eq(PromotionStatistics::getIsDelete, 0);
        return promotionStatisticsMapper.selectOne(wrapper);
    }

    @Override
    public List<PromotionStatistics> findByStatDate(LocalDate statDate) {
        LambdaQueryWrapper<PromotionStatistics> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PromotionStatistics::getStatDate, statDate)
               .eq(PromotionStatistics::getIsDelete, 0)
               .orderByDesc(PromotionStatistics::getViewCount);
        return promotionStatisticsMapper.selectList(wrapper);
    }

    @Override
    public List<PromotionStatistics> findByPromotionIdAndDateRange(Long promotionId, LocalDate startDate, LocalDate endDate) {
        LambdaQueryWrapper<PromotionStatistics> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PromotionStatistics::getPromotionId, promotionId)
               .ge(PromotionStatistics::getStatDate, startDate)
               .le(PromotionStatistics::getStatDate, endDate)
               .eq(PromotionStatistics::getIsDelete, 0)
               .orderByAsc(PromotionStatistics::getStatDate);
        return promotionStatisticsMapper.selectList(wrapper);
    }

    @Override
    public List<PromotionStatistics> findByDateRange(LocalDate startDate, LocalDate endDate) {
        LambdaQueryWrapper<PromotionStatistics> wrapper = new LambdaQueryWrapper<>();
        wrapper.ge(PromotionStatistics::getStatDate, startDate)
               .le(PromotionStatistics::getStatDate, endDate)
               .eq(PromotionStatistics::getIsDelete, 0)
               .orderByAsc(PromotionStatistics::getStatDate);
        return promotionStatisticsMapper.selectList(wrapper);
    }

    @Override
    public PageResult<PromotionStatistics> findPage(PageRequest pageRequest) {
        LambdaQueryWrapper<PromotionStatistics> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PromotionStatistics::getIsDelete, 0)
               .orderByDesc(PromotionStatistics::getStatDate);
        return promotionStatisticsMapper.selectPage(pageRequest, wrapper);
    }

    @Override
    public PromotionStatistics save(PromotionStatistics statistics) {
        promotionStatisticsMapper.insert(statistics);
        return statistics;
    }

    @Override
    public PromotionStatistics update(PromotionStatistics statistics) {
        promotionStatisticsMapper.updateById(statistics);
        return statistics;
    }

    @Override
    public boolean deleteById(Long id) {
        PromotionStatistics statistics = new PromotionStatistics();
        statistics.setId(id);
        statistics.setIsDelete(1);
        return promotionStatisticsMapper.updateById(statistics) > 0;
    }

    @Override
    public boolean incrementViewCount(Long promotionId, LocalDate statDate, Integer count) {
        return updateStatisticField(promotionId, statDate, "viewCount", count);
    }

    @Override
    public boolean incrementShareCount(Long promotionId, LocalDate statDate, Integer count) {
        return updateStatisticField(promotionId, statDate, "shareCount", count);
    }

    @Override
    public boolean incrementParticipantCount(Long promotionId, LocalDate statDate, Integer count) {
        return updateStatisticField(promotionId, statDate, "participantCount", count);
    }

    @Override
    public boolean incrementUsageCount(Long promotionId, LocalDate statDate, Integer count) {
        return updateStatisticField(promotionId, statDate, "usageCount", count);
    }

    @Override
    public boolean addConversionAmount(Long promotionId, LocalDate statDate, BigDecimal amount) {
        // 1. 查询或创建统计记录
        PromotionStatistics statistics = findByPromotionIdAndStatDate(promotionId, statDate);
        if (statistics == null) {
            statistics = createDefaultStatistics(promotionId, statDate);
            save(statistics);
        }
        
        // 2. 更新转化金额
        BigDecimal newAmount = statistics.getConversionAmount().add(amount);
        statistics.setConversionAmount(newAmount);
        
        // 3. 更新转化率
        updateConversionRateInternal(statistics);
        
        return promotionStatisticsMapper.updateById(statistics) > 0;
    }

    @Override
    public boolean updateConversionRate(Long promotionId, LocalDate statDate) {
        PromotionStatistics statistics = findByPromotionIdAndStatDate(promotionId, statDate);
        if (statistics == null) {
            return false;
        }
        
        updateConversionRateInternal(statistics);
        return promotionStatisticsMapper.updateById(statistics) > 0;
    }

    @Override
    public long count() {
        LambdaQueryWrapper<PromotionStatistics> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PromotionStatistics::getIsDelete, 0);
        return promotionStatisticsMapper.selectCount(wrapper);
    }

    @Override
    public long countByPromotionId(Long promotionId) {
        LambdaQueryWrapper<PromotionStatistics> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PromotionStatistics::getPromotionId, promotionId)
               .eq(PromotionStatistics::getIsDelete, 0);
        return promotionStatisticsMapper.selectCount(wrapper);
    }

    @Override
    public Integer calculateTotalViewCount(Long promotionId) {
        List<PromotionStatistics> statisticsList = findByPromotionId(promotionId);
        return statisticsList.stream()
                .mapToInt(PromotionStatistics::getViewCount)
                .sum();
    }

    @Override
    public Integer calculateTotalParticipantCount(Long promotionId) {
        List<PromotionStatistics> statisticsList = findByPromotionId(promotionId);
        return statisticsList.stream()
                .mapToInt(PromotionStatistics::getParticipantCount)
                .sum();
    }

    @Override
    public BigDecimal calculateTotalConversionAmount(Long promotionId) {
        List<PromotionStatistics> statisticsList = findByPromotionId(promotionId);
        return statisticsList.stream()
                .map(PromotionStatistics::getConversionAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public BigDecimal calculateAverageConversionRate(Long promotionId) {
        List<PromotionStatistics> statisticsList = findByPromotionId(promotionId);
        if (statisticsList.isEmpty()) {
            return BigDecimal.ZERO;
        }
        
        BigDecimal totalRate = statisticsList.stream()
                .map(PromotionStatistics::getConversionRate)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        return totalRate.divide(BigDecimal.valueOf(statisticsList.size()), 4, RoundingMode.HALF_UP);
    }

    /**
     * 更新统计字段的通用方法
     */
    private boolean updateStatisticField(Long promotionId, LocalDate statDate, String fieldName, Integer count) {
        // 1. 查询或创建统计记录
        PromotionStatistics statistics = findByPromotionIdAndStatDate(promotionId, statDate);
        if (statistics == null) {
            statistics = createDefaultStatistics(promotionId, statDate);
            save(statistics);
        }
        
        // 2. 更新对应字段
        switch (fieldName) {
            case "viewCount":
                statistics.setViewCount(statistics.getViewCount() + count);
                break;
            case "shareCount":
                statistics.setShareCount(statistics.getShareCount() + count);
                break;
            case "participantCount":
                statistics.setParticipantCount(statistics.getParticipantCount() + count);
                break;
            case "usageCount":
                statistics.setUsageCount(statistics.getUsageCount() + count);
                updateConversionRateInternal(statistics);
                break;
        }
        
        return promotionStatisticsMapper.updateById(statistics) > 0;
    }

    /**
     * 创建默认统计记录
     */
    private PromotionStatistics createDefaultStatistics(Long promotionId, LocalDate statDate) {
        PromotionStatistics statistics = new PromotionStatistics();
        statistics.setPromotionId(promotionId);
        statistics.setStatDate(statDate);
        statistics.setViewCount(0);
        statistics.setShareCount(0);
        statistics.setParticipantCount(0);
        statistics.setUsageCount(0);
        statistics.setConversionAmount(BigDecimal.ZERO);
        statistics.setConversionRate(BigDecimal.ZERO);
        return statistics;
    }

    /**
     * 更新转化率
     */
    private void updateConversionRateInternal(PromotionStatistics statistics) {
        if (statistics.getParticipantCount() > 0) {
            BigDecimal rate = BigDecimal.valueOf(statistics.getUsageCount())
                    .divide(BigDecimal.valueOf(statistics.getParticipantCount()), 4, RoundingMode.HALF_UP);
            statistics.setConversionRate(rate);
        } else {
            statistics.setConversionRate(BigDecimal.ZERO);
        }
    }
}
