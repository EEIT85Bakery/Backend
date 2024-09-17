package com.EEIT85.bunnySugar.repository;

import com.EEIT85.bunnySugar.entity.PaymentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentDetailsRepository extends JpaRepository<PaymentDetails, Long> {
    PaymentDetails findByOrdersId(Long orderId);

    @Modifying
    @Query("UPDATE PaymentDetails pd SET pd.paymentStatus = :paymentStatus WHERE pd.orders.id = :orderId")
    int updatePaymentStatus(@Param("orderId") Long orderId, @Param("paymentStatus") String paymentStatus);


//    @Query("SELECT pd.merchantNo FROM PaymentDetails pd " +
//            "JOIN pd.orders o " +
//            "JOIN o.user u " +
//            "WHERE u.id = :userId AND pd.paymentStatus = '未付款'")
//    List<String> findMerchantNoByUserId(@Param("userId") Long userId);

    @Query(value = "SELECT pd.merchant_no " +
            "FROM payment_details pd " +
            "INNER JOIN Orders o ON pd.orders_id = o.id " +
            "INNER JOIN Users u ON o.users_id = u.id " +
            "WHERE u.id = :userId " +
            "AND pd.payment_status = '未付款' " +
            "AND DATE(pd.create_time) >= CURDATE() AND payment_method = '信用卡付款'",
            nativeQuery = true)
    List<String> findMerchantNoByUserId(@Param("userId") Long userId);

    @Query("SELECT pd FROM PaymentDetails pd WHERE pd.merchantNo = :merchantNo")
    Optional<PaymentDetails> findPaymentStatusByMerchantNo(@Param("merchantNo") String merchantNo);
}
