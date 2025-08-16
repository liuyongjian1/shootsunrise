package com.shootsunrise.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 摄沐晨光微信小程序 - 管理后台启动类
 *
 * @author lyj
 * @since 2025-07-27
 */
@SpringBootApplication
@MapperScan(
    basePackages = {"com.shootsunrise.core.mapper"},
    annotationClass = org.apache.ibatis.annotations.Mapper.class,
    nameGenerator = org.springframework.beans.factory.support.DefaultBeanNameGenerator.class
)
@ComponentScan(basePackages = {"com.shootsunrise.admin", "com.shootsunrise.core", "com.shootsunrise.common"})
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
        System.out.println("=================================");
        System.out.println("摄沐晨光 - 管理后台启动成功！");
        System.out.println("API文档地址: http://localhost:8082/doc.html");
        System.out.println("=================================");
    }
}
