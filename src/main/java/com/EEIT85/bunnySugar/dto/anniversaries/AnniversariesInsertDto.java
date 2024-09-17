package com.EEIT85.bunnySugar.dto.anniversaries;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AnniversariesInsertDto extends AnniversariesBaseDto{
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Boolean mailSent;

    public AnniversariesInsertDto(LocalDate anniversaryDate, String anniversaryName, LocalDateTime createTime, LocalDateTime updateTime, Boolean mailSent) {
        super(anniversaryDate, anniversaryName);
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.mailSent = mailSent;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getMailSent() {
        return mailSent;
    }

    public void setMailSent(Boolean mailSent) {
        this.mailSent = mailSent;
    }
}
