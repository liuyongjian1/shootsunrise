package com.shootsunrise.core.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shootsunrise.common.api.request.PageRequest;
import com.shootsunrise.common.api.response.PageResult;
import com.shootsunrise.core.entity.PortfolioCategories;
import com.shootsunrise.core.mapper.PortfolioCategoriesMapper;
import com.shootsunrise.core.repository.PortfolioCategoriesRepository;
import org.springframework.stereotype.Repository;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * 作品分类表 Repository 实现类
 * @author lyj
 * @since 2025-07-27
 */
@Repository
public class PortfolioCategoriesRepositoryImpl implements PortfolioCategoriesRepository {

    @Resource
    private PortfolioCategoriesMapper portfolioCategoriesMapper;

    @Override
    public PortfolioCategories findById(Long id) {
        return portfolioCategoriesMapper.selectById(id);
    }

    @Override
    public PortfolioCategories findByName(String name) {
        LambdaQueryWrapper<PortfolioCategories> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PortfolioCategories::getName, name)
               .eq(PortfolioCategories::getIsDelete, 0);
        return portfolioCategoriesMapper.selectOne(wrapper);
    }

    @Override
    public List<PortfolioCategories> findByParentId(Long parentId) {
        LambdaQueryWrapper<PortfolioCategories> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PortfolioCategories::getParentId, parentId)
               .eq(PortfolioCategories::getIsActive, true)
               .eq(PortfolioCategories::getIsDelete, 0)
               .orderByAsc(PortfolioCategories::getSortOrder);
        return portfolioCategoriesMapper.selectList(wrapper);
    }

    @Override
    public List<PortfolioCategories> findTopCategories() {
        LambdaQueryWrapper<PortfolioCategories> wrapper = new LambdaQueryWrapper<>();
        wrapper.isNull(PortfolioCategories::getParentId)
               .eq(PortfolioCategories::getIsActive, true)
               .eq(PortfolioCategories::getIsDelete, 0)
               .orderByAsc(PortfolioCategories::getSortOrder);
        return portfolioCategoriesMapper.selectList(wrapper);
    }

    @Override
    public List<PortfolioCategories> findRootCategories() {
        LambdaQueryWrapper<PortfolioCategories> wrapper = new LambdaQueryWrapper<>();
        wrapper.isNull(PortfolioCategories::getParentId)
               .eq(PortfolioCategories::getIsActive, true)
               .eq(PortfolioCategories::getIsDelete, 0)
               .orderByAsc(PortfolioCategories::getSortOrder);
        return portfolioCategoriesMapper.selectList(wrapper);
    }

    @Override
    public List<PortfolioCategories> findActiveCategories() {
        LambdaQueryWrapper<PortfolioCategories> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PortfolioCategories::getIsActive, true)
               .eq(PortfolioCategories::getIsDelete, 0)
               .orderByAsc(PortfolioCategories::getSortOrder);
        return portfolioCategoriesMapper.selectList(wrapper);
    }

    @Override
    public PageResult<PortfolioCategories> findPage(PageRequest pageRequest) {
        LambdaQueryWrapper<PortfolioCategories> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PortfolioCategories::getIsDelete, 0)
               .orderByAsc(PortfolioCategories::getSortOrder)
               .orderByDesc(PortfolioCategories::getCreateTime);
        return portfolioCategoriesMapper.selectPage(pageRequest, wrapper);
    }

    @Override
    public PortfolioCategories save(PortfolioCategories category) {
        portfolioCategoriesMapper.insert(category);
        return category;
    }

    @Override
    public PortfolioCategories update(PortfolioCategories category) {
        portfolioCategoriesMapper.updateById(category);
        return category;
    }

    @Override
    public boolean deleteById(Long id) {
        PortfolioCategories category = new PortfolioCategories();
        category.setId(id);
        category.setIsDelete(1);
        return portfolioCategoriesMapper.updateById(category) > 0;
    }

    @Override
    public boolean setActive(Long id, Boolean isActive) {
        PortfolioCategories category = new PortfolioCategories();
        category.setId(id);
        category.setIsActive(isActive);
        return portfolioCategoriesMapper.updateById(category) > 0;
    }

    @Override
    public boolean updateSortOrder(Long id, Integer sortOrder) {
        PortfolioCategories category = new PortfolioCategories();
        category.setId(id);
        category.setSortOrder(sortOrder);
        return portfolioCategoriesMapper.updateById(category) > 0;
    }

    @Override
    public boolean enableCategory(Long id) {
        return setActive(id, true);
    }

    @Override
    public boolean disableCategory(Long id) {
        return setActive(id, false);
    }

    @Override
    public long count() {
        LambdaQueryWrapper<PortfolioCategories> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PortfolioCategories::getIsDelete, 0);
        return portfolioCategoriesMapper.selectCount(wrapper);
    }

    @Override
    public long countActiveCategories() {
        LambdaQueryWrapper<PortfolioCategories> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PortfolioCategories::getIsActive, true)
               .eq(PortfolioCategories::getIsDelete, 0);
        return portfolioCategoriesMapper.selectCount(wrapper);
    }

    @Override
    public long countByParentId(Long parentId) {
        LambdaQueryWrapper<PortfolioCategories> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PortfolioCategories::getParentId, parentId)
               .eq(PortfolioCategories::getIsDelete, 0);
        return portfolioCategoriesMapper.selectCount(wrapper);
    }
}
