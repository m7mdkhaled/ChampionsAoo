package com.foadia.champions.Models;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserModel{

	@SerializedName("userTypeId")
	@Expose
	private int userTypeId;
	@Expose
	@SerializedName("Courses")
	private List<Courses> courses;

	@SerializedName("comments")
	private List<Comments> comments;

	@SerializedName("userstatus")
	private String userstatus;

	@SerializedName("mobile")
	private String mobile;

	@SerializedName("cityId")
	private int cityId;

	@SerializedName("userName")
	private String userName;

	@SerializedName("gender")
	private String gender;

	@SerializedName("age")
	private String age;

	@SerializedName("countryId")
	private int countryId;

	@SerializedName("countryName")
	private int countryName;

	@SerializedName("confirmPayment")
	private int confirmPayment;

	@SerializedName("photoUrl")
	private String photoUrl;

	@SerializedName("hobbies")
	private List<Hobby> hobbies;

	@SerializedName("id")
	private int id;

	@SerializedName("galleries")
	private List<Gallery> galleries;

	@SerializedName("email")
	private String email;

	@SerializedName("rewards")
	private List<Reward> rewards;

	@SerializedName("userTalents")
	private List<UserTalents> userTalents;

	public int getUserTypeId() {
		return userTypeId;
	}

	public void setUserTypeId(int userTypeId) {
		this.userTypeId = userTypeId;
	}

	public List<Courses> getCourses() {
		return courses;
	}

	public void setCourses(List<Courses> courses) {
		this.courses = courses;
	}

	public List<Comments> getComments() {
		return comments;
	}

	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}

	public String getUserstatus() {
		return userstatus;
	}

	public void setUserstatus(String userstatus) {
		this.userstatus = userstatus;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public int getCountryName() {
		return countryName;
	}

	public void setCountryName(int countryName) {
		this.countryName = countryName;
	}

	public int getConfirmPayment() {
		return confirmPayment;
	}

	public void setConfirmPayment(int confirmPayment) {
		this.confirmPayment = confirmPayment;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public List<Hobby> getHobbies() {
		return hobbies;
	}

	public void setHobbies(List<Hobby> hobbies) {
		this.hobbies = hobbies;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Gallery> getGalleries() {
		return galleries;
	}

	public void setGalleries(List<Gallery> galleries) {
		this.galleries = galleries;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Reward> getRewards() {
		return rewards;
	}

	public void setRewards(List<Reward> rewards) {
		this.rewards = rewards;
	}

	public List<UserTalents> getUserTalents() {
		return userTalents;
	}

	public void setUserTalents(List<UserTalents> userTalents) {
		this.userTalents = userTalents;
	}
}