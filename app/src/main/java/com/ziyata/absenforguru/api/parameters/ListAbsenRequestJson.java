package com.ziyata.absenforguru.api.parameters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.annotations.PrimaryKey;

public class ListAbsenRequestJson {
    @PrimaryKey
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("siswa_id")
    @Expose
    private String siswaId;

    @SerializedName("in")
    @Expose
    private String in;

    @SerializedName("out")
    @Expose
    private String out;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("capture")
    @Expose
    private String capture;

    @SerializedName("latitude")
    @Expose
    private String latitude;

    @SerializedName("longtitude")
    @Expose
    private String longtitude;

    @SerializedName("date_created")
    @Expose
    private String dateCreated;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("flag")
    @Expose
    private String flag;

    public String getId(){return id;}
    public void setId(String id){this.id=id;}

    public String getSiswaId(){return siswaId;}
    public void setSiswaId(String siswaId){this.siswaId=siswaId;}

    public String getIn(){return in;}
    public void setIn(String in){this.in=in;}

    public String getOut(){return out;}
    public void setOut(String out){this.out=out;}

    public String getDate(){return date;}
    public void setDate(String date){this.date=date;}

    public String getCapture(){return capture;}
    public void setCapture(String capture){this.capture=capture;}

    public String getLatitude(){return latitude;}
    public void setLatitude(String latitude){this.latitude=latitude;}

    public String getLongtitude(){return longtitude;}
    public void setLongtitude(String longtitude){this.longtitude=longtitude;}

    public String getDateCreated(){return dateCreated;}
    public void setDateCreated(String dateCreated){this.dateCreated=dateCreated;}

    public String getStatus(){return status;}
    public void setStatus(String status){this.status=status;}

    public String getFlag(){return flag;}
    public void setFlag(String flag){this.flag=flag;}




}
