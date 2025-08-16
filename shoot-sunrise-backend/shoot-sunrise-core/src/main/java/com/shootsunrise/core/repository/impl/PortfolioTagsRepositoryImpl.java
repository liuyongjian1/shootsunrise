package com.shootsunrise.core.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shootsunrise.common.api.request.PageRequest;
import com.shootsunrise.common.api.response.PageResult;
import com.shootsunrise.core.entity.PortfolioTags;
import com.shootsunrise.core.mapper.PortfolioTagsMapper;
import com.shootsunrise.core.repository.PortfolioTagsRepository;
import org.springframework.stereotype.Repository;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * 作品标签表 Repository 实现类
 * @author lyj
 * @since 2025-07-27
 */
@Repository
public class PortfolioTagsRepositoryImpl implements PortfolioTagsRepository {

    @Resource
    private PortfolioTagsMapper portfolioTagsMapper;

    @Override
    public PortfolioTags findById(Long id) {
        return portfolioTagsMapper.selectById(id);
    }

    @Override
    public PortfolioTags findByName(String name) {
        LambdaQueryWrapper<PortfolioTags> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PortfolioTags::getName, name)
               .eq(PortfolioTags::getIsDelete, 0);
        return portfolioTagsMapper.selectOne(wrapper);
    }

    @Override
    public List<PortfolioTags> findPopularTags(Integer limit) {
        LambdaQueryWrapper<PortfolioTags> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PortfolioTags::getIsDelete, 0)
               .orderByDesc(PortfolioTags::getUsageCount)
               .last("LIMIT " + limit);
        return portfolioTagsMapper.selectList(wrapper);
    }

    @Override
    public PageResult<PortfolioTags> findPage(PageRequest pageRequest) {
        LambdaQueryWrapper<PortfolioTags> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PortfolioTags::getIsDelete, 0)
               .orderByDesc(PortfolioTags::getUsageCount)
               .orderByDesc(PortfolioTags::getCreateTime);
        return portfolioTagsMapper.selectPage(pageRequest, wrapper);
    }

    @Override
    public List<PortfolioTags> searchByKeyword(String keyword) {
        LambdaQueryWrapper<PortfolioTags> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(PortfolioTags::getName, keyword)
               .eq(PortfolioTags::getIsDelete, 0)
               .orderByDesc(PortfolioTags::getUsageCount);
        return portfolioTagsMapper.selectList(wrapper);
    }

    @Override
    public PortfolioTags save(PortfolioTags tag) {
        portfolioTagsMapper.insert(tag);
        return tag;
    }

    @Override
    public PortfolioTags update(PortfolioTags tag) {
        portfolioTagsMapper.updateById(tag);
        return tag;
    }

    @Override
    public boolean deleteById(Long id) {
        PortfolioTags tag = new PortfolioTags();
        tag.setId(id);
        tag.setIsDelete(1);
        return portfolioTagsMapper.updateById(tag) > 0;
    }

    @Override
    public boolean incrementUsageCount(Long id) {
        // 1. 查询当前使用次数
        PortfolioTags current = portfolioTagsMapper.selectById(id);
        if (current == null) {
            return false;
        }
        
        // 2. 更新使用次数
        PortfolioTags tag = new PortfolioTags();
        tag.setId(id);
        tag.setUsageCount(current.getUsageCount() + 1);
        return portfolioTagsMapper.updateById(tag) > 0;
    }

    @Override
    public boolean decrementUsageCount(Long id) {
        // 1. 查询当前使用次数
        PortfolioTags current = portfolioTagsMapper.selectById(id);
        if (current == null || current.getUsageCount() <= 0) {
            return false;
        }
        
        // 2. 更新使用次数
        PortfolioTags tag = new PortfolioTags();
        tag.setId(id);
        tag.setUsageCount(current.getUsageCount() - 1);
        return portfolioTagsMapper.updateById(tag) > 0;
    }

    @Override
    public long count() {
        LambdaQueryWrapper<PortfolioTags> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PortfolioTags::getIsDelete, 0);
        return portfolioTagsMapper.selectCount(wrapper);
    }
}
