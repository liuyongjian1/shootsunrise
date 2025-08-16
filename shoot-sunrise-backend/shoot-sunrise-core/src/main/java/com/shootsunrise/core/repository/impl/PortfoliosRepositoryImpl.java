package com.shootsunrise.core.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shootsunrise.common.api.request.PageRequest;
import com.shootsunrise.common.api.response.PageResult;
import com.shootsunrise.core.entity.Portfolios;
import com.shootsunrise.core.enums.PortfolioStatusEnum;
import com.shootsunrise.core.mapper.PortfoliosMapper;
import com.shootsunrise.core.repository.PortfoliosRepository;
import org.springframework.stereotype.Repository;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * 作品集表 Repository 实现类
 * @author lyj
 * @since 2025-07-27
 */
@Repository
public class PortfoliosRepositoryImpl implements PortfoliosRepository {

    @Resource
    private PortfoliosMapper portfoliosMapper;

    @Override
    public Portfolios findById(Long id) {
        return portfoliosMapper.selectById(id);
    }

    @Override
    public List<Portfolios> findByPhotographerId(Long photographerId) {
        LambdaQueryWrapper<Portfolios> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Portfolios::getPhotographerId, photographerId)
               .eq(Portfolios::getStatus, 1) // PUBLISHED
               .eq(Portfolios::getIsPrivate, false)
               .eq(Portfolios::getIsDelete, 0)
               .orderByAsc(Portfolios::getSortOrder)
               .orderByDesc(Portfolios::getCreateTime);
        return portfoliosMapper.selectList(wrapper);
    }

    @Override
    public List<Portfolios> findByStatus(PortfolioStatusEnum status) {
        LambdaQueryWrapper<Portfolios> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Portfolios::getStatus, status)
               .eq(Portfolios::getIsDelete, 0)
               .orderByDesc(Portfolios::getCreateTime);
        return portfoliosMapper.selectList(wrapper);
    }

    @Override
    public List<Portfolios> findByCategoryId(Long categoryId) {
        LambdaQueryWrapper<Portfolios> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Portfolios::getCategoryId, categoryId)
               .eq(Portfolios::getStatus, PortfolioStatusEnum.PUBLISHED)
               .eq(Portfolios::getIsPrivate, false)
               .eq(Portfolios::getIsDelete, 0)
               .orderByAsc(Portfolios::getSortOrder)
               .orderByDesc(Portfolios::getCreateTime);
        return portfoliosMapper.selectList(wrapper);
    }

    @Override
    public List<Portfolios> findFeaturedPortfolios() {
        LambdaQueryWrapper<Portfolios> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Portfolios::getIsFeatured, true)
               .eq(Portfolios::getStatus, PortfolioStatusEnum.PUBLISHED)
               .eq(Portfolios::getIsPrivate, false)
               .eq(Portfolios::getIsDelete, 0)
               .orderByAsc(Portfolios::getSortOrder)
               .orderByDesc(Portfolios::getLikesCount);
        return portfoliosMapper.selectList(wrapper);
    }

    @Override
    public PageResult<Portfolios> findPage(PageRequest pageRequest) {
        LambdaQueryWrapper<Portfolios> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Portfolios::getIsDelete, 0)
               .orderByDesc(Portfolios::getCreateTime);
        return portfoliosMapper.selectPage(pageRequest, wrapper);
    }

    @Override
    public List<Portfolios> findByCategory(String category) {
        // TODO: 需要联表查询 portfolio_categories 表根据分类名称查询
        // 暂时返回空列表，后续需要实现联表查询
        LambdaQueryWrapper<Portfolios> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Portfolios::getStyle, category) // 暂时使用 style 字段作为替代
               .eq(Portfolios::getStatus, PortfolioStatusEnum.PUBLISHED)
               .eq(Portfolios::getIsDelete, 0)
               .orderByDesc(Portfolios::getCreateTime);
        return portfoliosMapper.selectList(wrapper);
    }

    @Override
    public List<Portfolios> searchByKeyword(String keyword) {
        LambdaQueryWrapper<Portfolios> wrapper = new LambdaQueryWrapper<>();
        wrapper.and(w -> w.like(Portfolios::getTitle, keyword)
                         .or()
                         .like(Portfolios::getDescription, keyword)
                         .or()
                         .like(Portfolios::getSeoKeywords, keyword))
               .eq(Portfolios::getStatus, 1) // PUBLISHED
               .eq(Portfolios::getIsPrivate, false)
               .eq(Portfolios::getIsDelete, 0)
               .orderByDesc(Portfolios::getViewsCount);
        return portfoliosMapper.selectList(wrapper);
    }

    @Override
    public Portfolios save(Portfolios portfolio) {
        portfoliosMapper.insert(portfolio);
        return portfolio;
    }

    @Override
    public Portfolios update(Portfolios portfolio) {
        portfoliosMapper.updateById(portfolio);
        return portfolio;
    }

    @Override
    public boolean deleteById(Long id) {
        Portfolios portfolio = new Portfolios();
        portfolio.setId(id);
        portfolio.setIsDelete(1);
        return portfoliosMapper.updateById(portfolio) > 0;
    }

    @Override
    public boolean updateStatus(Long id, PortfolioStatusEnum status) {
        Portfolios portfolio = new Portfolios();
        portfolio.setId(id);
        portfolio.setStatus(status);
        return portfoliosMapper.updateById(portfolio) > 0;
    }

    @Override
    public boolean incrementViewCount(Long id) {
        // 1. 查询当前浏览数
        Portfolios current = portfoliosMapper.selectById(id);
        if (current == null) {
            return false;
        }
        
        // 2. 更新浏览数
        Portfolios portfolio = new Portfolios();
        portfolio.setId(id);
        portfolio.setViewsCount(current.getViewsCount() + 1);
        return portfoliosMapper.updateById(portfolio) > 0;
    }

    @Override
    public boolean incrementLikeCount(Long id) {
        // 1. 查询当前点赞数
        Portfolios current = portfoliosMapper.selectById(id);
        if (current == null) {
            return false;
        }
        
        // 2. 更新点赞数
        Portfolios portfolio = new Portfolios();
        portfolio.setId(id);
        portfolio.setLikesCount(current.getLikesCount() + 1);
        return portfoliosMapper.updateById(portfolio) > 0;
    }

    @Override
    public long count() {
        LambdaQueryWrapper<Portfolios> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Portfolios::getIsDelete, 0);
        return portfoliosMapper.selectCount(wrapper);
    }

    @Override
    public long countByPhotographerId(Long photographerId) {
        LambdaQueryWrapper<Portfolios> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Portfolios::getPhotographerId, photographerId)
               .eq(Portfolios::getIsDelete, 0);
        return portfoliosMapper.selectCount(wrapper);
    }

}
