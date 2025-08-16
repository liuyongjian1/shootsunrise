package com.shootsunrise.core.repository;

import com.shootsunrise.common.api.request.PageRequest;
import com.shootsunrise.common.api.response.PageResult;
import com.shootsunrise.core.entity.PortfolioTags;

import java.util.List;

/**
 * 作品标签表 Repository 接口
 * @author lyj
 * @since 2025-07-27
 */
public interface PortfolioTagsRepository {

    /**
     * 根据ID查询标签
     * @param id 标签ID
     * @return 标签信息
     */
    PortfolioTags findById(Long id);

    /**
     * 根据标签名称查询
     * @param name 标签名称
     * @return 标签信息
     */
    PortfolioTags findByName(String name);

    /**
     * 查询热门标签
     * @param limit 限制数量
     * @return 热门标签列表
     */
    List<PortfolioTags> findPopularTags(Integer limit);

    /**
     * 分页查询标签列表
     * @param pageRequest 分页请求
     * @return 分页结果
     */
    PageResult<PortfolioTags> findPage(PageRequest pageRequest);

    /**
     * 根据关键词搜索标签
     * @param keyword 搜索关键词
     * @return 标签列表
     */
    List<PortfolioTags> searchByKeyword(String keyword);

    /**
     * 保存标签
     * @param tag 标签信息
     * @return 保存后的标签信息
     */
    PortfolioTags save(PortfolioTags tag);

    /**
     * 更新标签
     * @param tag 标签信息
     * @return 更新后的标签信息
     */
    PortfolioTags update(PortfolioTags tag);

    /**
     * 删除标签
     * @param id 标签ID
     * @return 是否删除成功
     */
    boolean deleteById(Long id);

    /**
     * 增加使用次数
     * @param id 标签ID
     * @return 是否更新成功
     */
    boolean incrementUsageCount(Long id);

    /**
     * 减少使用次数
     * @param id 标签ID
     * @return 是否更新成功
     */
    boolean decrementUsageCount(Long id);

    /**
     * 统计标签总数
     * @return 标签总数
     */
    long count();
}
