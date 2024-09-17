package com.EEIT85.bunnySugar.service.user;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class VerificationEmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    public void sendVerificationEmail(String email, String verifyingToken) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(email);
        helper.setSubject("驗證信件");

        // 創建 Thymeleaf 上下文並設置變數
        Context context = new Context();
        context.setVariable("verifyingToken", verifyingToken);
        context.setVariable("expirationTime", LocalDateTime.now().plusMinutes(10).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));

        // 使用模板引擎生成郵件內容（HTML 格式）
        String body = templateEngine.process("verificationEmail", context);
        helper.setText(body, true);

        // 發送郵件
        javaMailSender.send(message);
    }
}
