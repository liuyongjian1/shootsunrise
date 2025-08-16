package com.shootsunrise.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.shootsunrise.core.common.mybatis.core.dataobject.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 作品分类表
 * @author lyj
 * @since 2025-07-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("portfolio_categories")
public class PortfolioCategories extends BaseEntity {

    /**
     * 分类ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 分类描述
     */
    private String description;

    /**
     * 分类图标
     */
    private String icon;

    /**
     * 排序权重
     */
    private Integer sortOrder;

    /**
     * 父分类ID
     */
    private Long parentId;

    /**
     * 是否激活
     */
    private Boolean isActive;
}
