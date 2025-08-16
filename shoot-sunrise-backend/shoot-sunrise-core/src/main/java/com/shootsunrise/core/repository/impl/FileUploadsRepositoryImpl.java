package com.shootsunrise.core.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shootsunrise.common.api.request.PageRequest;
import com.shootsunrise.common.api.response.PageResult;
import com.shootsunrise.core.entity.FileUploads;
import com.shootsunrise.core.mapper.FileUploadsMapper;
import com.shootsunrise.core.repository.FileUploadsRepository;
import org.springframework.stereotype.Repository;

import jakarta.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 文件上传记录表 Repository 实现类
 * @author lyj
 * @since 2025-07-27
 */
@Repository
public class FileUploadsRepositoryImpl implements FileUploadsRepository {

    @Resource
    private FileUploadsMapper fileUploadsMapper;

    @Override
    public FileUploads findById(Long id) {
        return fileUploadsMapper.selectById(id);
    }

    @Override
    public FileUploads findByFileName(String fileName) {
        LambdaQueryWrapper<FileUploads> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FileUploads::getFileName, fileName)
               .eq(FileUploads::getIsDelete, 0);
        return fileUploadsMapper.selectOne(wrapper);
    }

    @Override
    public FileUploads findByMd5Hash(String md5Hash) {
        LambdaQueryWrapper<FileUploads> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FileUploads::getMd5Hash, md5Hash)
               .eq(FileUploads::getIsDelete, 0);
        return fileUploadsMapper.selectOne(wrapper);
    }

    @Override
    public List<FileUploads> findByUserId(Long userId) {
        LambdaQueryWrapper<FileUploads> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FileUploads::getUserId, userId)
               .eq(FileUploads::getIsDelete, 0)
               .orderByDesc(FileUploads::getCreateTime);
        return fileUploadsMapper.selectList(wrapper);
    }

    @Override
    public List<FileUploads> findByFileType(String fileType) {
        LambdaQueryWrapper<FileUploads> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FileUploads::getFileType, fileType)
               .eq(FileUploads::getIsDelete, 0)
               .orderByDesc(FileUploads::getCreateTime);
        return fileUploadsMapper.selectList(wrapper);
    }

    @Override
    public List<FileUploads> findByStorageType(String storageType) {
        LambdaQueryWrapper<FileUploads> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FileUploads::getStorageType, storageType)
               .eq(FileUploads::getIsDelete, 0)
               .orderByDesc(FileUploads::getCreateTime);
        return fileUploadsMapper.selectList(wrapper);
    }

    @Override
    public List<FileUploads> findByRelation(String relationType, Long relationId) {
        LambdaQueryWrapper<FileUploads> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FileUploads::getRelationType, relationType)
               .eq(FileUploads::getRelationId, relationId)
               .eq(FileUploads::getIsDelete, 0)
               .orderByDesc(FileUploads::getCreateTime);
        return fileUploadsMapper.selectList(wrapper);
    }

    @Override
    public List<FileUploads> findByStatus(String status) {
        LambdaQueryWrapper<FileUploads> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FileUploads::getStatus, status)
               .eq(FileUploads::getIsDelete, 0)
               .orderByDesc(FileUploads::getCreateTime);
        return fileUploadsMapper.selectList(wrapper);
    }

    @Override
    public List<FileUploads> findByTimeRange(LocalDateTime startTime, LocalDateTime endTime) {
        LambdaQueryWrapper<FileUploads> wrapper = new LambdaQueryWrapper<>();
        wrapper.ge(FileUploads::getCreateTime, startTime)
               .le(FileUploads::getCreateTime, endTime)
               .eq(FileUploads::getIsDelete, 0)
               .orderByDesc(FileUploads::getCreateTime);
        return fileUploadsMapper.selectList(wrapper);
    }

    @Override
    public List<FileUploads> findFailedUploads() {
        LambdaQueryWrapper<FileUploads> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FileUploads::getStatus, "FAILED")
               .eq(FileUploads::getIsDelete, 0)
               .orderByDesc(FileUploads::getCreateTime);
        return fileUploadsMapper.selectList(wrapper);
    }

    @Override
    public List<FileUploads> findOrphanFiles() {
        LambdaQueryWrapper<FileUploads> wrapper = new LambdaQueryWrapper<>();
        wrapper.isNull(FileUploads::getRelationId)
               .eq(FileUploads::getStatus, "SUCCESS")
               .eq(FileUploads::getIsDelete, 0)
               .orderByDesc(FileUploads::getCreateTime);
        return fileUploadsMapper.selectList(wrapper);
    }

    @Override
    public PageResult<FileUploads> findPage(PageRequest pageRequest) {
        LambdaQueryWrapper<FileUploads> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FileUploads::getIsDelete, 0)
               .orderByDesc(FileUploads::getCreateTime);
        return fileUploadsMapper.selectPage(pageRequest, wrapper);
    }

    @Override
    public FileUploads save(FileUploads fileUpload) {
        fileUploadsMapper.insert(fileUpload);
        return fileUpload;
    }

    @Override
    public FileUploads update(FileUploads fileUpload) {
        fileUploadsMapper.updateById(fileUpload);
        return fileUpload;
    }

    @Override
    public boolean deleteById(Long id) {
        FileUploads fileUpload = new FileUploads();
        fileUpload.setId(id);
        fileUpload.setIsDelete(1);
        return fileUploadsMapper.updateById(fileUpload) > 0;
    }

    @Override
    public boolean updateStatus(Long id, String status) {
        FileUploads fileUpload = new FileUploads();
        fileUpload.setId(id);
        fileUpload.setStatus(status);
        return fileUploadsMapper.updateById(fileUpload) > 0;
    }

    @Override
    public boolean updateRelation(Long id, String relationType, Long relationId) {
        FileUploads fileUpload = new FileUploads();
        fileUpload.setId(id);
        fileUpload.setRelationType(relationType);
        fileUpload.setRelationId(relationId);
        return fileUploadsMapper.updateById(fileUpload) > 0;
    }

    @Override
    public int deleteExpiredFiles(LocalDateTime expiredTime) {
        LambdaQueryWrapper<FileUploads> wrapper = new LambdaQueryWrapper<>();
        wrapper.lt(FileUploads::getCreateTime, expiredTime)
               .eq(FileUploads::getIsDelete, 0);
        
        List<FileUploads> expiredFiles = fileUploadsMapper.selectList(wrapper);
        int count = 0;
        for (FileUploads file : expiredFiles) {
            if (deleteById(file.getId())) {
                count++;
            }
        }
        return count;
    }

    @Override
    public long count() {
        LambdaQueryWrapper<FileUploads> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FileUploads::getIsDelete, 0);
        return fileUploadsMapper.selectCount(wrapper);
    }

    @Override
    public long countByUserId(Long userId) {
        LambdaQueryWrapper<FileUploads> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FileUploads::getUserId, userId)
               .eq(FileUploads::getIsDelete, 0);
        return fileUploadsMapper.selectCount(wrapper);
    }

    @Override
    public long countByFileType(String fileType) {
        LambdaQueryWrapper<FileUploads> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FileUploads::getFileType, fileType)
               .eq(FileUploads::getIsDelete, 0);
        return fileUploadsMapper.selectCount(wrapper);
    }

    @Override
    public long countByStatus(String status) {
        LambdaQueryWrapper<FileUploads> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FileUploads::getStatus, status)
               .eq(FileUploads::getIsDelete, 0);
        return fileUploadsMapper.selectCount(wrapper);
    }

    @Override
    public Long calculateTotalFileSize() {
        LambdaQueryWrapper<FileUploads> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FileUploads::getStatus, "SUCCESS")
               .eq(FileUploads::getIsDelete, 0);
        
        List<FileUploads> files = fileUploadsMapper.selectList(wrapper);
        return files.stream()
                .mapToLong(FileUploads::getFileSize)
                .sum();
    }

    @Override
    public Long calculateUserFileSize(Long userId) {
        LambdaQueryWrapper<FileUploads> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FileUploads::getUserId, userId)
               .eq(FileUploads::getStatus, "SUCCESS")
               .eq(FileUploads::getIsDelete, 0);
        
        List<FileUploads> files = fileUploadsMapper.selectList(wrapper);
        return files.stream()
                .mapToLong(FileUploads::getFileSize)
                .sum();
    }
}
