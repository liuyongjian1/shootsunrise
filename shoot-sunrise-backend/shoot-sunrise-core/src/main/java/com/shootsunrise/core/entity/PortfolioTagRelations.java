package com.shootsunrise.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 作品标签关联表
 * @author lyj
 * @since 2025-07-27
 */
@Data
@TableName("portfolio_tag_relations")
public class PortfolioTagRelations {

    /**
     * 关联ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 作品ID - 关联portfolios.id
     */
    private Long portfolioId;

    /**
     * 标签ID - 关联portfolio_tags.id
     */
    private Long tagId;

    /**
     * 创建人ID
     */
    private Long creator;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新人ID
     */
    private Long updater;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 是否删除：0-未删除，1-已删除
     */
    private Integer isDelete;
}
