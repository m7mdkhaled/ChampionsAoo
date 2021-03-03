package com.foadia.champions.Models;

import com.google.gson.annotations.SerializedName;

public class Reward{

	@SerializedName("rewardNameEn")
	private String rewardNameEn;

	@SerializedName("year")
	private int year;

	@SerializedName("rewardNameAr")
	private String rewardNameAr;

	@SerializedName("userId")
	private int userId;

	public String getRewardNameEn(){
		return rewardNameEn;
	}

	public int getYear(){
		return year;
	}

	public String getRewardNameAr(){
		return rewardNameAr;
	}

	public int getUserId(){
		return userId;
	}
}