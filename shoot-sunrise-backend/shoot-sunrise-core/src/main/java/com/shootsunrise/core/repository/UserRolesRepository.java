package com.shootsunrise.core.repository;

import com.shootsunrise.common.api.request.PageRequest;
import com.shootsunrise.common.api.response.PageResult;
import com.shootsunrise.core.entity.UserRoles;
import com.shootsunrise.core.enums.RoleTypeEnum;

import java.util.List;

/**
 * 用户角色关联表 Repository 接口
 * @author lyj
 * @since 2025-07-27
 */
public interface UserRolesRepository {

    /**
     * 根据ID查询用户角色
     * @param id 用户角色ID
     * @return 用户角色信息
     */
    UserRoles findById(Long id);

    /**
     * 根据用户ID查询角色列表
     * @param userId 用户ID
     * @return 角色列表
     */
    List<UserRoles> findByUserId(Long userId);

    /**
     * 根据用户ID和角色类型查询用户角色
     * @param userId 用户ID
     * @param roleType 角色类型
     * @return 用户角色信息
     */
    UserRoles findByUserIdAndRoleType(Long userId, RoleTypeEnum roleType);

    /**
     * 根据角色类型查询用户列表
     * @param roleType 角色类型
     * @return 用户角色列表
     */
    List<UserRoles> findByRoleType(RoleTypeEnum roleType);

    /**
     * 根据角色查询用户列表
     * @param role 角色
     * @return 用户角色列表
     */
    List<UserRoles> findByRole(String role);

    /**
     * 分页查询用户角色列表
     * @param pageRequest 分页请求
     * @return 分页结果
     */
    PageResult<UserRoles> findPage(PageRequest pageRequest);

    /**
     * 查询激活的用户角色
     * @param userId 用户ID
     * @return 激活的角色列表
     */
    List<UserRoles> findActiveRolesByUserId(Long userId);

    /**
     * 保存用户角色
     * @param userRole 用户角色信息
     * @return 保存后的用户角色信息
     */
    UserRoles save(UserRoles userRole);

    /**
     * 更新用户角色
     * @param userRole 用户角色信息
     * @return 更新后的用户角色信息
     */
    UserRoles update(UserRoles userRole);

    /**
     * 删除用户角色
     * @param id 用户角色ID
     * @return 是否删除成功
     */
    boolean deleteById(Long id);

    /**
     * 激活用户角色
     * @param id 用户角色ID
     * @return 是否激活成功
     */
    boolean activateRole(Long id);

    /**
     * 停用用户角色
     * @param id 用户角色ID
     * @return 是否停用成功
     */
    boolean deactivateRole(Long id);

    /**
     * 统计用户角色总数
     * @return 用户角色总数
     */
    long count();

    /**
     * 统计指定用户的角色数量
     * @param userId 用户ID
     * @return 角色数量
     */
    long countByUserId(Long userId);

    /**
     * 统计指定角色类型的用户数量
     * @param roleType 角色类型
     * @return 用户数量
     */
    long countByRoleType(RoleTypeEnum roleType);
}
