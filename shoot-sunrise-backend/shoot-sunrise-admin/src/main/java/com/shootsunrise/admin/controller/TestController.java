package com.shootsunrise.admin.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 管理后台测试控制器
 * @author lyj
 * @since 2025-07-27
 */
@Tag(name = "测试接口", description = "管理后台测试接口")
@RestController
@RequestMapping("/test")
public class TestController {

    @Operation(summary = "健康检查", description = "检查管理后台服务是否正常运行")
    @GetMapping("/health")
    public Map<String, Object> health() {
        Map<String, Object> result = new HashMap<>();
        result.put("status", "UP");
        result.put("service", "shoot-sunrise-admin");
        result.put("timestamp", LocalDateTime.now());
        result.put("message", "管理后台服务运行正常");
        return result;
    }

    @Operation(summary = "版本信息", description = "获取管理后台版本信息")
    @GetMapping("/version")
    public Map<String, Object> version() {
        Map<String, Object> result = new HashMap<>();
        result.put("name", "个人摄影师微信小程序-管理后台");
        result.put("version", "1.0.0");
        result.put("description", "个人摄影师微信小程序管理后台系统，提供用户管理、内容审核、数据统计等功能");
        result.put("buildTime", LocalDateTime.now());
        return result;
    }
}
