package com.shootsunrise.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.shootsunrise.core.common.mybatis.core.dataobject.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 文件上传记录表
 * @author lyj
 * @since 2025-07-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("file_uploads")
public class FileUploads extends BaseEntity {

    /**
     * 文件ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 上传用户ID - 关联users.id
     */
    private Long userId;

    /**
     * 原始文件名
     */
    private String originalName;

    /**
     * 存储文件名
     */
    private String fileName;

    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 文件URL
     */
    private String fileUrl;

    /**
     * 文件大小（字节）
     */
    private Long fileSize;

    /**
     * 文件类型：IMAGE-图片，VIDEO-视频，DOCUMENT-文档，OTHER-其他
     */
    private String fileType;

    /**
     * MIME类型
     */
    private String mimeType;

    /**
     * 文件扩展名
     */
    private String fileExtension;

    /**
     * 存储类型：LOCAL-本地存储，OSS-对象存储，CDN-CDN存储
     */
    private String storageType;

    /**
     * 关联类型：AVATAR-头像，PORTFOLIO-作品，CERTIFICATE-证书，OTHER-其他
     */
    private String relationType;

    /**
     * 关联ID
     */
    private Long relationId;

    /**
     * 文件状态：UPLOADING-上传中，SUCCESS-上传成功，FAILED-上传失败
     */
    private String status;

    /**
     * MD5值
     */
    private String md5Hash;
}
