package com.ziyata.absenforguru.db;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;

public class ListPelajarandb extends RealmObject implements Serializable {

	@SerializedName("date")
	private String date;

	@SerializedName("flag")
	private String flag;

	@SerializedName("pelajaran_id")
	private String pelajaranId;

	@SerializedName("in")
	private String in;

	@SerializedName("date_created")
	private String dateCreated;

	@SerializedName("latitude")
	private String latitude;

	@SerializedName("longtitude")
	private String longtitude;

	@SerializedName("capture")
	private String capture;

	@SerializedName("id")
	private String id;

	@SerializedName("siswa_id")
	private String siswaId;

	@SerializedName("out")
	private String out;

	@SerializedName("status")
	private String status;

	public void setDate(String date){
		this.date = date;
	}

	public String getDate(){
		return date;
	}

	public void setFlag(String flag){
		this.flag = flag;
	}

	public String getFlag(){
		return flag;
	}

	public void setPelajaranId(String pelajaranId){
		this.pelajaranId = pelajaranId;
	}

	public String getPelajaranId(){
		return pelajaranId;
	}

	public void setIn(String in){
		this.in = in;
	}

	public String getIn(){
		return in;
	}

	public void setDateCreated(String dateCreated){
		this.dateCreated = dateCreated;
	}

	public String getDateCreated(){
		return dateCreated;
	}

	public void setLatitude(String latitude){
		this.latitude = latitude;
	}

	public String getLatitude(){
		return latitude;
	}

	public void setLongtitude(String longtitude){
		this.longtitude = longtitude;
	}

	public String getLongtitude(){
		return longtitude;
	}

	public void setCapture(String capture){
		this.capture = capture;
	}

	public String getCapture(){
		return capture;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setSiswaId(String siswaId){
		this.siswaId = siswaId;
	}

	public String getSiswaId(){
		return siswaId;
	}

	public void setOut(String out){
		this.out = out;
	}

	public String getOut(){
		return out;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"DaftarAbsenPelajaran{" +
			"date = '" + date + '\'' + 
			",flag = '" + flag + '\'' + 
			",pelajaran_id = '" + pelajaranId + '\'' + 
			",in = '" + in + '\'' + 
			",date_created = '" + dateCreated + '\'' + 
			",latitude = '" + latitude + '\'' + 
			",longtitude = '" + longtitude + '\'' + 
			",capture = '" + capture + '\'' + 
			",id = '" + id + '\'' + 
			",siswa_id = '" + siswaId + '\'' + 
			",out = '" + out + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}