package com.shootsunrise.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.shootsunrise.core.common.mybatis.core.dataobject.BaseEntity;
import com.shootsunrise.core.enums.CertificationStatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 摄影师认证信息表
 * @author lyj
 * @since 2025-07-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("photographer_profiles")
public class PhotographerProfiles extends BaseEntity {

    /**
     * 摄影师资料ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID - 关联users.id
     */
    private Long userId;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 身份证号（加密存储）
     */
    private String idCard;

    /**
     * 个人简介
     */
    private String bio;

    /**
     * 擅长领域 JSON格式 ["人像", "风景", "婚纱"]
     */
    private String specialties;

    /**
     * 从业年限
     */
    private Integer experienceYears;

    /**
     * 认证状态：PENDING-待审核，APPROVED-已通过，REJECTED-已拒绝
     */
    private CertificationStatusEnum certificationStatus;

    /**
     * 认证时间
     */
    private LocalDateTime certificationTime;

    /**
     * 拒绝原因
     */
    private String rejectionReason;

    /**
     * 服务区域 JSON格式 ["北京", "上海"]
     */
    private String serviceAreas;

    /**
     * 微信号
     */
    private String contactWechat;

    /**
     * 联系电话
     */
    private String contactPhone;

    /**
     * 作品集封面
     */
    private String portfolioCover;

    /**
     * 评分(1.00-5.00)
     */
    private BigDecimal rating;

    /**
     * 总订单数
     */
    private Integer totalOrders;

    /**
     * 完成订单数
     */
    private Integer completedOrders;

    /**
     * 总收入
     */
    private BigDecimal totalRevenue;

    /**
     * 是否推荐摄影师
     */
    private Boolean isFeatured;

    /**
     * 排序权重
     */
    private Integer sortOrder;
}
