package com.shootsunrise.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.shootsunrise.core.common.mybatis.core.dataobject.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户关注关系表
 * @author lyj
 * @since 2025-07-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("user_follows")
public class UserFollows extends BaseEntity {

    /**
     * 关注ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 关注者ID - 关联users.id
     */
    private Long followerId;

    /**
     * 被关注者ID - 关联users.id
     */
    private Long followingId;
}
