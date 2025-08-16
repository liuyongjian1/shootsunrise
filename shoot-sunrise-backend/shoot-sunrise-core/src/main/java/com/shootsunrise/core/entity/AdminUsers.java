package com.shootsunrise.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.shootsunrise.core.common.mybatis.core.dataobject.BaseEntity;
import com.shootsunrise.core.enums.AdminRoleEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 管理员用户表
 * @author lyj
 * @since 2025-07-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("admin_users")
public class AdminUsers extends BaseEntity {

    /**
     * 管理员ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码(加密)
     */
    private String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 角色：0-超级管理员，1-系统管理员
     */
    private AdminRoleEnum role;

    /**
     * 权限列表 JSON格式
     */
    private String permissions;

    /**
     * 状态：0-禁用，1-正常
     */
    private Integer status;

    /**
     * 最后登录时间
     */
    private LocalDateTime lastLoginAt;

    /**
     * 最后登录IP
     */
    private String lastLoginIp;
}
