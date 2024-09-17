package com.EEIT85.bunnySugar.dto.anniversaries;

import java.time.LocalDate;

public abstract class AnniversariesBaseDto {


    private String anniversaryName;
    private LocalDate anniversaryDate;
    public AnniversariesBaseDto() {
    }

    public AnniversariesBaseDto(LocalDate anniversaryDate, String anniversaryName) {
        this.anniversaryDate = anniversaryDate;
        this.anniversaryName = anniversaryName;
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

}