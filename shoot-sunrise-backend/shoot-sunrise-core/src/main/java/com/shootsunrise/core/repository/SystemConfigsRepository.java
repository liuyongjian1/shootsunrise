package com.shootsunrise.core.repository;

import com.shootsunrise.common.api.request.PageRequest;
import com.shootsunrise.common.api.response.PageResult;

import java.util.List;

/**
 * 系统配置表 Repository 接口
 * @author lyj
 * @since 2025-07-27
 */
public interface SystemConfigsRepository {

    /**
     * 根据ID查询系统配置
     * @param id 配置ID
     * @return 系统配置信息
     */
    SystemConfigs findById(Long id);

    /**
     * 根据配置键查询
     * @param configKey 配置键
     * @return 系统配置信息
     */
    SystemConfigs findByConfigKey(String configKey);

    /**
     * 查询所有公开配置
     * @return 公开配置列表
     */
    List<SystemConfigs> findPublicConfigs();

    /**
     * 分页查询系统配置列表
     * @param pageRequest 分页请求
     * @return 分页结果
     */
    PageResult<SystemConfigs> findPage(PageRequest pageRequest);

    /**
     * 根据配置类型查询
     * @param configType 配置类型
     * @return 配置列表
     */
    List<SystemConfigs> findByConfigType(String configType);

    /**
     * 根据配置键前缀查询
     * @param keyPrefix 配置键前缀
     * @return 配置列表
     */
    List<SystemConfigs> findByKeyPrefix(String keyPrefix);

    /**
     * 保存系统配置
     * @param config 系统配置信息
     * @return 保存后的系统配置信息
     */
    SystemConfigs save(SystemConfigs config);

    /**
     * 更新系统配置
     * @param config 系统配置信息
     * @return 更新后的系统配置信息
     */
    SystemConfigs update(SystemConfigs config);

    /**
     * 删除系统配置
     * @param id 配置ID
     * @return 是否删除成功
     */
    boolean deleteById(Long id);

    /**
     * 根据配置键删除
     * @param configKey 配置键
     * @return 是否删除成功
     */
    boolean deleteByConfigKey(String configKey);

    /**
     * 更新配置值
     * @param configKey 配置键
     * @param configValue 配置值
     * @return 是否更新成功
     */
    boolean updateConfigValue(String configKey, String configValue);

    /**
     * 统计系统配置总数
     * @return 配置总数
     */
    long count();

    /**
     * 统计公开配置数量
     * @return 公开配置数量
     */
    long countPublicConfigs();
}
