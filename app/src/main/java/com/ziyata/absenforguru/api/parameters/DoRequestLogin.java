package com.ziyata.absenforguru.api.parameters;

import com.google.gson.annotations.SerializedName;

public class DoRequestLogin{

	@SerializedName("password")
	private String password;

	@SerializedName("username")
	private String username;

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	@Override
 	public String toString(){
		return 
			"DoRequestLogin{" + 
			"password = '" + password + '\'' + 
			",username = '" + username + '\'' + 
			"}";
		}
}