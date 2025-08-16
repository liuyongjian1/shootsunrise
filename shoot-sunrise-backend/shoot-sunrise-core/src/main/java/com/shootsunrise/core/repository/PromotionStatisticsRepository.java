package com.shootsunrise.core.repository;

import com.shootsunrise.common.api.request.PageRequest;
import com.shootsunrise.common.api.response.PageResult;
import com.shootsunrise.core.entity.PromotionStatistics;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * 营销活动统计表 Repository 接口
 * @author lyj
 * @since 2025-07-27
 */
public interface PromotionStatisticsRepository {

    /**
     * 根据ID查询统计记录
     * @param id 统计ID
     * @return 统计记录信息
     */
    PromotionStatistics findById(Long id);

    /**
     * 根据活动ID查询统计记录列表
     * @param promotionId 活动ID
     * @return 统计记录列表
     */
    List<PromotionStatistics> findByPromotionId(Long promotionId);

    /**
     * 根据活动ID和统计日期查询统计记录
     * @param promotionId 活动ID
     * @param statDate 统计日期
     * @return 统计记录信息
     */
    PromotionStatistics findByPromotionIdAndStatDate(Long promotionId, LocalDate statDate);

    /**
     * 根据统计日期查询统计记录列表
     * @param statDate 统计日期
     * @return 统计记录列表
     */
    List<PromotionStatistics> findByStatDate(LocalDate statDate);

    /**
     * 根据日期范围查询统计记录列表
     * @param promotionId 活动ID
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 统计记录列表
     */
    List<PromotionStatistics> findByPromotionIdAndDateRange(Long promotionId, LocalDate startDate, LocalDate endDate);

    /**
     * 根据日期范围查询统计记录列表
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 统计记录列表
     */
    List<PromotionStatistics> findByDateRange(LocalDate startDate, LocalDate endDate);

    /**
     * 分页查询统计记录列表
     * @param pageRequest 分页请求
     * @return 分页结果
     */
    PageResult<PromotionStatistics> findPage(PageRequest pageRequest);

    /**
     * 保存统计记录
     * @param statistics 统计记录信息
     * @return 保存后的统计记录信息
     */
    PromotionStatistics save(PromotionStatistics statistics);

    /**
     * 更新统计记录
     * @param statistics 统计记录信息
     * @return 更新后的统计记录信息
     */
    PromotionStatistics update(PromotionStatistics statistics);

    /**
     * 删除统计记录
     * @param id 统计ID
     * @return 是否删除成功
     */
    boolean deleteById(Long id);

    /**
     * 增加浏览次数
     * @param promotionId 活动ID
     * @param statDate 统计日期
     * @param count 增加数量
     * @return 是否更新成功
     */
    boolean incrementViewCount(Long promotionId, LocalDate statDate, Integer count);

    /**
     * 增加分享次数
     * @param promotionId 活动ID
     * @param statDate 统计日期
     * @param count 增加数量
     * @return 是否更新成功
     */
    boolean incrementShareCount(Long promotionId, LocalDate statDate, Integer count);

    /**
     * 增加参与人数
     * @param promotionId 活动ID
     * @param statDate 统计日期
     * @param count 增加数量
     * @return 是否更新成功
     */
    boolean incrementParticipantCount(Long promotionId, LocalDate statDate, Integer count);

    /**
     * 增加使用次数
     * @param promotionId 活动ID
     * @param statDate 统计日期
     * @param count 增加数量
     * @return 是否更新成功
     */
    boolean incrementUsageCount(Long promotionId, LocalDate statDate, Integer count);

    /**
     * 增加转化金额
     * @param promotionId 活动ID
     * @param statDate 统计日期
     * @param amount 转化金额
     * @return 是否更新成功
     */
    boolean addConversionAmount(Long promotionId, LocalDate statDate, BigDecimal amount);

    /**
     * 更新转化率
     * @param promotionId 活动ID
     * @param statDate 统计日期
     * @return 是否更新成功
     */
    boolean updateConversionRate(Long promotionId, LocalDate statDate);

    /**
     * 统计记录总数
     * @return 统计记录总数
     */
    long count();

    /**
     * 统计活动的统计记录数量
     * @param promotionId 活动ID
     * @return 统计记录数量
     */
    long countByPromotionId(Long promotionId);

    /**
     * 计算活动的总浏览次数
     * @param promotionId 活动ID
     * @return 总浏览次数
     */
    Integer calculateTotalViewCount(Long promotionId);

    /**
     * 计算活动的总参与人数
     * @param promotionId 活动ID
     * @return 总参与人数
     */
    Integer calculateTotalParticipantCount(Long promotionId);

    /**
     * 计算活动的总转化金额
     * @param promotionId 活动ID
     * @return 总转化金额
     */
    BigDecimal calculateTotalConversionAmount(Long promotionId);

    /**
     * 计算活动的平均转化率
     * @param promotionId 活动ID
     * @return 平均转化率
     */
    BigDecimal calculateAverageConversionRate(Long promotionId);
}
