package com.shootsunrise.core.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shootsunrise.common.api.request.PageRequest;
import com.shootsunrise.common.api.response.PageResult;
import com.shootsunrise.core.entity.PortfolioTagRelations;
import com.shootsunrise.core.mapper.PortfolioTagRelationsMapper;
import com.shootsunrise.core.repository.PortfolioTagRelationsRepository;
import org.springframework.stereotype.Repository;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * 作品标签关联表 Repository 实现类
 * @author lyj
 * @since 2025-07-27
 */
@Repository
public class PortfolioTagRelationsRepositoryImpl implements PortfolioTagRelationsRepository {

    @Resource
    private PortfolioTagRelationsMapper portfolioTagRelationsMapper;

    @Override
    public PortfolioTagRelations findById(Long id) {
        return portfolioTagRelationsMapper.selectById(id);
    }

    @Override
    public List<PortfolioTagRelations> findByPortfolioId(Long portfolioId) {
        LambdaQueryWrapper<PortfolioTagRelations> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PortfolioTagRelations::getPortfolioId, portfolioId)
               .eq(PortfolioTagRelations::getIsDelete, 0)
               .orderByDesc(PortfolioTagRelations::getCreateTime);
        return portfolioTagRelationsMapper.selectList(wrapper);
    }

    @Override
    public List<PortfolioTagRelations> findByTagId(Long tagId) {
        LambdaQueryWrapper<PortfolioTagRelations> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PortfolioTagRelations::getTagId, tagId)
               .eq(PortfolioTagRelations::getIsDelete, 0)
               .orderByDesc(PortfolioTagRelations::getCreateTime);
        return portfolioTagRelationsMapper.selectList(wrapper);
    }

    @Override
    public PortfolioTagRelations findByPortfolioIdAndTagId(Long portfolioId, Long tagId) {
        LambdaQueryWrapper<PortfolioTagRelations> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PortfolioTagRelations::getPortfolioId, portfolioId)
               .eq(PortfolioTagRelations::getTagId, tagId)
               .eq(PortfolioTagRelations::getIsDelete, 0);
        return portfolioTagRelationsMapper.selectOne(wrapper);
    }

    @Override
    public PageResult<PortfolioTagRelations> findPage(PageRequest pageRequest) {
        LambdaQueryWrapper<PortfolioTagRelations> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PortfolioTagRelations::getIsDelete, 0)
               .orderByDesc(PortfolioTagRelations::getCreateTime);
        return portfolioTagRelationsMapper.selectPage(pageRequest, wrapper);
    }

    @Override
    public PortfolioTagRelations save(PortfolioTagRelations relation) {
        portfolioTagRelationsMapper.insert(relation);
        return relation;
    }

    @Override
    public PortfolioTagRelations update(PortfolioTagRelations relation) {
        portfolioTagRelationsMapper.updateById(relation);
        return relation;
    }

    @Override
    public boolean deleteById(Long id) {
        PortfolioTagRelations relation = new PortfolioTagRelations();
        relation.setId(id);
        relation.setIsDelete(1);
        return portfolioTagRelationsMapper.updateById(relation) > 0;
    }

    @Override
    public int deleteByPortfolioId(Long portfolioId) {
        // 1. 查询所有关联关系
        List<PortfolioTagRelations> relations = findByPortfolioId(portfolioId);
        if (relations.isEmpty()) {
            return 0;
        }
        
        // 2. 批量软删除
        int count = 0;
        for (PortfolioTagRelations relation : relations) {
            if (deleteById(relation.getId())) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int deleteByTagId(Long tagId) {
        // 1. 查询所有关联关系
        List<PortfolioTagRelations> relations = findByTagId(tagId);
        if (relations.isEmpty()) {
            return 0;
        }
        
        // 2. 批量软删除
        int count = 0;
        for (PortfolioTagRelations relation : relations) {
            if (deleteById(relation.getId())) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int batchSave(List<PortfolioTagRelations> relations) {
        if (relations == null || relations.isEmpty()) {
            return 0;
        }
        
        int count = 0;
        for (PortfolioTagRelations relation : relations) {
            // 检查是否已存在
            PortfolioTagRelations existing = findByPortfolioIdAndTagId(
                relation.getPortfolioId(), relation.getTagId());
            if (existing == null) {
                save(relation);
                count++;
            }
        }
        return count;
    }

    @Override
    public long count() {
        LambdaQueryWrapper<PortfolioTagRelations> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PortfolioTagRelations::getIsDelete, 0);
        return portfolioTagRelationsMapper.selectCount(wrapper);
    }
}
