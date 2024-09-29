package com.EEIT85.bunnySugar.dto.users.admin;

import java.time.LocalDate;

public class MemberAdminUpdateDto {
    private String userVip;

    public MemberAdminUpdateDto() {
    }

    public String getUserVip() {
        return userVip;
    }

    public void setUserVip(String userVip) {
        this.userVip = userVip;
    }
}
