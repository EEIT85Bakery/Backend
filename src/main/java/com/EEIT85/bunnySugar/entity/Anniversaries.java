package com.EEIT85.bunnySugar.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@DynamicUpdate
@Table(name = "anniversaries")
public class Anniversaries {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "anniversary_name", length = 55)
    private String anniversaryName;
    @Column(name = "anniversary_date")
    private LocalDate anniversaryDate;
    @Column(name = "mail_sent")
    private Boolean mailSent;
    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;
    @Column(name = "update_time", nullable = false)
    private LocalDateTime updateTime;

    @ManyToOne
    //被管理的一方(避免無窮層遞)
    @JsonBackReference("Users_Anniversaries")
    @JoinColumn(name = "users_id", nullable = false)
    private Users users;

    public Anniversaries() {
    }

    public Anniversaries(Long id, String anniversaryName, LocalDate anniversaryDate,
                         Boolean mailSent, LocalDateTime createTime,
                         LocalDateTime updateTime) {
        this.anniversaryName = anniversaryName;
        this.anniversaryDate = anniversaryDate;
        this.mailSent = mailSent;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Long getId() {
        return id;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public String getAnniversaryName() {
        return anniversaryName;
    }

    public void setAnniversaryName(String anniversaryName) {
        this.anniversaryName = anniversaryName;
    }

    public LocalDate getAnniversaryDate() {
        return anniversaryDate;
    }

    public void setAnniversaryDate(LocalDate anniversaryDate) {
        this.anniversaryDate = anniversaryDate;
    }

    public Boolean getMailSent() {
        return mailSent;
    }

    public void setMailSent(Boolean mailSent) {
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

    @Override
    public String toString() {
        return "Anniversaries{" +
                "id=" + id +
                ", anniversaryName='" + anniversaryName + '\'' +
                ", anniversaryDate=" + anniversaryDate +
                ", mailSent=" + mailSent +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", users=" + users +
                '}';
    }

}