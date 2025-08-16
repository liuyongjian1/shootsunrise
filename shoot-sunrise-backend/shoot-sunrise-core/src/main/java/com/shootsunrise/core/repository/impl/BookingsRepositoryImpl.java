package com.shootsunrise.core.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shootsunrise.common.api.request.PageRequest;
import com.shootsunrise.common.api.response.PageResult;
import com.shootsunrise.core.entity.Bookings;
import com.shootsunrise.core.enums.BookingStatusEnum;
import com.shootsunrise.core.mapper.BookingsMapper;
import com.shootsunrise.core.repository.BookingsRepository;
import org.springframework.stereotype.Repository;

import jakarta.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 预约表 Repository 实现类
 * @author lyj
 * @since 2025-07-27
 */
@Repository
public class BookingsRepositoryImpl implements BookingsRepository {

    @Resource
    private BookingsMapper bookingsMapper;

    @Override
    public Bookings findById(Long id) {
        return bookingsMapper.selectById(id);
    }

    @Override
    public Bookings findByBookingNo(String bookingNo) {
        LambdaQueryWrapper<Bookings> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Bookings::getBookingNo, bookingNo)
               .eq(Bookings::getIsDelete, 0);
        return bookingsMapper.selectOne(wrapper);
    }

    @Override
    public List<Bookings> findByClientId(Long clientId) {
        LambdaQueryWrapper<Bookings> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Bookings::getClientId, clientId)
               .eq(Bookings::getIsDelete, 0)
               .orderByDesc(Bookings::getCreateTime);
        return bookingsMapper.selectList(wrapper);
    }

    @Override
    public List<Bookings> findByPhotographerId(Long photographerId) {
        LambdaQueryWrapper<Bookings> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Bookings::getPhotographerId, photographerId)
               .eq(Bookings::getIsDelete, 0)
               .orderByDesc(Bookings::getCreateTime);
        return bookingsMapper.selectList(wrapper);
    }

    @Override
    public List<Bookings> findByStatus(BookingStatusEnum status) {
        LambdaQueryWrapper<Bookings> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Bookings::getStatus, status)
               .eq(Bookings::getIsDelete, 0)
               .orderByDesc(Bookings::getCreateTime);
        return bookingsMapper.selectList(wrapper);
    }

    @Override
    public List<Bookings> findByShootDate(LocalDate shootDate) {
        LambdaQueryWrapper<Bookings> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Bookings::getShootDate, shootDate)
               .eq(Bookings::getIsDelete, 0)
               .orderByAsc(Bookings::getShootTime);
        return bookingsMapper.selectList(wrapper);
    }

    @Override
    public List<Bookings> findByPhotographerAndDateRange(Long photographerId, LocalDate startDate, LocalDate endDate) {
        LambdaQueryWrapper<Bookings> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Bookings::getPhotographerId, photographerId)
               .ge(Bookings::getShootDate, startDate)
               .le(Bookings::getShootDate, endDate)
               .eq(Bookings::getIsDelete, 0)
               .orderByAsc(Bookings::getShootDate)
               .orderByAsc(Bookings::getShootTime);
        return bookingsMapper.selectList(wrapper);
    }

    @Override
    public PageResult<Bookings> findPage(PageRequest pageRequest) {
        LambdaQueryWrapper<Bookings> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Bookings::getIsDelete, 0)
               .orderByDesc(Bookings::getCreateTime);
        return bookingsMapper.selectPage(pageRequest, wrapper);
    }

    @Override
    public Bookings save(Bookings booking) {
        bookingsMapper.insert(booking);
        return booking;
    }

    @Override
    public Bookings update(Bookings booking) {
        bookingsMapper.updateById(booking);
        return booking;
    }

    @Override
    public boolean deleteById(Long id) {
        Bookings booking = new Bookings();
        booking.setId(id);
        booking.setIsDelete(1);
        return bookingsMapper.updateById(booking) > 0;
    }

    @Override
    public boolean updateStatus(Long id, BookingStatusEnum status) {
        Bookings booking = new Bookings();
        booking.setId(id);
        booking.setStatus(status);
        return bookingsMapper.updateById(booking) > 0;
    }

    @Override
    public boolean confirmBooking(Long id) {
        Bookings booking = new Bookings();
        booking.setId(id);
        booking.setStatus(BookingStatusEnum.CONFIRMED);
        return bookingsMapper.updateById(booking) > 0;
    }

    @Override
    public boolean cancelBooking(Long id, String reason) {
        Bookings booking = new Bookings();
        booking.setId(id);
        booking.setStatus(BookingStatusEnum.CANCELLED);
        booking.setCancelReason(reason);
        return bookingsMapper.updateById(booking) > 0;
    }

    @Override
    public long count() {
        LambdaQueryWrapper<Bookings> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Bookings::getIsDelete, 0);
        return bookingsMapper.selectCount(wrapper);
    }

    @Override
    public long countByStatus(BookingStatusEnum status) {
        LambdaQueryWrapper<Bookings> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Bookings::getStatus, status)
               .eq(Bookings::getIsDelete, 0);
        return bookingsMapper.selectCount(wrapper);
    }

    @Override
    public long countByPhotographerId(Long photographerId) {
        LambdaQueryWrapper<Bookings> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Bookings::getPhotographerId, photographerId)
               .eq(Bookings::getIsDelete, 0);
        return bookingsMapper.selectCount(wrapper);
    }

    @Override
    public List<Bookings> findByTimeRange(LocalDateTime startTime, LocalDateTime endTime) {
        LambdaQueryWrapper<Bookings> wrapper = new LambdaQueryWrapper<>();
        wrapper.ge(Bookings::getCreateTime, startTime)
               .le(Bookings::getCreateTime, endTime)
               .eq(Bookings::getIsDelete, 0)
               .orderByDesc(Bookings::getCreateTime);
        return bookingsMapper.selectList(wrapper);
    }

    @Override
    public List<Bookings> findByPhotographerAndTimeRange(Long photographerId, LocalDateTime startTime, LocalDateTime endTime) {
        LambdaQueryWrapper<Bookings> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Bookings::getPhotographerId, photographerId)
               .ge(Bookings::getCreateTime, startTime)
               .le(Bookings::getCreateTime, endTime)
               .eq(Bookings::getIsDelete, 0)
               .orderByDesc(Bookings::getCreateTime);
        return bookingsMapper.selectList(wrapper);
    }

    @Override
    public boolean completeBooking(Long id) {
        Bookings booking = new Bookings();
        booking.setId(id);
        booking.setStatus(BookingStatusEnum.COMPLETED);
        return bookingsMapper.updateById(booking) > 0;
    }
}
