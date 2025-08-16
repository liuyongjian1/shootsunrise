package com.shootsunrise.core.repository;

import com.shootsunrise.common.api.request.PageRequest;
import com.shootsunrise.common.api.response.PageResult;
import com.shootsunrise.core.entity.ServicePackages;
import com.shootsunrise.core.enums.ServicePackageCategoryEnum;

import java.math.BigDecimal;
import java.util.List;

/**
 * 服务套餐表 Repository 接口
 * @author lyj
 * @since 2025-07-27
 */
public interface ServicePackagesRepository {

    /**
     * 根据ID查询服务套餐
     * @param id 套餐ID
     * @return 服务套餐信息
     */
    ServicePackages findById(Long id);

    /**
     * 根据摄影师ID查询套餐列表
     * @param photographerId 摄影师ID
     * @return 套餐列表
     */
    List<ServicePackages> findByPhotographerId(Long photographerId);

    /**
     * 分页查询服务套餐列表
     * @param pageRequest 分页请求
     * @return 分页结果
     */
    PageResult<ServicePackages> findPage(PageRequest pageRequest);

    /**
     * 查询启用的服务套餐
     * @param photographerId 摄影师ID
     * @return 启用的套餐列表
     */
    List<ServicePackages> findActivePackagesByPhotographerId(Long photographerId);

    /**
     * 根据价格范围查询套餐
     * @param minPrice 最低价格
     * @param maxPrice 最高价格
     * @return 套餐列表
     */
    List<ServicePackages> findByPriceRange(BigDecimal minPrice, BigDecimal maxPrice);

    /**
     * 根据套餐类型查询
     * @param packageType 套餐类型
     * @return 套餐列表
     */
    List<ServicePackages> findByPackageType(ServicePackageCategoryEnum packageType);

    /**
     * 根据分类查询套餐
     * @param category 套餐分类
     * @return 套餐列表
     */
    List<ServicePackages> findByCategory(ServicePackageCategoryEnum category);

    /**
     * 查询所有启用的套餐
     * @return 启用的套餐列表
     */
    List<ServicePackages> findActivePackages();

    /**
     * 查询热门套餐
     * @param limit 限制数量
     * @return 热门套餐列表
     */
    List<ServicePackages> findPopularPackages(Integer limit);

    /**
     * 保存服务套餐
     * @param servicePackage 服务套餐信息
     * @return 保存后的服务套餐信息
     */
    ServicePackages save(ServicePackages servicePackage);

    /**
     * 更新服务套餐
     * @param servicePackage 服务套餐信息
     * @return 更新后的服务套餐信息
     */
    ServicePackages update(ServicePackages servicePackage);

    /**
     * 删除服务套餐
     * @param id 套餐ID
     * @return 是否删除成功
     */
    boolean deleteById(Long id);

    /**
     * 设置套餐启用状态
     * @param id 套餐ID
     * @param isActive 是否启用
     * @return 是否设置成功
     */
    boolean setActive(Long id, Boolean isActive);

    /**
     * 更新排序顺序
     * @param id 套餐ID
     * @param sortOrder 排序顺序
     * @return 是否更新成功
     */
    boolean updateSortOrder(Long id, Integer sortOrder);

    /**
     * 启用服务套餐
     * @param id 套餐ID
     * @return 是否启用成功
     */
    boolean enablePackage(Long id);

    /**
     * 禁用服务套餐
     * @param id 套餐ID
     * @return 是否禁用成功
     */
    boolean disablePackage(Long id);

    /**
     * 统计服务套餐总数
     * @return 套餐总数
     */
    long count();

    /**
     * 统计指定摄影师的套餐数量
     * @param photographerId 摄影师ID
     * @return 套餐数量
     */
    long countByPhotographerId(Long photographerId);

    /**
     * 统计指定类型的套餐数量
     * @param category 套餐类型
     * @return 套餐数量
     */
    long countByCategory(ServicePackageCategoryEnum category);
}
