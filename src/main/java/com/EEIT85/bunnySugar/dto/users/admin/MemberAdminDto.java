package com.EEIT85.bunnySugar.dto.users.admin;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MemberAdminDto {
    private Long id;
    private String name;
    private String gender;
    private String email;
    private String phone;
    private LocalDate birthday;
    private String userVip;

    public MemberAdminDto() {
    }

    public MemberAdminDto(Long id, String name, String gender, String email, String phone, LocalDate birthday, String userVip) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.birthday = birthday;
        this.userVip = userVip;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getUserVip() {
        return userVip;
    }

    public void setUserVip(String userVip) {
        this.userVip = userVip;
    }
}
