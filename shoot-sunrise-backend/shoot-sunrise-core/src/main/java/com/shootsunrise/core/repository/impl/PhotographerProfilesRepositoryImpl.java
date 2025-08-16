package com.shootsunrise.core.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shootsunrise.common.api.request.PageRequest;
import com.shootsunrise.common.api.response.PageResult;
import com.shootsunrise.core.entity.PhotographerProfiles;
import com.shootsunrise.core.enums.CertificationStatusEnum;
import com.shootsunrise.core.mapper.PhotographerProfilesMapper;
import com.shootsunrise.core.repository.PhotographerProfilesRepository;
import org.springframework.stereotype.Repository;

import jakarta.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * 摄影师认证信息表 Repository 实现类
 * @author lyj
 * @since 2025-07-27
 */
@Repository
public class PhotographerProfilesRepositoryImpl implements PhotographerProfilesRepository {

    @Resource
    private PhotographerProfilesMapper photographerProfilesMapper;

    @Override
    public PhotographerProfiles findById(Long id) {
        return photographerProfilesMapper.selectById(id);
    }

    @Override
    public PhotographerProfiles findByUserId(Long userId) {
        LambdaQueryWrapper<PhotographerProfiles> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PhotographerProfiles::getUserId, userId)
               .eq(PhotographerProfiles::getIsDelete, 0);
        return photographerProfilesMapper.selectOne(wrapper);
    }

    @Override
    public PageResult<PhotographerProfiles> findPage(PageRequest pageRequest) {
        LambdaQueryWrapper<PhotographerProfiles> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PhotographerProfiles::getIsDelete, 0)
               .orderByDesc(PhotographerProfiles::getCreateTime);
        return photographerProfilesMapper.selectPage(pageRequest, wrapper);
    }

    @Override
    public List<PhotographerProfiles> findByCertificationStatus(CertificationStatusEnum status) {
        LambdaQueryWrapper<PhotographerProfiles> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PhotographerProfiles::getCertificationStatus, status)
               .eq(PhotographerProfiles::getIsDelete, 0)
               .orderByDesc(PhotographerProfiles::getCreateTime);
        return photographerProfilesMapper.selectList(wrapper);
    }

    @Override
    public List<PhotographerProfiles> findFeaturedPhotographers() {
        LambdaQueryWrapper<PhotographerProfiles> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PhotographerProfiles::getIsFeatured, true)
               .eq(PhotographerProfiles::getCertificationStatus, CertificationStatusEnum.APPROVED)
               .eq(PhotographerProfiles::getIsDelete, 0)
               .orderByAsc(PhotographerProfiles::getSortOrder)
               .orderByDesc(PhotographerProfiles::getRating);
        return photographerProfilesMapper.selectList(wrapper);
    }

    @Override
    public List<PhotographerProfiles> findByRatingRange(BigDecimal minRating, BigDecimal maxRating) {
        LambdaQueryWrapper<PhotographerProfiles> wrapper = new LambdaQueryWrapper<>();
        wrapper.ge(PhotographerProfiles::getRating, minRating)
               .le(PhotographerProfiles::getRating, maxRating)
               .eq(PhotographerProfiles::getCertificationStatus, CertificationStatusEnum.APPROVED)
               .eq(PhotographerProfiles::getIsDelete, 0)
               .orderByDesc(PhotographerProfiles::getRating);
        return photographerProfilesMapper.selectList(wrapper);
    }

    @Override
    public PhotographerProfiles save(PhotographerProfiles profile) {
        photographerProfilesMapper.insert(profile);
        return profile;
    }

    @Override
    public PhotographerProfiles update(PhotographerProfiles profile) {
        photographerProfilesMapper.updateById(profile);
        return profile;
    }

    @Override
    public boolean deleteById(Long id) {
        PhotographerProfiles profile = new PhotographerProfiles();
        profile.setId(id);
        profile.setIsDelete(1);
        return photographerProfilesMapper.updateById(profile) > 0;
    }

    @Override
    public boolean updateCertificationStatus(Long id, CertificationStatusEnum status, String reason) {
        PhotographerProfiles profile = new PhotographerProfiles();
        profile.setId(id);
        profile.setCertificationStatus(status);
        if (CertificationStatusEnum.REJECTED.equals(status)) {
            profile.setRejectionReason(reason);
        }
        return photographerProfilesMapper.updateById(profile) > 0;
    }

    @Override
    public boolean updateRating(Long id, BigDecimal rating) {
        PhotographerProfiles profile = new PhotographerProfiles();
        profile.setId(id);
        profile.setRating(rating);
        return photographerProfilesMapper.updateById(profile) > 0;
    }

    @Override
    public long count() {
        LambdaQueryWrapper<PhotographerProfiles> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PhotographerProfiles::getIsDelete, 0);
        return photographerProfilesMapper.selectCount(wrapper);
    }

    @Override
    public long countByCertificationStatus(CertificationStatusEnum status) {
        LambdaQueryWrapper<PhotographerProfiles> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PhotographerProfiles::getCertificationStatus, status)
               .eq(PhotographerProfiles::getIsDelete, 0);
        return photographerProfilesMapper.selectCount(wrapper);
    }
}
