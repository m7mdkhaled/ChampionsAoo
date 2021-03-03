package com.foadia.champions.Models;

import com.google.gson.annotations.SerializedName;

public class Comments{

	@SerializedName("id")
	private int id;

	@SerializedName("commentContent")
	private String commentContent;

	@SerializedName("userId")
	private int userId;

	@SerializedName("starRate")
	private int starRate;

	public int getId(){
		return id;
	}

	public String getCommentContent(){
		return commentContent;
	}

	public int getUserId(){
		return userId;
	}

	public int getStarRate(){
		return starRate;
	}
}