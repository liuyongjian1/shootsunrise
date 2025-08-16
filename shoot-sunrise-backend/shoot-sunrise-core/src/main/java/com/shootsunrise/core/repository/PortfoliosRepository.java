package com.shootsunrise.core.repository;

import com.shootsunrise.common.api.request.PageRequest;
import com.shootsunrise.common.api.response.PageResult;
import com.shootsunrise.core.entity.Portfolios;
import com.shootsunrise.core.enums.PortfolioStatusEnum;

import java.util.List;

/**
 * 作品集表 Repository 接口
 * @author lyj
 * @since 2025-07-27
 */
public interface PortfoliosRepository {

    /**
     * 根据ID查询作品
     * @param id 作品ID
     * @return 作品信息
     */
    Portfolios findById(Long id);

    /**
     * 根据摄影师ID查询作品列表
     * @param photographerId 摄影师ID
     * @return 作品列表
     */
    List<Portfolios> findByPhotographerId(Long photographerId);

    /**
     * 分页查询作品列表
     * @param pageRequest 分页请求
     * @return 分页结果
     */
    PageResult<Portfolios> findPage(PageRequest pageRequest);

    /**
     * 根据状态查询作品列表
     * @param status 作品状态
     * @return 作品列表
     */
    List<Portfolios> findByStatus(PortfolioStatusEnum status);

    /**
     * 根据分类ID查询作品列表
     * @param categoryId 分类ID
     * @return 作品列表
     */
    List<Portfolios> findByCategoryId(Long categoryId);

    /**
     * 查询精选作品列表
     * @return 精选作品列表
     */
    List<Portfolios> findFeaturedPortfolios();

    /**
     * 根据分类查询作品
     * @param category 作品分类
     * @return 作品列表
     */
    List<Portfolios> findByCategory(String category);

    /**
     * 根据关键词搜索作品
     * @param keyword 搜索关键词
     * @return 作品列表
     */
    List<Portfolios> searchByKeyword(String keyword);

    /**
     * 保存作品
     * @param portfolio 作品信息
     * @return 保存后的作品信息
     */
    Portfolios save(Portfolios portfolio);

    /**
     * 更新作品
     * @param portfolio 作品信息
     * @return 更新后的作品信息
     */
    Portfolios update(Portfolios portfolio);

    /**
     * 删除作品
     * @param id 作品ID
     * @return 是否删除成功
     */
    boolean deleteById(Long id);

    /**
     * 更新作品状态
     * @param id 作品ID
     * @param status 作品状态
     * @return 是否更新成功
     */
    boolean updateStatus(Long id, PortfolioStatusEnum status);

    /**
     * 增加浏览数
     * @param id 作品ID
     * @return 是否更新成功
     */
    boolean incrementViewCount(Long id);

    /**
     * 增加点赞数
     * @param id 作品ID
     * @return 是否更新成功
     */
    boolean incrementLikeCount(Long id);

    /**
     * 统计作品总数
     * @return 作品总数
     */
    long count();

    /**
     * 统计指定摄影师的作品数量
     * @param photographerId 摄影师ID
     * @return 作品数量
     */
    long countByPhotographerId(Long photographerId);
}
