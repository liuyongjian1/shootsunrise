package com.shootsunrise.core.repository;

import com.shootsunrise.common.api.request.PageRequest;
import com.shootsunrise.common.api.response.PageResult;
import com.shootsunrise.core.entity.PortfolioTagRelations;

import java.util.List;

/**
 * 作品标签关联表 Repository 接口
 * @author lyj
 * @since 2025-07-27
 */
public interface PortfolioTagRelationsRepository {

    /**
     * 根据ID查询关联关系
     * @param id 关联ID
     * @return 关联关系信息
     */
    PortfolioTagRelations findById(Long id);

    /**
     * 根据作品ID查询标签关联
     * @param portfolioId 作品ID
     * @return 标签关联列表
     */
    List<PortfolioTagRelations> findByPortfolioId(Long portfolioId);

    /**
     * 根据标签ID查询作品关联
     * @param tagId 标签ID
     * @return 作品关联列表
     */
    List<PortfolioTagRelations> findByTagId(Long tagId);

    /**
     * 查询作品和标签的关联关系
     * @param portfolioId 作品ID
     * @param tagId 标签ID
     * @return 关联关系
     */
    PortfolioTagRelations findByPortfolioIdAndTagId(Long portfolioId, Long tagId);

    /**
     * 分页查询关联关系列表
     * @param pageRequest 分页请求
     * @return 分页结果
     */
    PageResult<PortfolioTagRelations> findPage(PageRequest pageRequest);

    /**
     * 保存关联关系
     * @param relation 关联关系信息
     * @return 保存后的关联关系信息
     */
    PortfolioTagRelations save(PortfolioTagRelations relation);

    /**
     * 更新关联关系
     * @param relation 关联关系信息
     * @return 更新后的关联关系信息
     */
    PortfolioTagRelations update(PortfolioTagRelations relation);

    /**
     * 删除关联关系
     * @param id 关联ID
     * @return 是否删除成功
     */
    boolean deleteById(Long id);

    /**
     * 删除作品的所有标签关联
     * @param portfolioId 作品ID
     * @return 删除的关联数量
     */
    int deleteByPortfolioId(Long portfolioId);

    /**
     * 删除标签的所有作品关联
     * @param tagId 标签ID
     * @return 删除的关联数量
     */
    int deleteByTagId(Long tagId);

    /**
     * 批量保存关联关系
     * @param relations 关联关系列表
     * @return 保存成功的数量
     */
    int batchSave(List<PortfolioTagRelations> relations);

    /**
     * 统计关联关系总数
     * @return 关联关系总数
     */
    long count();
}
