package com.shootsunrise.core.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shootsunrise.common.api.request.PageRequest;
import com.shootsunrise.common.api.response.PageResult;
import com.shootsunrise.core.entity.SystemConfigs;
import com.shootsunrise.core.mapper.SystemConfigsMapper;
import com.shootsunrise.core.repository.SystemConfigsRepository;
import com.shootsunrise.core.common.mybatis.core.util.PageUtils;
import org.springframework.stereotype.Repository;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * 系统配置表 Repository 实现类
 * @author lyj
 * @since 2025-07-27
 */
@Repository
public class SystemConfigsRepositoryImpl implements SystemConfigsRepository {

    @Resource
    private SystemConfigsMapper systemConfigsMapper;

    @Override
    public SystemConfigs findById(Long id) {
        return systemConfigsMapper.selectById(id);
    }

    @Override
    public SystemConfigs findByConfigKey(String configKey) {
        LambdaQueryWrapper<SystemConfigs> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SystemConfigs::getConfigKey, configKey)
               .eq(SystemConfigs::getIsDelete, 0);
        return systemConfigsMapper.selectOne(wrapper);
    }

    @Override
    public List<SystemConfigs> findByConfigType(String configType) {
        LambdaQueryWrapper<SystemConfigs> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SystemConfigs::getConfigType, configType)
               .eq(SystemConfigs::getIsDelete, 0)
               .orderByAsc(SystemConfigs::getConfigKey);
        return systemConfigsMapper.selectList(wrapper);
    }

    @Override
    public List<SystemConfigs> findByKeyPrefix(String keyPrefix) {
        LambdaQueryWrapper<SystemConfigs> wrapper = new LambdaQueryWrapper<>();
        wrapper.likeRight(SystemConfigs::getConfigKey, keyPrefix)
               .eq(SystemConfigs::getIsDelete, 0)
               .orderByAsc(SystemConfigs::getConfigKey);
        return systemConfigsMapper.selectList(wrapper);
    }

    @Override
    public List<SystemConfigs> findPublicConfigs() {
        LambdaQueryWrapper<SystemConfigs> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SystemConfigs::getIsPublic, true)
               .eq(SystemConfigs::getIsDelete, 0)
               .orderByAsc(SystemConfigs::getConfigKey);
        return systemConfigsMapper.selectList(wrapper);
    }

    @Override
    public PageResult<SystemConfigs> findPage(PageRequest pageRequest) {
        LambdaQueryWrapper<SystemConfigs> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SystemConfigs::getIsDelete, 0)
               .orderByAsc(SystemConfigs::getConfigKey);
        return PageUtils.selectPage(systemConfigsMapper, pageRequest, wrapper);
    }

    @Override
    public SystemConfigs save(SystemConfigs config) {
        systemConfigsMapper.insert(config);
        return config;
    }

    @Override
    public SystemConfigs update(SystemConfigs config) {
        systemConfigsMapper.updateById(config);
        return config;
    }

    @Override
    public boolean deleteById(Long id) {
        SystemConfigs config = new SystemConfigs();
        config.setId(id);
        config.setIsDelete(1);
        return systemConfigsMapper.updateById(config) > 0;
    }

    @Override
    public boolean deleteByConfigKey(String configKey) {
        // 1. 查询配置
        SystemConfigs config = findByConfigKey(configKey);
        if (config == null) {
            return false;
        }

        // 2. 软删除配置
        return deleteById(config.getId());
    }

    @Override
    public boolean updateConfigValue(String configKey, String configValue) {
        // 1. 查询配置
        SystemConfigs config = findByConfigKey(configKey);
        if (config == null) {
            return false;
        }
        
        // 2. 更新配置值
        config.setConfigValue(configValue);
        return systemConfigsMapper.updateById(config) > 0;
    }


    @Override
    public long count() {
        LambdaQueryWrapper<SystemConfigs> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SystemConfigs::getIsDelete, 0);
        return systemConfigsMapper.selectCount(wrapper);
    }

    @Override
    public long countPublicConfigs() {
        LambdaQueryWrapper<SystemConfigs> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SystemConfigs::getIsPublic, true)
               .eq(SystemConfigs::getIsDelete, 0);
        return systemConfigsMapper.selectCount(wrapper);
    }

}
