package com.foadia.champions.Models;

import com.google.gson.annotations.SerializedName;

public class Gallery{

	@SerializedName("filePath")
	private String filePath;

	@SerializedName("id")
	private int id;

	@SerializedName("userId")
	private int userId;

	@SerializedName("fileType")
	private String fileType;

	public String getFilePath(){
		return filePath;
	}

	public int getId(){
		return id;
	}

	public int getUserId(){
		return userId;
	}

	public String getFileType(){
		return fileType;
	}
}