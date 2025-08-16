package com.shootsunrise.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.shootsunrise.core.common.mybatis.core.dataobject.BaseEntity;
import com.shootsunrise.core.enums.PortfolioStatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * 作品集表
 * @author lyj
 * @since 2025-07-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("portfolios")
public class Portfolios extends BaseEntity {

    /**
     * 作品ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 摄影师ID - 关联users.id
     */
    private Long photographerId;

    /**
     * 作品标题
     */
    private String title;

    /**
     * 作品描述
     */
    private String description;

    /**
     * 分类ID - 关联portfolio_categories.id
     */
    private Long categoryId;

    /**
     * 拍摄风格
     */
    private String style;

    /**
     * 拍摄地点
     */
    private String location;

    /**
     * 拍摄日期
     */
    private LocalDate shootDate;

    /**
     * 拍摄参数 JSON格式
     */
    private String cameraInfo;

    /**
     * 封面图片URL
     */
    private String coverImage;

    /**
     * 图片列表 JSON格式
     */
    private String images;

    /**
     * 视频列表 JSON格式
     */
    private String videos;

    /**
     * 点赞数
     */
    private Integer likesCount;

    /**
     * 浏览数
     */
    private Integer viewsCount;

    /**
     * 评论数
     */
    private Integer commentsCount;

    /**
     * 分享数
     */
    private Integer sharesCount;

    /**
     * 是否精选
     */
    private Boolean isFeatured;

    /**
     * 是否私有
     */
    private Boolean isPrivate;

    /**
     * 状态：0-DRAFT（草稿），1-PUBLISHED（已发布），2-HIDDEN（已隐藏）
     */
    private PortfolioStatusEnum status;

    /**
     * 排序权重
     */
    private Integer sortOrder;

    /**
     * SEO关键词
     */
    private String seoKeywords;
}
