package com.shootsunrise.core.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shootsunrise.common.api.request.PageRequest;
import com.shootsunrise.common.api.response.PageResult;
import com.shootsunrise.core.entity.Payments;
import com.shootsunrise.core.enums.PaymentStatusEnum;
import com.shootsunrise.core.mapper.PaymentsMapper;
import com.shootsunrise.core.repository.PaymentsRepository;
import org.springframework.stereotype.Repository;

import jakarta.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 支付记录表 Repository 实现类
 * @author lyj
 * @since 2025-07-27
 */
@Repository
public class PaymentsRepositoryImpl implements PaymentsRepository {

    @Resource
    private PaymentsMapper paymentsMapper;

    @Override
    public Payments findById(Long id) {
        return paymentsMapper.selectById(id);
    }

    @Override
    public Payments findByPaymentNo(String paymentNo) {
        LambdaQueryWrapper<Payments> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Payments::getPaymentNo, paymentNo)
               .eq(Payments::getIsDelete, 0);
        return paymentsMapper.selectOne(wrapper);
    }

    @Override
    public List<Payments> findByBookingId(Long bookingId) {
        LambdaQueryWrapper<Payments> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Payments::getBookingId, bookingId)
               .eq(Payments::getIsDelete, 0)
               .orderByDesc(Payments::getCreateTime);
        return paymentsMapper.selectList(wrapper);
    }

    @Override
    public List<Payments> findByUserId(Long userId) {
        LambdaQueryWrapper<Payments> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Payments::getUserId, userId)
               .eq(Payments::getIsDelete, 0)
               .orderByDesc(Payments::getCreateTime);
        return paymentsMapper.selectList(wrapper);
    }

    @Override
    public PageResult<Payments> findPage(PageRequest pageRequest) {
        LambdaQueryWrapper<Payments> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Payments::getIsDelete, 0)
               .orderByDesc(Payments::getCreateTime);
        return paymentsMapper.selectPage(pageRequest, wrapper);
    }

    @Override
    public List<Payments> findByStatus(PaymentStatusEnum status) {
        LambdaQueryWrapper<Payments> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Payments::getStatus, status)
               .eq(Payments::getIsDelete, 0)
               .orderByDesc(Payments::getCreateTime);
        return paymentsMapper.selectList(wrapper);
    }

    @Override
    public List<Payments> findByPaymentType(Integer paymentType) {
        LambdaQueryWrapper<Payments> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Payments::getPaymentType, paymentType)
               .eq(Payments::getIsDelete, 0)
               .orderByDesc(Payments::getCreateTime);
        return paymentsMapper.selectList(wrapper);
    }

    @Override
    public List<Payments> findByTimeRange(LocalDateTime startTime, LocalDateTime endTime) {
        LambdaQueryWrapper<Payments> wrapper = new LambdaQueryWrapper<>();
        wrapper.ge(Payments::getCreateTime, startTime)
               .le(Payments::getCreateTime, endTime)
               .eq(Payments::getIsDelete, 0)
               .orderByDesc(Payments::getCreateTime);
        return paymentsMapper.selectList(wrapper);
    }

    @Override
    public Payments save(Payments payment) {
        paymentsMapper.insert(payment);
        return payment;
    }

    @Override
    public Payments update(Payments payment) {
        paymentsMapper.updateById(payment);
        return payment;
    }

    @Override
    public boolean deleteById(Long id) {
        Payments payment = new Payments();
        payment.setId(id);
        payment.setIsDelete(1);
        return paymentsMapper.updateById(payment) > 0;
    }

    @Override
    public boolean updateStatus(Long id, PaymentStatusEnum status) {
        Payments payment = new Payments();
        payment.setId(id);
        payment.setStatus(status);
        if (PaymentStatusEnum.SUCCESS ==status) { // SUCCESS
            payment.setPaidAt(LocalDateTime.now());
        }
        return paymentsMapper.updateById(payment) > 0;
    }

    @Override
    public boolean processRefund(Long id, BigDecimal refundAmount, String refundReason) {
        Payments payment = new Payments();
        payment.setId(id);
        payment.setStatus(PaymentStatusEnum.REFUNDED);
        payment.setRefundAmount(refundAmount);
        payment.setRefundReason(refundReason);
        payment.setRefundedAt(LocalDateTime.now());
        return paymentsMapper.updateById(payment) > 0;
    }

    @Override
    public long count() {
        LambdaQueryWrapper<Payments> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Payments::getIsDelete, 0);
        return paymentsMapper.selectCount(wrapper);
    }

    @Override
    public long countByStatus(PaymentStatusEnum status) {
        LambdaQueryWrapper<Payments> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Payments::getStatus, status)
               .eq(Payments::getIsDelete, 0);
        return paymentsMapper.selectCount(wrapper);
    }

    @Override
    public BigDecimal calculateTotalRevenue(LocalDateTime startTime, LocalDateTime endTime) {
        // 1. 查询成功支付的记录
        LambdaQueryWrapper<Payments> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Payments::getStatus, 1) // SUCCESS
               .ge(Payments::getPaidAt, startTime)
               .le(Payments::getPaidAt, endTime)
               .eq(Payments::getIsDelete, 0);
        
        List<Payments> payments = paymentsMapper.selectList(wrapper);
        
        // 2. 计算总收入
        return payments.stream()
                .map(Payments::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
