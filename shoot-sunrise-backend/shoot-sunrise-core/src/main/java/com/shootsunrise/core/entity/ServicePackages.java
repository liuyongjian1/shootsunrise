package com.shootsunrise.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.shootsunrise.core.common.mybatis.core.dataobject.BaseEntity;
import com.shootsunrise.core.enums.ServicePackageCategoryEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 服务套餐表
 * @author lyj
 * @since 2025-07-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("service_packages")
public class ServicePackages extends BaseEntity {

    /**
     * 套餐ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 摄影师ID - 关联users.id
     */
    private Long photographerId;

    /**
     * 套餐名称
     */
    private String name;

    /**
     * 套餐描述
     */
    private String description;

    /**
     * 套餐类型：0-WEDDING（婚纱），1-PORTRAIT（人像），2-COMMERCIAL（商业），3-EVENT（活动），4-OTHER（其他）
     */
    private ServicePackageCategoryEnum category;

    /**
     * 套餐价格
     */
    private BigDecimal price;

    /**
     * 拍摄时长（分钟）
     */
    private Integer duration;

    /**
     * 照片数量
     */
    private Integer photoCount;

    /**
     * 包含服务 JSON格式
     */
    private String includes;

    /**
     * 不包含服务 JSON格式
     */
    private String excludes;



    /**
     * 是否激活
     */
    private Boolean isActive;

    /**
     * 排序权重
     */
    private Integer sortOrder;
}
