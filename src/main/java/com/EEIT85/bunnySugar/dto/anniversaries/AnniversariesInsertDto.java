package com.EEIT85.bunnySugar.dto.anniversaries;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class AnniversariesInsertDto extends AnniversariesBaseDto{
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Boolean mailSent;

    public AnniversariesInsertDto(String anniversaryName, LocalDate anniversaryDate, UUID usersId, Boolean mailSent, LocalDateTime updateTime, LocalDateTime createTime) {
        super(anniversaryName, anniversaryDate, usersId);
        this.mailSent = mailSent;
        this.updateTime = updateTime;
        this.createTime = createTime;
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
