package com.shootsunrise.core.repository;

import com.shootsunrise.common.api.request.PageRequest;
import com.shootsunrise.common.api.response.PageResult;
import com.shootsunrise.core.entity.Reviews;

import java.math.BigDecimal;
import java.util.List;

/**
 * 评价表 Repository 接口
 * @author lyj
 * @since 2025-07-27
 */
public interface ReviewsRepository {

    /**
     * 根据ID查询评价
     * @param id 评价ID
     * @return 评价信息
     */
    Reviews findById(Long id);

    /**
     * 根据预约ID查询评价
     * @param bookingId 预约ID
     * @return 评价信息
     */
    Reviews findByBookingId(Long bookingId);

    /**
     * 根据客户ID查询评价列表
     * @param clientId 客户ID
     * @return 评价列表
     */
    List<Reviews> findByClientId(Long clientId);

    /**
     * 根据摄影师ID查询评价列表
     * @param photographerId 摄影师ID
     * @return 评价列表
     */
    List<Reviews> findByPhotographerId(Long photographerId);

    /**
     * 分页查询评价列表
     * @param pageRequest 分页请求
     * @return 分页结果
     */
    PageResult<Reviews> findPage(PageRequest pageRequest);

    /**
     * 查询可见的评价列表
     * @param photographerId 摄影师ID
     * @return 可见评价列表
     */
    List<Reviews> findVisibleReviewsByPhotographerId(Long photographerId);

    /**
     * 根据评分范围查询评价
     * @param minRating 最低评分
     * @param maxRating 最高评分
     * @return 评价列表
     */
    List<Reviews> findByRatingRange(BigDecimal minRating, BigDecimal maxRating);

    /**
     * 保存评价
     * @param review 评价信息
     * @return 保存后的评价信息
     */
    Reviews save(Reviews review);

    /**
     * 更新评价
     * @param review 评价信息
     * @return 更新后的评价信息
     */
    Reviews update(Reviews review);

    /**
     * 删除评价
     * @param id 评价ID
     * @return 是否删除成功
     */
    boolean deleteById(Long id);

    /**
     * 设置评价可见性
     * @param id 评价ID
     * @param isVisible 是否可见
     * @return 是否更新成功
     */
    boolean setVisibility(Long id, Boolean isVisible);

    /**
     * 增加点赞数
     * @param id 评价ID
     * @return 是否更新成功
     */
    boolean incrementLikeCount(Long id);

    /**
     * 统计评价总数
     * @return 评价总数
     */
    long count();

    /**
     * 统计摄影师的评价数量
     * @param photographerId 摄影师ID
     * @return 评价数量
     */
    long countByPhotographerId(Long photographerId);

    /**
     * 计算摄影师的平均评分
     * @param photographerId 摄影师ID
     * @return 平均评分
     */
    BigDecimal calculateAverageRating(Long photographerId);
}
