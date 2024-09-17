package com.EEIT85.bunnySugar.dto.anniversaries;

import java.time.LocalDate;

public class AnniversariesSelectDto extends AnniversariesBaseDto{
    private final Long id;
    private Boolean mailSent;

    public AnniversariesSelectDto(LocalDate anniversaryDate, String anniversaryName, Long id) {
        super(anniversaryDate, anniversaryName);
        this.id = id;
    }

    public AnniversariesSelectDto(LocalDate anniversaryDate, String anniversaryName, Long id, Boolean mailSent) {
        super(anniversaryDate, anniversaryName);
        this.id = id;
        this.mailSent = mailSent;
    }

    public Long getId() {
        return id;
    }

    public Boolean getMailSent() {
        return mailSent;
    }

    public void setMailSent(Boolean mailSent) {
        this.mailSent = mailSent;
    }
}
