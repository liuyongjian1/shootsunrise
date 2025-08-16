package com.shootsunrise.core.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shootsunrise.common.api.request.PageRequest;
import com.shootsunrise.common.api.response.PageResult;
import com.shootsunrise.core.entity.AdminUsers;
import com.shootsunrise.core.mapper.AdminUsersMapper;
import com.shootsunrise.core.repository.AdminUsersRepository;
import com.shootsunrise.core.common.mybatis.core.util.PageUtils;
import org.springframework.stereotype.Repository;

import jakarta.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 管理员用户表 Repository 实现类
 * @author lyj
 * @since 2025-07-27
 */
@Repository
public class AdminUsersRepositoryImpl implements AdminUsersRepository {

    @Resource
    private AdminUsersMapper adminUsersMapper;

    @Override
    public AdminUsers findById(Long id) {
        return adminUsersMapper.selectById(id);
    }

    @Override
    public AdminUsers findByUsername(String username) {
        LambdaQueryWrapper<AdminUsers> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AdminUsers::getUsername, username)
               .eq(AdminUsers::getIsDelete, 0);
        return adminUsersMapper.selectOne(wrapper);
    }

    @Override
    public AdminUsers findByEmail(String email) {
        LambdaQueryWrapper<AdminUsers> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AdminUsers::getEmail, email)
               .eq(AdminUsers::getIsDelete, 0);
        return adminUsersMapper.selectOne(wrapper);
    }

    @Override
    public AdminUsers findByPhone(String phone) {
        LambdaQueryWrapper<AdminUsers> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AdminUsers::getPhone, phone)
               .eq(AdminUsers::getIsDelete, 0);
        return adminUsersMapper.selectOne(wrapper);
    }

    @Override
    public List<AdminUsers> findByRole(Integer role) {
        LambdaQueryWrapper<AdminUsers> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AdminUsers::getRole, role)
               .eq(AdminUsers::getIsDelete, 0)
               .orderByDesc(AdminUsers::getCreateTime);
        return adminUsersMapper.selectList(wrapper);
    }

    @Override
    public List<AdminUsers> findByStatus(Integer status) {
        LambdaQueryWrapper<AdminUsers> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AdminUsers::getStatus, status)
               .eq(AdminUsers::getIsDelete, 0)
               .orderByDesc(AdminUsers::getCreateTime);
        return adminUsersMapper.selectList(wrapper);
    }

    @Override
    public PageResult<AdminUsers> findPage(PageRequest pageRequest) {
        LambdaQueryWrapper<AdminUsers> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AdminUsers::getIsDelete, 0)
               .orderByDesc(AdminUsers::getCreateTime);
        return PageUtils.selectPage(adminUsersMapper, pageRequest, wrapper);
    }

    @Override
    public AdminUsers save(AdminUsers adminUser) {
        adminUsersMapper.insert(adminUser);
        return adminUser;
    }

    @Override
    public AdminUsers update(AdminUsers adminUser) {
        adminUsersMapper.updateById(adminUser);
        return adminUser;
    }

    @Override
    public boolean deleteById(Long id) {
        AdminUsers adminUser = new AdminUsers();
        adminUser.setId(id);
        adminUser.setIsDelete(1);
        return adminUsersMapper.updateById(adminUser) > 0;
    }

    @Override
    public boolean updatePassword(Long id, String password) {
        AdminUsers adminUser = new AdminUsers();
        adminUser.setId(id);
        adminUser.setPassword(password);
        return adminUsersMapper.updateById(adminUser) > 0;
    }

    @Override
    public boolean updateStatus(Long id, Integer status) {
        AdminUsers adminUser = new AdminUsers();
        adminUser.setId(id);
        adminUser.setStatus(status);
        return adminUsersMapper.updateById(adminUser) > 0;
    }

    @Override
    public boolean updateLastLogin(Long id, LocalDateTime loginTime, String loginIp) {
        AdminUsers adminUser = new AdminUsers();
        adminUser.setId(id);
        adminUser.setLastLoginAt(loginTime);
        adminUser.setLastLoginIp(loginIp);
        return adminUsersMapper.updateById(adminUser) > 0;
    }

    @Override
    public long count() {
        LambdaQueryWrapper<AdminUsers> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AdminUsers::getIsDelete, 0);
        return adminUsersMapper.selectCount(wrapper);
    }

    @Override
    public long countByRole(Integer role) {
        LambdaQueryWrapper<AdminUsers> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AdminUsers::getRole, role)
               .eq(AdminUsers::getIsDelete, 0);
        return adminUsersMapper.selectCount(wrapper);
    }

    @Override
    public long countByStatus(Integer status) {
        LambdaQueryWrapper<AdminUsers> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AdminUsers::getStatus, status)
               .eq(AdminUsers::getIsDelete, 0);
        return adminUsersMapper.selectCount(wrapper);
    }
}
