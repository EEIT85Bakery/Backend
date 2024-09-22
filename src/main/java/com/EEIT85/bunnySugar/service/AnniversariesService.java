package com.EEIT85.bunnySugar.service;

import com.EEIT85.bunnySugar.dto.anniversaries.AnniversariesInsertDto;
import com.EEIT85.bunnySugar.dto.anniversaries.AnniversariesSelectDto;
import com.EEIT85.bunnySugar.dto.anniversaries.AnniversariesUpdateDto;
import com.EEIT85.bunnySugar.entity.Anniversaries;
import com.EEIT85.bunnySugar.repository.AnniversariesRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AnniversariesService {

    @Autowired
    AnniversariesRepository anniversariesRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;




    public void sendAnniversaryEmail(String email, LocalDate anniversaryDate,
                                     String anniversaryName,
                                     Integer yearDifference) throws MessagingException {


        // 創建 MimeMessage 物件來準備電子郵件
        MimeMessage message = javaMailSender.createMimeMessage();

        // 使用 MimeMessageHelper 來幫助設定郵件的內容
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        // 設定收件人和主題
        helper.setTo(email);
        helper.setSubject("紀念日提醒");

        // 創建 Thymeleaf 上下文並設置變數
        Context context = new Context();

        // 設定紀念日名稱
        context.setVariable("anniversaryName", anniversaryName);
        // 設定格式化的紀念日日期
        context.setVariable("anniversaryDate",
                anniversaryDate.format(DateTimeFormatter.ofPattern("MM-dd")));
        //設定幾週年
        context.setVariable("anniversaryYear", yearDifference);


        // 使用模板引擎生成郵件內容（HTML 格式）
        String body = templateEngine.process("anniversaryEmail", context);

        // 將 HTML 內容設置為郵件正文
        helper.setText(body, true); // true 表示內容是 HTML 格式

        // 發送郵件
        javaMailSender.send(message);
    }

    public void testGetFormattedDate() throws MessagingException {
        // 設定示例的紀念日日期（2024年9月18日）
        LocalDate anniversaryDate = LocalDate.of(1993, 9, 18);
        calculateDateDifference(anniversaryDate);
    }

    public void calculateDateDifference(LocalDate anniversaryDate) throws MessagingException {
        LocalDate nowDate = LocalDate.now();  // 取得今天的日期
        String anniversaryName = "結婚紀念日";
        // 只比較月份和日期，忽略年份
        MonthDay nowMonthDay = MonthDay.from(nowDate);
        MonthDay anniversaryMonthDay = MonthDay.from(anniversaryDate);

        // 計算原始年份的差距
        int yearDifference = nowDate.getYear() - anniversaryDate.getYear();

        // 檢查紀念日是否是今天
        if (nowMonthDay.equals(anniversaryMonthDay)) {
            sendAnniversaryEmail("willy718066@gmail.com", anniversaryDate,
                    anniversaryName, yearDifference);
        }
    }


    @Transactional
    public void insertAnniversaries(AnniversariesInsertDto anniversariesInsertDto,
                                    UUID userId) {
        anniversariesRepository.saveAnniversariesAndUsersId(
                anniversariesInsertDto.getAnniversaryName(),
                anniversariesInsertDto.getAnniversaryDate(),
                false, LocalDateTime.now(), LocalDateTime.now(),
                userId
        );
    }


    public List<AnniversariesSelectDto> getAllById(UUID userId) {
        List<Anniversaries> result = anniversariesRepository.findByUsersId(userId);
        return result.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private AnniversariesSelectDto mapToDto(Anniversaries anniversaries) {
        return new AnniversariesSelectDto(anniversaries.getId(),
                anniversaries.getAnniversaryName(),
                anniversaries.getAnniversaryDate(),
                anniversaries.getUsers().getId());
    }

    public void deleteAnniversaries(UUID userId, Long id) {
         anniversariesRepository.deleteById(id);
    }

    @Transactional
    public void updateAnniversaries(UUID userId, Long id, AnniversariesUpdateDto anniversariesUpdateDto) {
        // 查詢符合條件的紀念日
        Optional<Anniversaries> optionalAnniversary = anniversariesRepository
                .findByIdAndUsersId(id, anniversariesUpdateDto.getUsersId());
        System.out.println(anniversariesRepository
                .findByIdAndUsersId(id, anniversariesUpdateDto.getUsersId()));
        System.out.println(optionalAnniversary);
        if (optionalAnniversary.isPresent()) {
            Anniversaries anniversaries = optionalAnniversary.get();
            // 更新紀念日的名稱和日期
            anniversaries.setAnniversaryName(anniversariesUpdateDto.getAnniversaryName());
            anniversaries.setAnniversaryDate(anniversariesUpdateDto.getAnniversaryDate());
            anniversaries.setUpdateTime(LocalDateTime.now());
            // 保存更新後的紀念日
            anniversariesRepository.save(anniversaries);
        } else {
            throw new EntityNotFoundException("紀念日未找到");
        }
    }
}
