package com.shootsunrise.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.shootsunrise.core.common.mybatis.core.dataobject.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户点赞记录表
 * @author lyj
 * @since 2025-07-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("user_likes")
public class UserLikes extends BaseEntity {

    /**
     * 点赞ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID - 关联users.id
     */
    private Long userId;

    /**
     * 点赞类型：PORTFOLIO-作品，PHOTOGRAPHER-摄影师
     */
    private String targetType;

    /**
     * 目标ID
     */
    private Long targetId;
}
