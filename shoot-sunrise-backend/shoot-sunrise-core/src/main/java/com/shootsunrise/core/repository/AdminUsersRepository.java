package com.shootsunrise.core.repository;

import com.shootsunrise.common.api.request.PageRequest;
import com.shootsunrise.common.api.response.PageResult;
import com.shootsunrise.core.entity.AdminUsers;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 管理员用户表 Repository 接口
 * @author lyj
 * @since 2025-07-27
 */
public interface AdminUsersRepository {

    /**
     * 根据ID查询管理员
     * @param id 管理员ID
     * @return 管理员信息
     */
    AdminUsers findById(Long id);

    /**
     * 根据用户名查询管理员
     * @param username 用户名
     * @return 管理员信息
     */
    AdminUsers findByUsername(String username);

    /**
     * 根据邮箱查询管理员
     * @param email 邮箱
     * @return 管理员信息
     */
    AdminUsers findByEmail(String email);

    /**
     * 根据手机号查询管理员
     * @param phone 手机号
     * @return 管理员信息
     */
    AdminUsers findByPhone(String phone);

    /**
     * 根据角色查询管理员列表
     * @param role 角色
     * @return 管理员列表
     */
    List<AdminUsers> findByRole(Integer role);

    /**
     * 根据状态查询管理员列表
     * @param status 状态
     * @return 管理员列表
     */
    List<AdminUsers> findByStatus(Integer status);

    /**
     * 分页查询管理员列表
     * @param pageRequest 分页请求
     * @return 分页结果
     */
    PageResult<AdminUsers> findPage(PageRequest pageRequest);

    /**
     * 保存管理员
     * @param adminUser 管理员信息
     * @return 保存后的管理员信息
     */
    AdminUsers save(AdminUsers adminUser);

    /**
     * 更新管理员
     * @param adminUser 管理员信息
     * @return 更新后的管理员信息
     */
    AdminUsers update(AdminUsers adminUser);

    /**
     * 删除管理员
     * @param id 管理员ID
     * @return 是否删除成功
     */
    boolean deleteById(Long id);

    /**
     * 更新密码
     * @param id 管理员ID
     * @param password 新密码
     * @return 是否更新成功
     */
    boolean updatePassword(Long id, String password);

    /**
     * 更新状态
     * @param id 管理员ID
     * @param status 状态
     * @return 是否更新成功
     */
    boolean updateStatus(Long id, Integer status);

    /**
     * 更新最后登录信息
     * @param id 管理员ID
     * @param loginTime 登录时间
     * @param loginIp 登录IP
     * @return 是否更新成功
     */
    boolean updateLastLogin(Long id, LocalDateTime loginTime, String loginIp);

    /**
     * 统计管理员总数
     * @return 管理员总数
     */
    long count();

    /**
     * 统计指定角色的管理员数量
     * @param role 角色
     * @return 管理员数量
     */
    long countByRole(Integer role);

    /**
     * 统计指定状态的管理员数量
     * @param status 状态
     * @return 管理员数量
     */
    long countByStatus(Integer status);
}
