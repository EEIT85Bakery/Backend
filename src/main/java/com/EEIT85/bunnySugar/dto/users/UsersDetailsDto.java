package com.EEIT85.bunnySugar.dto.users;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UsersDetailsDto {

    private String email;
    private String account; // 帳號，可為空
    private String password; // 密碼，可為空
    private String name; // 姓名，可為空
    private String gender; // 性別，可為空
    private LocalDate birthday; // 生日，可為空
    private Integer bunnyCoin; // 小兔幣(紅利金)，可為空
    private LocalDateTime createTime; // 創建時間
    private LocalDateTime updateTime; // 更新時間
    private Integer gameTimes; // 遊戲次數，可為空

    // 無參數建構子
    public UsersDetailsDto() {}

    // 帶參數的建構子
    public UsersDetailsDto(String email, String account, String password, String name, String gender, LocalDate birthday,
                          Integer bunnyCoin, LocalDateTime createTime, LocalDateTime updateTime, Integer gameTimes) {
        this.email = email;
        this.account = account;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.bunnyCoin = bunnyCoin;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.gameTimes = gameTimes;
    }

    // Getter 和 Setter 方法
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Integer getBunnyCoin() {
        return bunnyCoin;
    }

    public void setBunnyCoin(Integer bunnyCoin) {
        this.bunnyCoin = bunnyCoin;
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

    public Integer getGameTimes() {
        return gameTimes;
    }

    public void setGameTimes(Integer gameTimes) {
        this.gameTimes = gameTimes;
    }
}

