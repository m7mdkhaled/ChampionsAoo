package com.foadia.champions.Models;

import com.google.gson.annotations.SerializedName;

public class CityItem{


	@SerializedName("id")
	private int id;


	@SerializedName("cityName")
	private String cityName;

	@SerializedName("countryId")
	private int countryId;

	@SerializedName("user")
	private Object user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public Object getUser() {
		return user;
	}

	public void setUser(Object user) {
		this.user = user;
	}
}