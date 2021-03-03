package com.foadia.champions.Models;

public class RegisterModel {

    public int userTypeId;
    public String userName;
    public String password;
    public String mobile;
    public String email;
    public String birthDate;
    public String type;
    public String userstatus;
    public String photoUrl;
    public String countryId;
    public String cityId;
    public String confirmPayment;

    public RegisterModel(int userTypeId, String userName, String password, String mobile, String email, String birthDate, String type, String userstatus, String photoUrl, String countryId, String cityId, String confirmPayment) {
        this.userTypeId = userTypeId;
        this.userName = userName;
        this.password = password;
        this.mobile = mobile;
        this.email = email;
        this.birthDate = birthDate;
        this.type = type;
        this.userstatus = userstatus;
        this.photoUrl = photoUrl;
        this.countryId = countryId;
        this.cityId = cityId;
        this.confirmPayment = confirmPayment;
    }

    public int getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(int userTypeId) {
        this.userTypeId = userTypeId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserstatus() {
        return userstatus;
    }

    public void setUserstatus(String userstatus) {
        this.userstatus = userstatus;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getConfirmPayment() {
        return confirmPayment;
    }

    public void setConfirmPayment(String confirmPayment) {
        this.confirmPayment = confirmPayment;
    }
}
