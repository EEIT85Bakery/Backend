package com.EEIT85.bunnySugar.dto.users.admin;

import java.time.LocalDate;

public class MemberAdminUpdateDto {
    private Long id;
    private String userVip;

    public MemberAdminUpdateDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserVip() {
        return userVip;
    }

    public void setUserVip(String userVip) {
        this.userVip = userVip;
    }
}
