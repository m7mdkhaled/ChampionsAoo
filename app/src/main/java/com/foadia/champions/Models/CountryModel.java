package com.foadia.champions.Models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class CountryModel{


	@SerializedName("id")
	private int id;

	@SerializedName("countryName")
	private String countryName;

	@SerializedName("city")
	private List<CityItem> city;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public List<CityItem> getCity() {
		return city;
	}

	public void setCity(List<CityItem> city) {
		this.city = city;
	}
}