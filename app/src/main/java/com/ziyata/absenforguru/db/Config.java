package com.ziyata.absenforguru.db;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;

public class Config extends RealmObject implements Serializable {

	@SerializedName("location_name")
	private String locationName;

	@SerializedName("address")
	private String address;

	@SerializedName("app_version")
	private String appVersion;

	@SerializedName("diameter")
	private String diameter;

	@SerializedName("latitude")
	private String latitude;

	@SerializedName("longtitude")
	private String longtitude;

	@SerializedName("id")
	private String id;

	@SerializedName("lock_location")
	private String lockLocation;

	public void setLocationName(String locationName){
		this.locationName = locationName;
	}

	public String getLocationName(){
		return locationName;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
	}

	public void setAppVersion(String appVersion){
		this.appVersion = appVersion;
	}

	public String getAppVersion(){
		return appVersion;
	}

	public void setDiameter(String diameter){
		this.diameter = diameter;
	}

	public String getDiameter(){
		return diameter;
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

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setLockLocation(String lockLocation){
		this.lockLocation = lockLocation;
	}

	public String getLockLocation(){
		return lockLocation;
	}

	@Override
 	public String toString(){
		return 
			"Config{" + 
			"location_name = '" + locationName + '\'' + 
			",address = '" + address + '\'' + 
			",app_version = '" + appVersion + '\'' + 
			",diameter = '" + diameter + '\'' + 
			",latitude = '" + latitude + '\'' + 
			",longtitude = '" + longtitude + '\'' + 
			",id = '" + id + '\'' + 
			",lock_location = '" + lockLocation + '\'' + 
			"}";
		}
}