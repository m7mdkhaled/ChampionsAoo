package com.foadia.champions.Models;

import com.google.gson.annotations.SerializedName;

public class Courses {

	@SerializedName("courseNameAr")
	private String courseNameAr;

	@SerializedName("courseNameEn")
	private String courseNameEn;

	@SerializedName("id")
	private int id;

	@SerializedName("userId")
	private int userId;

	public String getCourseNameAr() {
		return courseNameAr;
	}

	public void setCourseNameAr(String courseNameAr) {
		this.courseNameAr = courseNameAr;
	}

	public String getCourseNameEn() {
		return courseNameEn;
	}

	public void setCourseNameEn(String courseNameEn) {
		this.courseNameEn = courseNameEn;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}