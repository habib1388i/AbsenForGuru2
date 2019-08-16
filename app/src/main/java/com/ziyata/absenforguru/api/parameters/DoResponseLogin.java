package com.ziyata.absenforguru.api.parameters;

import com.google.gson.annotations.SerializedName;
import com.ziyata.absenforguru.db.Siswa;

public class DoResponseLogin {

	@SerializedName("data")
	private Siswa data;

	@SerializedName("responseStatus")
	private String responseStatus;



	public void setData(Siswa data){
		this.data = data;
	}

	public Siswa getData(){
		return data;
	}

	public void setResponseStatus(String responseStatus){
		this.responseStatus = responseStatus;
	}

	public String getResponseStatus(){
		return responseStatus;
	}

	@Override
 	public String toString(){
		return 
			"DoResponseLogin{" +
			"data = '" + data + '\'' + 
			",responseStatus = '" + responseStatus + '\'' + 
			"}";
		}
}