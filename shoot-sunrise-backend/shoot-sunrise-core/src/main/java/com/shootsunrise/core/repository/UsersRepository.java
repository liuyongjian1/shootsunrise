package com.shootsunrise.core.repository;

import com.shootsunrise.common.api.request.PageRequest;
import com.shootsunrise.common.api.response.PageResult;

import java.util.List;

/**
 * 用户基础信息表 Repository 接口
 * @author lyj
 * @since 2025-07-27
 */
public interface UsersRepository {

    /**
     * 根据ID查询用户
     * @param id 用户ID
     * @return 用户信息
     */
    Users findById(Long id);

    /**
     * 根据OpenID查询用户
     * @param openid 微信OpenID
     * @return 用户信息
     */
    Users findByOpenid(String openid);

    /**
     * 根据手机号查询用户
     * @param phone 手机号
     * @return 用户信息
     */
    Users findByPhone(String phone);

    /**
     * 分页查询用户列表
     * @param pageRequest 分页请求
     * @return 分页结果
     */
    PageResult<Users> findPage(PageRequest pageRequest);

    /**
     * 根据状态查询用户列表
     * @param status 用户状态
     * @return 用户列表
     */
    List<Users> findByStatus(Integer status);

    /**
     * 保存用户
     * @param user 用户信息
     * @return 保存后的用户信息
     */
    Users save(Users user);

    /**
     * 更新用户
     * @param user 用户信息
     * @return 更新后的用户信息
     */
    Users update(Users user);

    /**
     * 删除用户
     * @param id 用户ID
     * @return 是否删除成功
     */
    boolean deleteById(Long id);

    /**
     * 统计用户总数
     * @return 用户总数
     */
    long count();

    /**
     * 统计指定状态的用户数量
     * @param status 用户状态
     * @return 用户数量
     */
    long countByStatus(Integer status);
}
