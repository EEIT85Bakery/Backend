package com.EEIT85.bunnySugar.controller;

import com.EEIT85.bunnySugar.dto.PostMerchantDto;
import com.EEIT85.bunnySugar.dto.QueryOrderDTO;
import com.EEIT85.bunnySugar.service.ECPAYService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequestMapping("/api")
@RestController
public class ECPAYController {

    @Autowired
    ECPAYService ecpayService;

    @PostMapping("/ecpayCheckout")
    public String ecpayCheckout(
                                @RequestBody PostMerchantDto postMerchantDto) throws IOException {
        String aioCheckOutALLForm = ecpayService.ecpayCheckout(postMerchantDto);
        System.out.println(2);
        return aioCheckOutALLForm;
    }

    @PostMapping("/queryOrder")
    public String queryOrder(@RequestBody QueryOrderDTO queryOrderDTO) throws IOException {
        return ecpayService.queryOrder(queryOrderDTO);
    }
}
