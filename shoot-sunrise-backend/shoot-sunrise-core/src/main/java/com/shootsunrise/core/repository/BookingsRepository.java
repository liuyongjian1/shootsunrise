package com.shootsunrise.core.repository;

import com.shootsunrise.common.api.request.PageRequest;
import com.shootsunrise.common.api.response.PageResult;
import com.shootsunrise.core.entity.Bookings;
import com.shootsunrise.core.enums.BookingStatusEnum;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 预约表 Repository 接口
 * @author lyj
 * @since 2025-07-27
 */
public interface BookingsRepository {

    /**
     * 根据ID查询预约
     * @param id 预约ID
     * @return 预约信息
     */
    Bookings findById(Long id);

    /**
     * 根据预约编号查询预约
     * @param bookingNo 预约编号
     * @return 预约信息
     */
    Bookings findByBookingNo(String bookingNo);

    /**
     * 根据客户ID查询预约列表
     * @param clientId 客户ID
     * @return 预约列表
     */
    List<Bookings> findByClientId(Long clientId);

    /**
     * 根据摄影师ID查询预约列表
     * @param photographerId 摄影师ID
     * @return 预约列表
     */
    List<Bookings> findByPhotographerId(Long photographerId);

    /**
     * 分页查询预约列表
     * @param pageRequest 分页请求
     * @return 分页结果
     */
    PageResult<Bookings> findPage(PageRequest pageRequest);

    /**
     * 根据状态查询预约列表
     * @param status 预约状态
     * @return 预约列表
     */
    List<Bookings> findByStatus(BookingStatusEnum status);

    /**
     * 根据拍摄日期查询预约列表
     * @param shootDate 拍摄日期
     * @return 预约列表
     */
    List<Bookings> findByShootDate(java.time.LocalDate shootDate);

    /**
     * 根据摄影师和日期范围查询预约列表
     * @param photographerId 摄影师ID
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 预约列表
     */
    List<Bookings> findByPhotographerAndDateRange(Long photographerId, java.time.LocalDate startDate, java.time.LocalDate endDate);

    /**
     * 根据时间范围查询预约
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 预约列表
     */
    List<Bookings> findByTimeRange(LocalDateTime startTime, LocalDateTime endTime);

    /**
     * 查询摄影师在指定时间的预约
     * @param photographerId 摄影师ID
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 预约列表
     */
    List<Bookings> findByPhotographerAndTimeRange(Long photographerId, LocalDateTime startTime, LocalDateTime endTime);

    /**
     * 保存预约
     * @param booking 预约信息
     * @return 保存后的预约信息
     */
    Bookings save(Bookings booking);

    /**
     * 更新预约
     * @param booking 预约信息
     * @return 更新后的预约信息
     */
    Bookings update(Bookings booking);

    /**
     * 删除预约
     * @param id 预约ID
     * @return 是否删除成功
     */
    boolean deleteById(Long id);

    /**
     * 更新预约状态
     * @param id 预约ID
     * @param status 预约状态
     * @return 是否更新成功
     */
    boolean updateStatus(Long id, BookingStatusEnum status);

    /**
     * 确认预约
     * @param id 预约ID
     * @return 是否确认成功
     */
    boolean confirmBooking(Long id);

    /**
     * 取消预约
     * @param id 预约ID
     * @param cancelReason 取消原因
     * @return 是否取消成功
     */
    boolean cancelBooking(Long id, String cancelReason);

    /**
     * 完成预约
     * @param id 预约ID
     * @return 是否完成成功
     */
    boolean completeBooking(Long id);

    /**
     * 统计预约总数
     * @return 预约总数
     */
    long count();

    /**
     * 统计指定状态的预约数量
     * @param status 预约状态
     * @return 预约数量
     */
    long countByStatus(BookingStatusEnum status);

    /**
     * 统计指定摄影师的预约数量
     * @param photographerId 摄影师ID
     * @return 预约数量
     */
    long countByPhotographerId(Long photographerId);
}
