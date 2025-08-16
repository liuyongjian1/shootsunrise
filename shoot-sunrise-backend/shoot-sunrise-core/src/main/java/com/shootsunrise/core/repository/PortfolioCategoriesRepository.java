package com.shootsunrise.core.repository;

import com.shootsunrise.common.api.request.PageRequest;
import com.shootsunrise.common.api.response.PageResult;
import com.shootsunrise.core.entity.PortfolioCategories;

import java.util.List;

/**
 * 作品分类表 Repository 接口
 * @author lyj
 * @since 2025-07-27
 */
public interface PortfolioCategoriesRepository {

    /**
     * 根据ID查询分类
     * @param id 分类ID
     * @return 分类信息
     */
    PortfolioCategories findById(Long id);

    /**
     * 根据分类名称查询
     * @param name 分类名称
     * @return 分类信息
     */
    PortfolioCategories findByName(String name);

    /**
     * 查询所有启用的分类
     * @return 分类列表
     */
    List<PortfolioCategories> findActiveCategories();

    /**
     * 分页查询分类列表
     * @param pageRequest 分页请求
     * @return 分页结果
     */
    PageResult<PortfolioCategories> findPage(PageRequest pageRequest);

    /**
     * 根据父分类查询子分类
     * @param parentId 父分类ID
     * @return 子分类列表
     */
    List<PortfolioCategories> findByParentId(Long parentId);

    /**
     * 查询顶级分类
     * @return 顶级分类列表
     */
    List<PortfolioCategories> findTopCategories();

    /**
     * 查询根分类
     * @return 根分类列表
     */
    List<PortfolioCategories> findRootCategories();

    /**
     * 保存分类
     * @param category 分类信息
     * @return 保存后的分类信息
     */
    PortfolioCategories save(PortfolioCategories category);

    /**
     * 更新分类
     * @param category 分类信息
     * @return 更新后的分类信息
     */
    PortfolioCategories update(PortfolioCategories category);

    /**
     * 删除分类
     * @param id 分类ID
     * @return 是否删除成功
     */
    boolean deleteById(Long id);

    /**
     * 启用分类
     * @param id 分类ID
     * @return 是否启用成功
     */
    boolean enableCategory(Long id);

    /**
     * 禁用分类
     * @param id 分类ID
     * @return 是否禁用成功
     */
    boolean disableCategory(Long id);

    /**
     * 设置分类启用状态
     * @param id 分类ID
     * @param isActive 是否启用
     * @return 是否设置成功
     */
    boolean setActive(Long id, Boolean isActive);

    /**
     * 更新排序顺序
     * @param id 分类ID
     * @param sortOrder 排序顺序
     * @return 是否更新成功
     */
    boolean updateSortOrder(Long id, Integer sortOrder);

    /**
     * 统计分类总数
     * @return 分类总数
     */
    long count();

    /**
     * 统计启用的分类数量
     * @return 启用的分类数量
     */
    long countActiveCategories();

    /**
     * 统计指定父分类下的子分类数量
     * @param parentId 父分类ID
     * @return 子分类数量
     */
    long countByParentId(Long parentId);
}
