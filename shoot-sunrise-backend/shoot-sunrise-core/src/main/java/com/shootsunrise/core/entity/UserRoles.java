package com.shootsunrise.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.shootsunrise.core.common.mybatis.core.dataobject.BaseEntity;
import com.shootsunrise.core.enums.RoleTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 用户角色关联表
 * @author lyj
 * @since 2025-07-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("user_roles")
public class UserRoles extends BaseEntity {

    /**
     * 角色关联ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID - 关联users.id
     */
    private Long userId;

    /**
     * 角色类型：0-普通用户，1-摄影师，2-管理员
     */
    private RoleTypeEnum roleType;

    /**
     * 是否激活
     */
    private Boolean isActive;

    /**
     * 激活时间
     */
    private LocalDateTime activatedAt;
}
