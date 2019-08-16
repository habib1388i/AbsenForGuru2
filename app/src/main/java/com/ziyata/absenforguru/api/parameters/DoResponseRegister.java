package com.ziyata.absenforguru.api.parameters;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DoResponseRegister{

	@SerializedName("data")
	private List<Object> data;

	@SerializedName("responseStatus")
	private String responseStatus;

	@SerializedName("message")
	private String message;

	public void setData(List<Object> data){
		this.data = data;
	}

	public List<Object> getData(){
		return data;
	}

	public void setResponseStatus(String responseStatus){
		this.responseStatus = responseStatus;
	}

	public String getResponseStatus(){
		return responseStatus;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	@Override
 	public String toString(){
		return 
			"DoResponseRegister{" + 
			"data = '" + data + '\'' + 
			",responseStatus = '" + responseStatus + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}