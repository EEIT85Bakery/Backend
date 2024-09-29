package com.EEIT85.bunnySugar.dto.users.front;

import java.time.LocalDate;

public class MemberFrontUpdateDto {
    private String name;
    private String gender;
    private String email;
    private String phone;
    private LocalDate birthday;
    private Integer gameTimes;
    private Integer bunnyCoin;

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

    public Integer getGameTimes() {
        return gameTimes;
    }

    public void setGameTimes(Integer gameTimes) {
        this.gameTimes = gameTimes;
    }

    public Integer getBunnyCoin() {
        return bunnyCoin;
    }

    public void setBunnyCoin(Integer bunnyCoin) {
        this.bunnyCoin = bunnyCoin;
    }
}
