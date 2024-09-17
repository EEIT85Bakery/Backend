package com.EEIT85.bunnySugar.dto.users;

public class UsersVerifyDto {
    private String email;
    private String verifyingToken;

    public UsersVerifyDto() {
    }

    public UsersVerifyDto(String email, String verifyingToken) {
        this.email = email;
        this.verifyingToken = verifyingToken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVerifyingToken() {
        return verifyingToken;
    }

    public void setVerifyingToken(String verifyingToken) {
        this.verifyingToken = verifyingToken;
    }
}
