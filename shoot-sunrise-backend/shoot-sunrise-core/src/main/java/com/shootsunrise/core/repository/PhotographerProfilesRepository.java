package com.shootsunrise.core.repository;

import com.shootsunrise.common.api.request.PageRequest;
import com.shootsunrise.common.api.response.PageResult;
import com.shootsunrise.core.entity.PhotographerProfiles;
import com.shootsunrise.core.enums.CertificationStatusEnum;

import java.math.BigDecimal;
import java.util.List;

/**
 * 摄影师认证信息表 Repository 接口
 * @author lyj
 * @since 2025-07-27
 */
public interface PhotographerProfilesRepository {

    /**
     * 根据ID查询摄影师资料
     * @param id 摄影师资料ID
     * @return 摄影师资料
     */
    PhotographerProfiles findById(Long id);

    /**
     * 根据用户ID查询摄影师资料
     * @param userId 用户ID
     * @return 摄影师资料
     */
    PhotographerProfiles findByUserId(Long userId);

    /**
     * 分页查询摄影师列表
     * @param pageRequest 分页请求
     * @return 分页结果
     */
    PageResult<PhotographerProfiles> findPage(PageRequest pageRequest);

    /**
     * 根据认证状态查询摄影师列表
     * @param certificationStatus 认证状态
     * @return 摄影师列表
     */
    List<PhotographerProfiles> findByCertificationStatus(CertificationStatusEnum certificationStatus);

    /**
     * 查询推荐摄影师列表
     * @return 推荐摄影师列表
     */
    List<PhotographerProfiles> findFeaturedPhotographers();

    /**
     * 根据评分范围查询摄影师
     * @param minRating 最低评分
     * @param maxRating 最高评分
     * @return 摄影师列表
     */
    List<PhotographerProfiles> findByRatingRange(BigDecimal minRating, BigDecimal maxRating);

    /**
     * 保存摄影师资料
     * @param profile 摄影师资料
     * @return 保存后的摄影师资料
     */
    PhotographerProfiles save(PhotographerProfiles profile);

    /**
     * 更新摄影师资料
     * @param profile 摄影师资料
     * @return 更新后的摄影师资料
     */
    PhotographerProfiles update(PhotographerProfiles profile);

    /**
     * 删除摄影师资料
     * @param id 摄影师资料ID
     * @return 是否删除成功
     */
    boolean deleteById(Long id);

    /**
     * 统计摄影师总数
     * @return 摄影师总数
     */
    long count();

    /**
     * 统计指定认证状态的摄影师数量
     * @param certificationStatus 认证状态
     * @return 摄影师数量
     */
    long countByCertificationStatus(CertificationStatusEnum certificationStatus);

    /**
     * 更新认证状态
     * @param id 摄影师资料ID
     * @param status 认证状态
     * @param reason 拒绝原因（可选）
     * @return 是否更新成功
     */
    boolean updateCertificationStatus(Long id, CertificationStatusEnum status, String reason);

    /**
     * 更新评分
     * @param id 摄影师资料ID
     * @param rating 评分
     * @return 是否更新成功
     */
    boolean updateRating(Long id, BigDecimal rating);
}
