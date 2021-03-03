package com.foadia.champions.Models;

public class LoginModel {

    public String mobile;
    public String password;
    public String token ;
    public int id;

    public LoginModel(String userName, String password) {
        this.mobile = userName;
        this.password = password;
    }

    public String getUserName() {
        return mobile;
    }

    public void setUserName(String userName) {
        this.mobile = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
