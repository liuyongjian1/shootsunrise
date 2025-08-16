package com.shootsunrise.core.common.mybatis.core.util;

import com.baomidou.mybatisplus.annotation.DbType;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * JDBC 工具类
 * @author lyj
 * @since 2025-07-27
 */
public class JdbcUtils {

    private static DbType dbType = DbType.MYSQL;

    /**
     * 判断连接是否正确
     *
     * @param url      数据源连接
     * @param username 账号
     * @param password 密码
     * @return 是否正确
     */
    public static boolean isConnectionOK(String url, String username, String password) {
        try (Connection ignored = DriverManager.getConnection(url, username, password)) {
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * 获得 URL 对应的 DB 类型
     *
     * @param url URL
     * @return DB 类型
     */
    public static DbType getDbType(String url) {
        return com.baomidou.mybatisplus.extension.toolkit.JdbcUtils.getDbType(url);
    }

    /**
     * 获取数据库类型
     */
    public static DbType getDbType() {
        return dbType;
    }

    /**
     * 设置数据库类型
     */
    public static void setDbType(DbType dbType) {
        JdbcUtils.dbType = dbType;
    }

    /**
     * 判断是否为 SQL Server
     */
    public static boolean isSQLServer(DbType dbType) {
        return DbType.SQL_SERVER == dbType || DbType.SQL_SERVER2005 == dbType;
    }

}
