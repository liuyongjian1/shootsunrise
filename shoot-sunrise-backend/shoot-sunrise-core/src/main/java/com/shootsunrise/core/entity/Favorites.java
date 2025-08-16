package com.shootsunrise.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.shootsunrise.core.common.mybatis.core.dataobject.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 收藏记录表
 * @author lyj
 * @since 2025-07-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("favorites")
public class Favorites extends BaseEntity {

    /**
     * 收藏ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID - 关联users.id
     */
    private Long userId;

    /**
     * 收藏类型：PHOTOGRAPHER-摄影师，PORTFOLIO-作品，PACKAGE-套餐
     */
    private String targetType;

    /**
     * 目标ID
     */
    private Long targetId;
}
