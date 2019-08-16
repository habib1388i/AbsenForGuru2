package com.ziyata.absenforguru.api.parameters;

import com.google.gson.annotations.SerializedName;

public class DoRequestRegister{

	@SerializedName("password")
	private String password;

	@SerializedName("phone")
	private String phone;

	@SerializedName("name")
	private String name;

	@SerializedName("photo")
	private String photo;

	@SerializedName("id")
	private String id;

	@SerializedName("gender_id")
	private String genderId;

	@SerializedName("email")
	private String email;

	@SerializedName("alamat")
	private String alamat;

	@SerializedName("status")
	private String status;

	@SerializedName("username")
	private String username;

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return phone;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setPhoto(String photo){
		this.photo = photo;
	}

	public String getPhoto(){
		return photo;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setGenderId(String genderId){
		this.genderId = genderId;
	}

	public String getGenderId(){
		return genderId;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setAlamat(String alamat){
		this.alamat = alamat;
	}

	public String getAlamat(){
		return alamat;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
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
			"DoRequestRegister{" + 
			"password = '" + password + '\'' + 
			",phone = '" + phone + '\'' + 
			",name = '" + name + '\'' + 
			",photo = '" + photo + '\'' + 
			",id = '" + id + '\'' + 
			",gender_id = '" + genderId + '\'' + 
			",email = '" + email + '\'' + 
			",alamat = '" + alamat + '\'' + 
			",status = '" + status + '\'' + 
			",username = '" + username + '\'' + 
			"}";
		}
}