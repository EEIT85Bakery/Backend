package com.EEIT85.bunnySugar.dto.anniversaries;

import java.time.LocalDate;
import java.util.UUID;

public abstract class AnniversariesBaseDto {


    private String anniversaryName;
    private LocalDate anniversaryDate;
    private UUID usersId;

    public AnniversariesBaseDto() {
    }

    public AnniversariesBaseDto(String anniversaryName, LocalDate anniversaryDate, UUID usersId) {
        this.anniversaryName = anniversaryName;
        this.anniversaryDate = anniversaryDate;
        this.usersId = usersId;
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

    public UUID getUsersId() {
        return usersId;
    }

    public void setUsersId(UUID usersId) {
        this.usersId = usersId;
    }
}