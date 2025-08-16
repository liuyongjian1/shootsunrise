package com.shootsunrise.core.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shootsunrise.common.api.request.PageRequest;
import com.shootsunrise.common.api.response.PageResult;
import com.shootsunrise.core.entity.ServicePackages;
import com.shootsunrise.core.enums.ServicePackageCategoryEnum;
import com.shootsunrise.core.mapper.ServicePackagesMapper;
import com.shootsunrise.core.repository.ServicePackagesRepository;
import org.springframework.stereotype.Repository;

import jakarta.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * 服务套餐表 Repository 实现类
 * @author lyj
 * @since 2025-07-27
 */
@Repository
public class ServicePackagesRepositoryImpl implements ServicePackagesRepository {

    @Resource
    private ServicePackagesMapper servicePackagesMapper;

    @Override
    public ServicePackages findById(Long id) {
        return servicePackagesMapper.selectById(id);
    }

    @Override
    public List<ServicePackages> findByPhotographerId(Long photographerId) {
        LambdaQueryWrapper<ServicePackages> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ServicePackages::getPhotographerId, photographerId)
               .eq(ServicePackages::getIsDelete, 0)
               .orderByAsc(ServicePackages::getSortOrder)
               .orderByAsc(ServicePackages::getPrice);
        return servicePackagesMapper.selectList(wrapper);
    }

    @Override
    public List<ServicePackages> findActivePackagesByPhotographerId(Long photographerId) {
        LambdaQueryWrapper<ServicePackages> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ServicePackages::getPhotographerId, photographerId)
               .eq(ServicePackages::getIsActive, true)
               .eq(ServicePackages::getIsDelete, 0)
               .orderByAsc(ServicePackages::getSortOrder)
               .orderByAsc(ServicePackages::getPrice);
        return servicePackagesMapper.selectList(wrapper);
    }

    @Override
    public List<ServicePackages> findByCategory(ServicePackageCategoryEnum category) {
        LambdaQueryWrapper<ServicePackages> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ServicePackages::getCategory, category)
               .eq(ServicePackages::getIsActive, true)
               .eq(ServicePackages::getIsDelete, 0)
               .orderByAsc(ServicePackages::getPrice);
        return servicePackagesMapper.selectList(wrapper);
    }

    @Override
    public List<ServicePackages> findByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        LambdaQueryWrapper<ServicePackages> wrapper = new LambdaQueryWrapper<>();
        wrapper.ge(ServicePackages::getPrice, minPrice)
               .le(ServicePackages::getPrice, maxPrice)
               .eq(ServicePackages::getIsActive, true)
               .eq(ServicePackages::getIsDelete, 0)
               .orderByAsc(ServicePackages::getPrice);
        return servicePackagesMapper.selectList(wrapper);
    }

    @Override
    public List<ServicePackages> findByPackageType(ServicePackageCategoryEnum packageType) {
        LambdaQueryWrapper<ServicePackages> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ServicePackages::getCategory, packageType)
               .eq(ServicePackages::getIsActive, true)
               .eq(ServicePackages::getIsDelete, 0)
               .orderByAsc(ServicePackages::getPrice);
        return servicePackagesMapper.selectList(wrapper);
    }

    @Override
    public PageResult<ServicePackages> findPage(PageRequest pageRequest) {
        LambdaQueryWrapper<ServicePackages> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ServicePackages::getIsDelete, 0)
               .orderByDesc(ServicePackages::getCreateTime);
        return servicePackagesMapper.selectPage(pageRequest, wrapper);
    }

    @Override
    public List<ServicePackages> findActivePackages() {
        LambdaQueryWrapper<ServicePackages> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ServicePackages::getIsActive, true)
               .eq(ServicePackages::getIsDelete, 0)
               .orderByAsc(ServicePackages::getSortOrder)
               .orderByAsc(ServicePackages::getPrice);
        return servicePackagesMapper.selectList(wrapper);
    }

    @Override
    public List<ServicePackages> findPopularPackages(Integer limit) {
        LambdaQueryWrapper<ServicePackages> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ServicePackages::getIsActive, true)
               .eq(ServicePackages::getIsDelete, 0)
               .orderByAsc(ServicePackages::getSortOrder)
               .orderByAsc(ServicePackages::getPrice)
               .last(limit != null ? "LIMIT " + limit : "");
        return servicePackagesMapper.selectList(wrapper);
    }

    @Override
    public ServicePackages save(ServicePackages servicePackage) {
        servicePackagesMapper.insert(servicePackage);
        return servicePackage;
    }

    @Override
    public ServicePackages update(ServicePackages servicePackage) {
        servicePackagesMapper.updateById(servicePackage);
        return servicePackage;
    }

    @Override
    public boolean deleteById(Long id) {
        ServicePackages servicePackage = new ServicePackages();
        servicePackage.setId(id);
        servicePackage.setIsDelete(1);
        return servicePackagesMapper.updateById(servicePackage) > 0;
    }

    @Override
    public boolean setActive(Long id, Boolean isActive) {
        ServicePackages servicePackage = new ServicePackages();
        servicePackage.setId(id);
        servicePackage.setIsActive(isActive);
        return servicePackagesMapper.updateById(servicePackage) > 0;
    }

    @Override
    public boolean updateSortOrder(Long id, Integer sortOrder) {
        ServicePackages servicePackage = new ServicePackages();
        servicePackage.setId(id);
        servicePackage.setSortOrder(sortOrder);
        return servicePackagesMapper.updateById(servicePackage) > 0;
    }

    @Override
    public long count() {
        LambdaQueryWrapper<ServicePackages> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ServicePackages::getIsDelete, 0);
        return servicePackagesMapper.selectCount(wrapper);
    }

    @Override
    public long countByPhotographerId(Long photographerId) {
        LambdaQueryWrapper<ServicePackages> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ServicePackages::getPhotographerId, photographerId)
               .eq(ServicePackages::getIsDelete, 0);
        return servicePackagesMapper.selectCount(wrapper);
    }

    @Override
    public long countByCategory(ServicePackageCategoryEnum category) {
        LambdaQueryWrapper<ServicePackages> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ServicePackages::getCategory, category)
               .eq(ServicePackages::getIsDelete, 0);
        return servicePackagesMapper.selectCount(wrapper);
    }

    @Override
    public boolean enablePackage(Long id) {
        return setActive(id, true);
    }

    @Override
    public boolean disablePackage(Long id) {
        return setActive(id, false);
    }
}
