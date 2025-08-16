package com.shootsunrise.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.shootsunrise.core.common.mybatis.core.dataobject.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 操作日志表
 * @author lyj
 * @since 2025-07-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("operation_logs")
public class OperationLogs extends BaseEntity {

    /**
     * 日志ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 操作用户ID - 关联users.id或admin_users.id
     */
    private Long userId;

    /**
     * 操作类型
     */
    private String operationType;

    /**
     * 操作描述
     */
    private String operationDesc;

    /**
     * 请求方法：GET、POST、PUT、DELETE
     */
    private String requestMethod;

    /**
     * 请求URL
     */
    private String requestUrl;

    /**
     * 请求参数 JSON格式
     */
    private String requestParams;

    /**
     * 响应结果 JSON格式
     */
    private String responseResult;

    /**
     * 操作IP
     */
    private String operationIp;

    /**
     * 用户代理
     */
    private String userAgent;

    /**
     * 操作时间
     */
    private LocalDateTime operationTime;

    /**
     * 执行时长（毫秒）
     */
    private Long executionTime;

    /**
     * 操作状态：SUCCESS-成功，FAILED-失败
     */
    private String status;

    /**
     * 错误信息
     */
    private String errorMessage;
}
