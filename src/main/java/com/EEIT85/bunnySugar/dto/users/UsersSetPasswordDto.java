package com.EEIT85.bunnySugar.dto.users;

public class UsersSetPasswordDto {
    private String password;
    private String email;

    public UsersSetPasswordDto() {
    }

    public UsersSetPasswordDto(String password, String email) {
        this.password = password;
        this.email = email;
    }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
