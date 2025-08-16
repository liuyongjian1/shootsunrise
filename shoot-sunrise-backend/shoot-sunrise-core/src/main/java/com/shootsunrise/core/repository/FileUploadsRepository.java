package com.shootsunrise.core.repository;

import com.shootsunrise.common.api.request.PageRequest;
import com.shootsunrise.common.api.response.PageResult;
import com.shootsunrise.core.entity.FileUploads;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 文件上传记录表 Repository 接口
 * @author lyj
 * @since 2025-07-27
 */
public interface FileUploadsRepository {

    /**
     * 根据ID查询文件上传记录
     * @param id 文件ID
     * @return 文件上传记录信息
     */
    FileUploads findById(Long id);

    /**
     * 根据文件名查询文件上传记录
     * @param fileName 文件名
     * @return 文件上传记录信息
     */
    FileUploads findByFileName(String fileName);

    /**
     * 根据MD5值查询文件上传记录
     * @param md5Hash MD5值
     * @return 文件上传记录信息
     */
    FileUploads findByMd5Hash(String md5Hash);

    /**
     * 根据用户ID查询文件上传记录列表
     * @param userId 用户ID
     * @return 文件上传记录列表
     */
    List<FileUploads> findByUserId(Long userId);

    /**
     * 根据文件类型查询文件上传记录列表
     * @param fileType 文件类型
     * @return 文件上传记录列表
     */
    List<FileUploads> findByFileType(String fileType);

    /**
     * 根据存储类型查询文件上传记录列表
     * @param storageType 存储类型
     * @return 文件上传记录列表
     */
    List<FileUploads> findByStorageType(String storageType);

    /**
     * 根据关联类型和关联ID查询文件上传记录列表
     * @param relationType 关联类型
     * @param relationId 关联ID
     * @return 文件上传记录列表
     */
    List<FileUploads> findByRelation(String relationType, Long relationId);

    /**
     * 根据状态查询文件上传记录列表
     * @param status 状态
     * @return 文件上传记录列表
     */
    List<FileUploads> findByStatus(String status);

    /**
     * 根据时间范围查询文件上传记录列表
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 文件上传记录列表
     */
    List<FileUploads> findByTimeRange(LocalDateTime startTime, LocalDateTime endTime);

    /**
     * 查询上传失败的文件记录列表
     * @return 上传失败的文件记录列表
     */
    List<FileUploads> findFailedUploads();

    /**
     * 查询孤儿文件（没有关联的文件）
     * @return 孤儿文件列表
     */
    List<FileUploads> findOrphanFiles();

    /**
     * 分页查询文件上传记录列表
     * @param pageRequest 分页请求
     * @return 分页结果
     */
    PageResult<FileUploads> findPage(PageRequest pageRequest);

    /**
     * 保存文件上传记录
     * @param fileUpload 文件上传记录信息
     * @return 保存后的文件上传记录信息
     */
    FileUploads save(FileUploads fileUpload);

    /**
     * 更新文件上传记录
     * @param fileUpload 文件上传记录信息
     * @return 更新后的文件上传记录信息
     */
    FileUploads update(FileUploads fileUpload);

    /**
     * 删除文件上传记录
     * @param id 文件ID
     * @return 是否删除成功
     */
    boolean deleteById(Long id);

    /**
     * 更新文件状态
     * @param id 文件ID
     * @param status 状态
     * @return 是否更新成功
     */
    boolean updateStatus(Long id, String status);

    /**
     * 更新文件关联
     * @param id 文件ID
     * @param relationType 关联类型
     * @param relationId 关联ID
     * @return 是否更新成功
     */
    boolean updateRelation(Long id, String relationType, Long relationId);

    /**
     * 批量删除过期文件记录
     * @param expiredTime 过期时间
     * @return 删除的数量
     */
    int deleteExpiredFiles(LocalDateTime expiredTime);

    /**
     * 统计文件上传记录总数
     * @return 文件上传记录总数
     */
    long count();

    /**
     * 统计用户的文件数量
     * @param userId 用户ID
     * @return 文件数量
     */
    long countByUserId(Long userId);

    /**
     * 统计指定类型的文件数量
     * @param fileType 文件类型
     * @return 文件数量
     */
    long countByFileType(String fileType);

    /**
     * 统计指定状态的文件数量
     * @param status 状态
     * @return 文件数量
     */
    long countByStatus(String status);

    /**
     * 计算总文件大小
     * @return 总文件大小（字节）
     */
    Long calculateTotalFileSize();

    /**
     * 计算用户的文件总大小
     * @param userId 用户ID
     * @return 用户文件总大小（字节）
     */
    Long calculateUserFileSize(Long userId);
}
