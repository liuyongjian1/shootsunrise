package com.shootsunrise.core.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shootsunrise.common.api.request.PageRequest;
import com.shootsunrise.common.api.response.PageResult;
import com.shootsunrise.core.entity.Users;
import com.shootsunrise.core.mapper.UsersMapper;
import com.shootsunrise.core.repository.UsersRepository;
import com.shootsunrise.core.common.mybatis.core.util.PageUtils;
import org.springframework.stereotype.Repository;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * 用户基础信息表 Repository 实现类
 * @author lyj
 * @since 2025-07-27
 */
@Repository
public class UsersRepositoryImpl implements UsersRepository {

    @Resource
    private UsersMapper usersMapper;

    @Override
    public Users findById(Long id) {
        return usersMapper.selectById(id);
    }

    @Override
    public Users findByOpenid(String openid) {
        LambdaQueryWrapper<Users> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Users::getOpenid, openid)
               .eq(Users::getIsDelete, 0);
        return usersMapper.selectOne(wrapper);
    }

    @Override
    public Users findByPhone(String phone) {
        LambdaQueryWrapper<Users> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Users::getPhone, phone)
               .eq(Users::getIsDelete, 0);
        return usersMapper.selectOne(wrapper);
    }

    @Override
    public PageResult<Users> findPage(PageRequest pageRequest) {
        LambdaQueryWrapper<Users> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Users::getIsDelete, 0)
               .orderByDesc(Users::getCreateTime);
        return PageUtils.selectPage(usersMapper, pageRequest, wrapper);
    }

    @Override
    public List<Users> findByStatus(Integer status) {
        LambdaQueryWrapper<Users> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Users::getStatus, status)
               .eq(Users::getIsDelete, 0)
               .orderByDesc(Users::getCreateTime);
        return usersMapper.selectList(wrapper);
    }

    @Override
    public Users save(Users user) {
        usersMapper.insert(user);
        return user;
    }

    @Override
    public Users update(Users user) {
        usersMapper.updateById(user);
        return user;
    }

    @Override
    public boolean deleteById(Long id) {
        Users user = new Users();
        user.setId(id);
        user.setIsDelete(1);
        return usersMapper.updateById(user) > 0;
    }

    @Override
    public long count() {
        LambdaQueryWrapper<Users> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Users::getIsDelete, 0);
        return usersMapper.selectCount(wrapper);
    }

    @Override
    public long countByStatus(Integer status) {
        LambdaQueryWrapper<Users> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Users::getStatus, status)
               .eq(Users::getIsDelete, 0);
        return usersMapper.selectCount(wrapper);
    }
}
