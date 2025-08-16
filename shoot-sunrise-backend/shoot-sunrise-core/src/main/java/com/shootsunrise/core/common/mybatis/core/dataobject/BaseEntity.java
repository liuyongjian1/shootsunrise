package com.shootsunrise.core.common.mybatis.core.dataobject;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 基础实体对象
 * 包含通用字段：创建人、创建时间、更新人、更新时间、逻辑删除标识
 *
 * @author lyj
 * @since 2025-07-27
 */
@Data
public abstract class BaseEntity implements Serializable {

    /**
     * 创建人ID
     */
    @TableField(fill = FieldFill.INSERT, jdbcType = JdbcType.BIGINT)
    private Long creator;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新人ID
     */
    @TableField(fill = FieldFill.INSERT_UPDATE, jdbcType = JdbcType.BIGINT)
    private Long updater;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 是否删除：0-未删除，1-已删除
     */
    @TableLogic
    @TableField("is_delete")
    private Integer isDelete;

    /**
     * 清空审计字段，避免前端直接传递这些字段
     */
    public void cleanAuditFields(){
        this.creator = null;
        this.createTime = null;
        this.updater = null;
        this.updateTime = null;
    }

}
