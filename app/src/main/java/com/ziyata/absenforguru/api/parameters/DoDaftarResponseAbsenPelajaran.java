package com.ziyata.absenforguru.api.parameters;

import com.google.gson.annotations.SerializedName;
import com.ziyata.absenforguru.db.ListPelajarandb;

import java.util.List;

public class DoDaftarResponseAbsenPelajaran {

	@SerializedName("data")
	private List<ListPelajarandb> data;

	@SerializedName("responseStatus")
	private String responseStatus;

	@SerializedName("message")
	private String message;

	public void setData(List<ListPelajarandb> data){
		this.data = data;
	}

	public List<ListPelajarandb> getData(){
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
			"DoDaftarResponseAbsenPelajaran{" +
			"data = '" + data + '\'' + 
			",responseStatus = '" + responseStatus + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}