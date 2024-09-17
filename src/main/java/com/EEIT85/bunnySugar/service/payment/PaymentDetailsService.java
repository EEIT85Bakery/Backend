package com.EEIT85.bunnySugar.service.payment;

import com.EEIT85.bunnySugar.entity.PaymentDetails;
import com.EEIT85.bunnySugar.repository.PaymentDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentDetailsService {

    @Autowired
    PaymentDetailsRepository paymentDetailsRepository;

    public List<String> getMerchantNoByUserId(Long userId) {

        return paymentDetailsRepository.findMerchantNoByUserId(userId);
    }

    public void checkPaymentStatus(String merchantNo) {
        System.out.println("Input merchantNo: " + merchantNo); // 除錯用

        Optional<PaymentDetails> paymentDetails =
                paymentDetailsRepository.findPaymentStatusByMerchantNo(merchantNo);

        if (paymentDetails.isPresent()) {
            PaymentDetails details = paymentDetails.get();
            // 更新狀態
            details.setPaymentStatus("已付款");
            paymentDetailsRepository.save(details);
        } else {
            System.out.println("No payment details found for merchantNo: " + merchantNo); // 除錯用
            throw new RuntimeException("找不到對應的付款資訊");
        }
    }
}
