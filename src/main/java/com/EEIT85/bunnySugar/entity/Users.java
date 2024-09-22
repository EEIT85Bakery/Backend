package com.EEIT85.bunnySugar.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@DynamicUpdate
@Entity
@Table(name = "users")
public class Users {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(length = 255, nullable = false, name = "email")  // 信箱
    private String email;

    @Column(length = 20, nullable = false, name = "phone")  // 手機
    private String phone;

    @Column(length = 255, nullable = false, name = "verifying_token")  // 驗證碼
    private String verifyingToken;

    @Column(length = 55, nullable = true, name = "account")  // 帳號，可為空
    private String account;

    @Column(length = 255, nullable = true, name = "password")  // 密碼，可為空
    private String password;

    @Column(length = 55, nullable = true, name = "username")  // 姓名，可為空
    private String name;

    @Column(length = 10, nullable = true, name = "gender")  // 性別，可為空
    private String gender;

    @Column(name = "birthday", nullable = true)  // 生日，可為空
    private LocalDate birthday;

    @Column(name = "bunny_coin", nullable = true)  // 小兔幣(紅利金)，可為空
    private Integer bunnyCoin;

    @Column(nullable = false, name = "active")  // 是否驗證
    private Integer active;

    @Column(length = 255, nullable = true, name = "forget_token")  // 忘記密碼token，可為空
    private String forgetToken;

    @Column(length = 20, nullable = true, name = "login_method")  // 登入方式，可為空
    private String loginMethod;

    @Column(length = 255, nullable = true, name = "google_token")  // Google token，可為空
    private String googleToken;

    @Column(length = 255, nullable = true, name = "facebook_token")  // Facebook token，可為空
    private String facebookToken;

    @Column(nullable = false, name = "create_time")  // 創建時間
    private LocalDateTime createTime;

    @Column(nullable = false, name = "update_time")  // 更新時間
    private LocalDateTime updateTime;

    @Column(name = "last_login_time", nullable = true)  // 最後登入時間，可為空
    private LocalDateTime lastLoginTime;

    @Column(name = "game_times", nullable = true)  // 遊戲次數，可為空
    private Integer gameTimes;

    @Column(name = "token_expiration_time", nullable = true)  // token 到期時間，可為空
    private LocalDateTime tokenExpirationTime;

    @Column(nullable = true, name = "Details_completed")
    private Integer detailsCompleted;

    @JsonManagedReference
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Anniversaries> anniversaries;

    // OneToOne關聯Cart
    @OneToOne(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
    private Cart cart;

    // OneToOne關聯WishList
    @OneToOne(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private WishList wishList;

    // Constructors, Getters, Setters...

    public Users() {
        this.id = UUID.randomUUID(); // 在構造函數中自動生成 UUID
    }

    // 所有參數的構造函數
    public Users(String email, String phone, String verifyingToken, String account, String password, String name,
                 String gender, LocalDate birthday, Integer bunnyCoin, Integer active,
                 String forgetToken, String loginMethod, String googleToken, String facebookToken,
                 LocalDateTime createTime, LocalDateTime updateTime, LocalDateTime lastLoginTime,
                 Integer gameTimes, LocalDateTime tokenExpirationTime, Integer detailsCompleted, List<Anniversaries> anniversaries,
                 Cart cart, WishList wishList) {
        this.id = UUID.randomUUID(); // 在構造函數中自動生成 UUID
        this.email = email;
        this.phone = phone;
        this.verifyingToken = verifyingToken;
        this.account = account;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.bunnyCoin = bunnyCoin;
        this.active = active;
        this.forgetToken = forgetToken;
        this.loginMethod = loginMethod;
        this.googleToken = googleToken;
        this.facebookToken = facebookToken;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.lastLoginTime = lastLoginTime;
        this.gameTimes = gameTimes;
        this.tokenExpirationTime = tokenExpirationTime;
        this.detailsCompleted = detailsCompleted;
        this.anniversaries = anniversaries;
        this.cart = cart;
        this.wishList = wishList;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public String getVerifyingToken() {
        return verifyingToken;
    }

    public void setVerifyingToken(String verifyingToken) {
        this.verifyingToken = verifyingToken;
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

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public String getForgetToken() {
        return forgetToken;
    }

    public void setForgetToken(String forgetToken) {
        this.forgetToken = forgetToken;
    }

    public String getLoginMethod() {
        return loginMethod;
    }

    public void setLoginMethod(String loginMethod) {
        this.loginMethod = loginMethod;
    }

    public String getGoogleToken() {
        return googleToken;
    }

    public void setGoogleToken(String googleToken) {
        this.googleToken = googleToken;
    }

    public String getFacebookToken() {
        return facebookToken;
    }

    public void setFacebookToken(String facebookToken) {
        this.facebookToken = facebookToken;
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

    public LocalDateTime getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(LocalDateTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getGameTimes() {
        return gameTimes;
    }

    public void setGameTimes(Integer gameTimes) {
        this.gameTimes = gameTimes;
    }

    public LocalDateTime getTokenExpirationTime() {
        return tokenExpirationTime;
    }

    public void setTokenExpirationTime(LocalDateTime tokenExpirationTime) {
        this.tokenExpirationTime = tokenExpirationTime;
    }

    public Integer getDetailsCompleted() {
        return detailsCompleted != null ? detailsCompleted : 0;
    }

    public void setDetailsCompleted(Integer detailsCompleted) {
        this.detailsCompleted = detailsCompleted;
    }

    public List<Anniversaries> getAnniversaries() {
        return anniversaries;
    }

    public void setAnniversaries(List<Anniversaries> anniversaries) {
        this.anniversaries = anniversaries;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public WishList getWishList() {
        return wishList;
    }

    public void setWishList(WishList wishList) {
        this.wishList = wishList;
    }
}
