package com.EEIT85.bunnySugar.service.anniversaries;

import com.EEIT85.bunnySugar.dto.anniversaries.AnniversariesCheckDto;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import java.util.stream.Collectors;

@Service
public class AnniversariesService {

    @Autowired
    AnniversariesRepository anniversariesRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;




    public String sendAnniversaryEmail(Long id, String email,
                                       LocalDate anniversaryDate,
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

        AnniversariesUpdateDto anniversariesUpdateDto = new AnniversariesUpdateDto();
        anniversariesUpdateDto.setMailSent(true);
        updateAnniversaries(id, anniversariesUpdateDto);

        return "信件已寄出";
    }


    //傳日期 名稱 信箱
//    public void testGetFormattedDate() throws MessagingException {
//        // 設定示例的紀念日日期（2024年9月18日）
//        calculateDateDifference(anniversaryDate);
//    }

    public String calculateDateDifference(AnniversariesCheckDto anniversariesCheckDto) throws MessagingException {
        System.out.println(anniversariesCheckDto);
        LocalDate anniversaryDate = anniversariesCheckDto.getAnniversaryDate();
        LocalDate nowDate = LocalDate.now();  // 取得今天的日期
        String anniversaryName = anniversariesCheckDto.getAnniversaryName();
        String email = anniversariesCheckDto.getUserEmail();
        Long id = anniversariesCheckDto.getId();
        // 只比較月份和日期，忽略年份
        MonthDay nowMonthDay = MonthDay.from(nowDate);
        MonthDay anniversaryMonthDay = MonthDay.from(anniversaryDate);

        // 計算原始年份的差距
        int yearDifference = nowDate.getYear() - anniversaryDate.getYear();

        // 檢查紀念日是否是今天
        if (nowMonthDay.equals(anniversaryMonthDay)) {
            return sendAnniversaryEmail(id, email, anniversaryDate,
                    anniversaryName, yearDifference);
        }else {
            return "今天不是紀念日";
        }

    }


    @Transactional
    public void insertAnniversaries(AnniversariesInsertDto anniversariesInsertDto,
                                    Long userId) {
        anniversariesRepository.saveAnniversariesAndUsersId(
                anniversariesInsertDto.getAnniversaryName(),
                anniversariesInsertDto.getAnniversaryDate(),
                false, LocalDateTime.now(), LocalDateTime.now(),
                userId
        );
    }


    public List<AnniversariesSelectDto> getAllById(Long userId) {
        List<Anniversaries> result = anniversariesRepository.findByUsersId(userId);
        return result.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private AnniversariesSelectDto mapToDto(Anniversaries anniversaries) {
        return new AnniversariesSelectDto(
                anniversaries.getAnniversaryDate(),
                anniversaries.getAnniversaryName(),anniversaries.getId(),
                anniversaries.getMailSent());
        }

    public void deleteAnniversaries(Long id) {
         anniversariesRepository.deleteById(id);
    }

    @Transactional
    public void updateAnniversaries(Long id, AnniversariesUpdateDto anniversariesUpdateDto) {
        // 查詢符合條件的紀念日
        Optional<Anniversaries> optionalAnniversary = anniversariesRepository
                .findById(id);
        System.out.println(123);
        System.out.println(optionalAnniversary);
        if (optionalAnniversary.isPresent()) {
            Anniversaries anniversaries = optionalAnniversary.get();
            anniversaries.setMailSent(anniversariesUpdateDto.getMailSent());
            anniversariesRepository.save(anniversaries);
        } else {
            throw new EntityNotFoundException("紀念日未找到");
        }
    }

    public Page<Anniversaries> getAnniversariesPaginated(Long userId, Pageable pageable) {
        return anniversariesRepository.findByUsersId(userId, pageable);
    }
}
