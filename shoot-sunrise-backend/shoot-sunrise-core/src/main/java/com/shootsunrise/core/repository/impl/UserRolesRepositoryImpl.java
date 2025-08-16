package com.shootsunrise.core.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shootsunrise.common.api.request.PageRequest;
import com.shootsunrise.common.api.response.PageResult;
import com.shootsunrise.core.entity.UserRoles;
import com.shootsunrise.core.enums.RoleTypeEnum;
import com.shootsunrise.core.mapper.UserRolesMapper;
import com.shootsunrise.core.repository.UserRolesRepository;
import org.springframework.stereotype.Repository;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * 用户角色关联表 Repository 实现类
 * @author lyj
 * @since 2025-07-27
 */
@Repository
public class UserRolesRepositoryImpl implements UserRolesRepository {

    @Resource
    private UserRolesMapper userRolesMapper;

    @Override
    public UserRoles findById(Long id) {
        return userRolesMapper.selectById(id);
    }

    @Override
    public List<UserRoles> findByUserId(Long userId) {
        LambdaQueryWrapper<UserRoles> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRoles::getUserId, userId)
               .eq(UserRoles::getIsDelete, 0)
               .orderByDesc(UserRoles::getCreateTime);
        return userRolesMapper.selectList(wrapper);
    }

    @Override
    public List<UserRoles> findActiveRolesByUserId(Long userId) {
        LambdaQueryWrapper<UserRoles> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRoles::getUserId, userId)
               .eq(UserRoles::getIsActive, true)
               .eq(UserRoles::getIsDelete, 0)
               .orderByDesc(UserRoles::getCreateTime);
        return userRolesMapper.selectList(wrapper);
    }

    @Override
    public UserRoles findByUserIdAndRoleType(Long userId, RoleTypeEnum roleType) {
        LambdaQueryWrapper<UserRoles> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRoles::getUserId, userId)
               .eq(UserRoles::getRoleType, roleType)
               .eq(UserRoles::getIsDelete, 0);
        return userRolesMapper.selectOne(wrapper);
    }

    @Override
    public List<UserRoles> findByRoleType(RoleTypeEnum roleType) {
        LambdaQueryWrapper<UserRoles> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRoles::getRoleType, roleType)
               .eq(UserRoles::getIsDelete, 0)
               .orderByDesc(UserRoles::getCreateTime);
        return userRolesMapper.selectList(wrapper);
    }

    @Override
    public List<UserRoles> findByRole(String role) {
        LambdaQueryWrapper<UserRoles> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRoles::getRoleType, RoleTypeEnum.valueOf(role))
               .eq(UserRoles::getIsDelete, 0)
               .orderByDesc(UserRoles::getCreateTime);
        return userRolesMapper.selectList(wrapper);
    }

    @Override
    public PageResult<UserRoles> findPage(PageRequest pageRequest) {
        LambdaQueryWrapper<UserRoles> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRoles::getIsDelete, 0)
               .orderByDesc(UserRoles::getCreateTime);
        return userRolesMapper.selectPage(pageRequest, wrapper);
    }

    @Override
    public UserRoles save(UserRoles userRole) {
        userRolesMapper.insert(userRole);
        return userRole;
    }

    @Override
    public UserRoles update(UserRoles userRole) {
        userRolesMapper.updateById(userRole);
        return userRole;
    }

    @Override
    public boolean deleteById(Long id) {
        UserRoles userRole = new UserRoles();
        userRole.setId(id);
        userRole.setIsDelete(1);
        return userRolesMapper.updateById(userRole) > 0;
    }

    @Override
    public boolean activateRole(Long id) {
        UserRoles userRole = new UserRoles();
        userRole.setId(id);
        userRole.setIsActive(true);
        return userRolesMapper.updateById(userRole) > 0;
    }

    @Override
    public boolean deactivateRole(Long id) {
        UserRoles userRole = new UserRoles();
        userRole.setId(id);
        userRole.setIsActive(false);
        return userRolesMapper.updateById(userRole) > 0;
    }

    @Override
    public long count() {
        LambdaQueryWrapper<UserRoles> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRoles::getIsDelete, 0);
        return userRolesMapper.selectCount(wrapper);
    }

    @Override
    public long countByUserId(Long userId) {
        LambdaQueryWrapper<UserRoles> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRoles::getUserId, userId)
               .eq(UserRoles::getIsDelete, 0);
        return userRolesMapper.selectCount(wrapper);
    }

    @Override
    public long countByRoleType(RoleTypeEnum roleType) {
        LambdaQueryWrapper<UserRoles> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRoles::getRoleType, roleType)
               .eq(UserRoles::getIsDelete, 0);
        return userRolesMapper.selectCount(wrapper);
    }
}
