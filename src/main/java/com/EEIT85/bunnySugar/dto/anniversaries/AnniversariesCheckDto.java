package com.EEIT85.bunnySugar.dto.anniversaries;

import java.time.LocalDate;

public class AnniversariesCheckDto extends AnniversariesBaseDto{
    private String userEmail;
    private Long id;

    public AnniversariesCheckDto() {
    }

    public AnniversariesCheckDto(LocalDate anniversaryDate, String anniversaryName, String userEmail, Long id) {
        super(anniversaryDate, anniversaryName);
        this.userEmail = userEmail;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
