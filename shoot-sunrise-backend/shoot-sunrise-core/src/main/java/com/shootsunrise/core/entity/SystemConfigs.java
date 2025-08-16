package com.shootsunrise.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.shootsunrise.core.common.mybatis.core.dataobject.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统配置表
 * @author lyj
 * @since 2025-07-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("system_configs")
public class SystemConfigs extends BaseEntity {

    /**
     * 配置ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 配置键
     */
    private String configKey;

    /**
     * 配置值
     */
    private String configValue;

    /**
     * 配置类型：STRING-字符串，NUMBER-数字，BOOLEAN-布尔，JSON-JSON对象
     */
    private String configType;

    /**
     * 配置描述
     */
    private String description;

    /**
     * 是否公开
     */
    private Boolean isPublic;
}
