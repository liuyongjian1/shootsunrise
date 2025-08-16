package com.shootsunrise.core.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shootsunrise.common.api.request.PageRequest;
import com.shootsunrise.common.api.response.PageResult;
import com.shootsunrise.core.entity.Notifications;
import com.shootsunrise.core.mapper.NotificationsMapper;
import com.shootsunrise.core.repository.NotificationsRepository;
import org.springframework.stereotype.Repository;

import jakarta.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 系统通知表 Repository 实现类
 * @author lyj
 * @since 2025-07-27
 */
@Repository
public class NotificationsRepositoryImpl implements NotificationsRepository {

    @Resource
    private NotificationsMapper notificationsMapper;

    @Override
    public Notifications findById(Long id) {
        return notificationsMapper.selectById(id);
    }

    @Override
    public List<Notifications> findByUserId(Long userId) {
        LambdaQueryWrapper<Notifications> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Notifications::getUserId, userId)
               .eq(Notifications::getIsDelete, 0)
               .orderByDesc(Notifications::getCreateTime);
        return notificationsMapper.selectList(wrapper);
    }

    @Override
    public List<Notifications> findUnreadByUserId(Long userId) {
        LambdaQueryWrapper<Notifications> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Notifications::getUserId, userId)
               .eq(Notifications::getIsRead, false)
               .eq(Notifications::getIsDelete, 0)
               .orderByDesc(Notifications::getCreateTime);
        return notificationsMapper.selectList(wrapper);
    }

    @Override
    public List<Notifications> findByType(String type) {
        LambdaQueryWrapper<Notifications> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Notifications::getType, type)
               .eq(Notifications::getIsDelete, 0)
               .orderByDesc(Notifications::getCreateTime);
        return notificationsMapper.selectList(wrapper);
    }

    @Override
    public List<Notifications> findByPriority(String priority) {
        LambdaQueryWrapper<Notifications> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Notifications::getPriority, priority)
               .eq(Notifications::getIsDelete, 0)
               .orderByDesc(Notifications::getCreateTime);
        return notificationsMapper.selectList(wrapper);
    }

    @Override
    public List<Notifications> findValidNotifications(Long userId) {
        LambdaQueryWrapper<Notifications> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Notifications::getUserId, userId)
               .and(w -> w.isNull(Notifications::getExpiredAt)
                         .or()
                         .gt(Notifications::getExpiredAt, LocalDateTime.now()))
               .eq(Notifications::getIsDelete, 0)
               .orderByDesc(Notifications::getCreateTime);
        return notificationsMapper.selectList(wrapper);
    }

    @Override
    public PageResult<Notifications> findPage(PageRequest pageRequest) {
        LambdaQueryWrapper<Notifications> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Notifications::getIsDelete, 0)
               .orderByDesc(Notifications::getCreateTime);
        return notificationsMapper.selectPage(pageRequest, wrapper);
    }

    @Override
    public Notifications save(Notifications notification) {
        notificationsMapper.insert(notification);
        return notification;
    }

    @Override
    public Notifications update(Notifications notification) {
        notificationsMapper.updateById(notification);
        return notification;
    }

    @Override
    public boolean deleteById(Long id) {
        Notifications notification = new Notifications();
        notification.setId(id);
        notification.setIsDelete(1);
        return notificationsMapper.updateById(notification) > 0;
    }

    @Override
    public boolean markAsRead(Long id) {
        Notifications notification = new Notifications();
        notification.setId(id);
        notification.setIsRead(true);
        notification.setReadAt(LocalDateTime.now());
        return notificationsMapper.updateById(notification) > 0;
    }

    @Override
    public int batchMarkAsRead(Long userId, List<Long> notificationIds) {
        if (notificationIds == null || notificationIds.isEmpty()) {
            return 0;
        }
        
        int count = 0;
        for (Long id : notificationIds) {
            // 验证通知属于该用户
            Notifications notification = findById(id);
            if (notification != null && notification.getUserId().equals(userId)) {
                if (markAsRead(id)) {
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    public int markAllAsRead(Long userId) {
        List<Notifications> unreadNotifications = findUnreadByUserId(userId);
        int count = 0;
        for (Notifications notification : unreadNotifications) {
            if (markAsRead(notification.getId())) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int deleteExpiredNotifications(LocalDateTime expiredTime) {
        LambdaQueryWrapper<Notifications> wrapper = new LambdaQueryWrapper<>();
        wrapper.lt(Notifications::getExpiredAt, expiredTime)
               .eq(Notifications::getIsDelete, 0);
        
        List<Notifications> expiredNotifications = notificationsMapper.selectList(wrapper);
        int count = 0;
        for (Notifications notification : expiredNotifications) {
            if (deleteById(notification.getId())) {
                count++;
            }
        }
        return count;
    }

    @Override
    public long count() {
        LambdaQueryWrapper<Notifications> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Notifications::getIsDelete, 0);
        return notificationsMapper.selectCount(wrapper);
    }

    @Override
    public long countUnreadByUserId(Long userId) {
        LambdaQueryWrapper<Notifications> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Notifications::getUserId, userId)
               .eq(Notifications::getIsRead, false)
               .eq(Notifications::getIsDelete, 0);
        return notificationsMapper.selectCount(wrapper);
    }

    @Override
    public long countByType(String type) {
        LambdaQueryWrapper<Notifications> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Notifications::getType, type)
               .eq(Notifications::getIsDelete, 0);
        return notificationsMapper.selectCount(wrapper);
    }
}
