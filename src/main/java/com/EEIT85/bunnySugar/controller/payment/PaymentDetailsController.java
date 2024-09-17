package com.EEIT85.bunnySugar.controller.payment;

import com.EEIT85.bunnySugar.dto.payment.CheckPaymentStatusDto;
import com.EEIT85.bunnySugar.service.payment.PaymentDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pay")
public class PaymentDetailsController {

    @Autowired
    PaymentDetailsService paymentDetailsService;

    @GetMapping("/getMerchantNos")
    public ResponseEntity<List<String>> getUserMerchantNoByUserId(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        List<String> paymentSelectDtoList =
                paymentDetailsService.getMerchantNoByUserId(userId);
        return ResponseEntity.ok(paymentSelectDtoList);
    }

    @PutMapping("/checkPaymentStatus")
    public ResponseEntity<String> checkPaymentStatus(@RequestBody CheckPaymentStatusDto checkPaymentStatusDto) {
        paymentDetailsService.checkPaymentStatus(checkPaymentStatusDto.getMerchantNo());
        return ResponseEntity.ok("確認已付款");
    }
}
