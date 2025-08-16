package com.shootsunrise.core.repository;

import com.shootsunrise.common.api.request.PageRequest;
import com.shootsunrise.common.api.response.PageResult;
import com.shootsunrise.core.entity.Payments;
import com.shootsunrise.core.enums.PaymentStatusEnum;
import com.shootsunrise.core.enums.PaymentTypeEnum;
import com.shootsunrise.core.enums.PaymentMethodEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 支付记录表 Repository 接口
 * @author lyj
 * @since 2025-07-27
 */
public interface PaymentsRepository {

    /**
     * 根据ID查询支付记录
     * @param id 支付ID
     * @return 支付记录信息
     */
    Payments findById(Long id);

    /**
     * 根据支付编号查询
     * @param paymentNo 支付编号
     * @return 支付记录信息
     */
    Payments findByPaymentNo(String paymentNo);

    /**
     * 根据预约ID查询支付记录
     * @param bookingId 预约ID
     * @return 支付记录列表
     */
    List<Payments> findByBookingId(Long bookingId);

    /**
     * 根据用户ID查询支付记录
     * @param userId 用户ID
     * @return 支付记录列表
     */
    List<Payments> findByUserId(Long userId);

    /**
     * 分页查询支付记录列表
     * @param pageRequest 分页请求
     * @return 分页结果
     */
    PageResult<Payments> findPage(PageRequest pageRequest);

    /**
     * 根据状态查询支付记录
     * @param status 支付状态
     * @return 支付记录列表
     */
    List<Payments> findByStatus(PaymentStatusEnum status);

    /**
     * 根据支付类型查询支付记录
     * @param paymentType 支付类型
     * @return 支付记录列表
     */
    List<Payments> findByPaymentType(Integer paymentType);

    /**
     * 根据时间范围查询支付记录
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 支付记录列表
     */
    List<Payments> findByTimeRange(LocalDateTime startTime, LocalDateTime endTime);

    /**
     * 保存支付记录
     * @param payment 支付记录信息
     * @return 保存后的支付记录信息
     */
    Payments save(Payments payment);

    /**
     * 更新支付记录
     * @param payment 支付记录信息
     * @return 更新后的支付记录信息
     */
    Payments update(Payments payment);

    /**
     * 删除支付记录
     * @param id 支付ID
     * @return 是否删除成功
     */
    boolean deleteById(Long id);

    /**
     * 更新支付状态
     * @param id 支付ID
     * @param status 支付状态
     * @return 是否更新成功
     */
    boolean updateStatus(Long id, PaymentStatusEnum status);

    /**
     * 处理退款
     * @param id 支付ID
     * @param refundAmount 退款金额
     * @param refundReason 退款原因
     * @return 是否处理成功
     */
    boolean processRefund(Long id, BigDecimal refundAmount, String refundReason);

    /**
     * 统计支付记录总数
     * @return 支付记录总数
     */
    long count();

    /**
     * 统计指定状态的支付记录数量
     * @param status 支付状态
     * @return 支付记录数量
     */
    long countByStatus(PaymentStatusEnum status);

    /**
     * 计算总收入
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 总收入
     */
    BigDecimal calculateTotalRevenue(LocalDateTime startTime, LocalDateTime endTime);
}
