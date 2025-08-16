package com.shootsunrise.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.shootsunrise.core.common.mybatis.core.dataobject.BaseEntity;
import com.shootsunrise.core.enums.GenderEnum;
import com.shootsunrise.core.enums.UserRoleEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 用户基础信息表
 * @author lyj
 * @since 2025-07-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("users")
public class Users extends BaseEntity {

    /**
     * 用户ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 微信OpenID
     */
    private String openid;

    /**
     * 微信UnionID
     */
    private String unionid;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 头像URL
     */
    private String avatarUrl;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 性别：0-未知，1-男，2-女
     */
    private GenderEnum gender;

    /**
     * 城市
     */
    private String city;

    /**
     * 省份
     */
    private String province;

    /**
     * 国家
     */
    private String country;

    /**
     * 当前角色：0-CLIENT（客户），1-PHOTOGRAPHER（摄影师）
     */
    private UserRoleEnum currentRole;

    /**
     * 状态：0-禁用，1-正常
     */
    private Integer status;

    /**
     * 最后登录时间
     */
    private LocalDateTime lastLoginAt;

    /**
     * 登录次数
     */
    private Integer loginCount;
}
