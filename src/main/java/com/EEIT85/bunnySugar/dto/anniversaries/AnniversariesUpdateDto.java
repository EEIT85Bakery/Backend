package com.EEIT85.bunnySugar.dto.anniversaries;

import java.time.LocalDate;

public class AnniversariesUpdateDto{
    private Boolean mailSent;

    public AnniversariesUpdateDto() {
    }

    public AnniversariesUpdateDto(LocalDate anniversaryDate, String anniversaryName, Boolean mailSent, Long id) {
        this.mailSent = mailSent;
    }

    public Boolean getMailSent() {
        return mailSent;
    }

    public void setMailSent(Boolean mailSent) {
        this.mailSent = mailSent;
    }

}
