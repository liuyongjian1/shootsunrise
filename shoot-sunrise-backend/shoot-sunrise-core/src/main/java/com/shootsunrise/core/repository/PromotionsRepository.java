package com.shootsunrise.core.repository;

import com.shootsunrise.common.api.request.PageRequest;
import com.shootsunrise.common.api.response.PageResult;
import com.shootsunrise.core.entity.Promotions;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 营销活动表 Repository 接口
 * @author lyj
 * @since 2025-07-27
 */
public interface PromotionsRepository {

    /**
     * 根据ID查询营销活动
     * @param id 活动ID
     * @return 营销活动信息
     */
    Promotions findById(Long id);

    /**
     * 根据摄影师ID查询营销活动列表
     * @param photographerId 摄影师ID
     * @return 营销活动列表
     */
    List<Promotions> findByPhotographerId(Long photographerId);

    /**
     * 根据活动类型查询营销活动列表
     * @param type 活动类型
     * @return 营销活动列表
     */
    List<Promotions> findByType(String type);

    /**
     * 查询激活的营销活动列表
     * @return 激活的营销活动列表
     */
    List<Promotions> findActivePromotions();

    /**
     * 查询进行中的营销活动列表
     * @return 进行中的营销活动列表
     */
    List<Promotions> findOngoingPromotions();

    /**
     * 根据目标用户查询营销活动列表
     * @param targetUsers 目标用户类型
     * @return 营销活动列表
     */
    List<Promotions> findByTargetUsers(String targetUsers);

    /**
     * 根据时间范围查询营销活动列表
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 营销活动列表
     */
    List<Promotions> findByTimeRange(LocalDateTime startTime, LocalDateTime endTime);

    /**
     * 分页查询营销活动列表
     * @param pageRequest 分页请求
     * @return 分页结果
     */
    PageResult<Promotions> findPage(PageRequest pageRequest);

    /**
     * 保存营销活动
     * @param promotion 营销活动信息
     * @return 保存后的营销活动信息
     */
    Promotions save(Promotions promotion);

    /**
     * 更新营销活动
     * @param promotion 营销活动信息
     * @return 更新后的营销活动信息
     */
    Promotions update(Promotions promotion);

    /**
     * 删除营销活动
     * @param id 活动ID
     * @return 是否删除成功
     */
    boolean deleteById(Long id);

    /**
     * 激活/禁用营销活动
     * @param id 活动ID
     * @param isActive 是否激活
     * @return 是否更新成功
     */
    boolean setActive(Long id, Boolean isActive);

    /**
     * 增加参与人数
     * @param id 活动ID
     * @return 是否更新成功
     */
    boolean incrementParticipantCount(Long id);

    /**
     * 减少参与人数
     * @param id 活动ID
     * @return 是否更新成功
     */
    boolean decrementParticipantCount(Long id);

    /**
     * 检查活动是否已满员
     * @param id 活动ID
     * @return 是否已满员
     */
    boolean isParticipantLimitReached(Long id);

    /**
     * 统计营销活动总数
     * @return 营销活动总数
     */
    long count();

    /**
     * 统计摄影师的营销活动数量
     * @param photographerId 摄影师ID
     * @return 营销活动数量
     */
    long countByPhotographerId(Long photographerId);

    /**
     * 统计指定类型的营销活动数量
     * @param type 活动类型
     * @return 营销活动数量
     */
    long countByType(String type);

    /**
     * 统计激活的营销活动数量
     * @return 激活的营销活动数量
     */
    long countActivePromotions();
}
