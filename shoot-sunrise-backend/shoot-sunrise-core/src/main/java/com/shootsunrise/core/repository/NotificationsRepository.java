package com.shootsunrise.core.repository;

import com.shootsunrise.common.api.request.PageRequest;
import com.shootsunrise.common.api.response.PageResult;
import com.shootsunrise.core.entity.Notifications;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 系统通知表 Repository 接口
 * @author lyj
 * @since 2025-07-27
 */
public interface NotificationsRepository {

    /**
     * 根据ID查询通知
     * @param id 通知ID
     * @return 通知信息
     */
    Notifications findById(Long id);

    /**
     * 根据用户ID查询通知列表
     * @param userId 用户ID
     * @return 通知列表
     */
    List<Notifications> findByUserId(Long userId);

    /**
     * 根据用户ID查询未读通知列表
     * @param userId 用户ID
     * @return 未读通知列表
     */
    List<Notifications> findUnreadByUserId(Long userId);

    /**
     * 根据通知类型查询通知列表
     * @param type 通知类型
     * @return 通知列表
     */
    List<Notifications> findByType(String type);

    /**
     * 根据优先级查询通知列表
     * @param priority 优先级
     * @return 通知列表
     */
    List<Notifications> findByPriority(String priority);

    /**
     * 查询未过期的通知列表
     * @param userId 用户ID
     * @return 未过期通知列表
     */
    List<Notifications> findValidNotifications(Long userId);

    /**
     * 分页查询通知列表
     * @param pageRequest 分页请求
     * @return 分页结果
     */
    PageResult<Notifications> findPage(PageRequest pageRequest);

    /**
     * 保存通知
     * @param notification 通知信息
     * @return 保存后的通知信息
     */
    Notifications save(Notifications notification);

    /**
     * 更新通知
     * @param notification 通知信息
     * @return 更新后的通知信息
     */
    Notifications update(Notifications notification);

    /**
     * 删除通知
     * @param id 通知ID
     * @return 是否删除成功
     */
    boolean deleteById(Long id);

    /**
     * 标记通知为已读
     * @param id 通知ID
     * @return 是否标记成功
     */
    boolean markAsRead(Long id);

    /**
     * 批量标记通知为已读
     * @param userId 用户ID
     * @param notificationIds 通知ID列表
     * @return 标记成功的数量
     */
    int batchMarkAsRead(Long userId, List<Long> notificationIds);

    /**
     * 标记用户所有通知为已读
     * @param userId 用户ID
     * @return 标记成功的数量
     */
    int markAllAsRead(Long userId);

    /**
     * 删除过期通知
     * @param expiredTime 过期时间
     * @return 删除的数量
     */
    int deleteExpiredNotifications(LocalDateTime expiredTime);

    /**
     * 统计通知总数
     * @return 通知总数
     */
    long count();

    /**
     * 统计用户的未读通知数量
     * @param userId 用户ID
     * @return 未读通知数量
     */
    long countUnreadByUserId(Long userId);

    /**
     * 统计指定类型的通知数量
     * @param type 通知类型
     * @return 通知数量
     */
    long countByType(String type);
}
