package com.example.developerdiary.login.dto;

public class LoginResponse {

    public UsersData usersData;

    public String access_token;

    public String refresh_token;

    public UsersData getUsersData() {
        return usersData;
    }

    public void setUser(UsersData user) {
        this.usersData = user;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }
}
