package com.ziyata.absenforguru.api.parameters;

import com.google.gson.annotations.SerializedName;

public class DoDaftarRequestAbsenPelajaran {

	@SerializedName("start")
	private String start;

	@SerializedName("end")
	private String end;

	public void setStart(String start){
		this.start = start;
	}

	public String getStart(){
		return start;
	}

	public void setEnd(String end){
		this.end = end;
	}

	public String getEnd(){
		return end;
	}

	@Override
 	public String toString(){
		return 
			"DoDaftarRequestAbsenPelajaran{" +
			"start = '" + start + '\'' + 
			",end = '" + end + '\'' + 
			"}";
		}
}