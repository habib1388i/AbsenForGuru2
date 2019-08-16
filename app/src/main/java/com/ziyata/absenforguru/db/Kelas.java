package com.ziyata.absenforguru.db;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Chandra on 2019-07-11.
 * Bangunindo TBK
 * chandra@bangunindo.com
 */
public class Kelas extends RealmObject implements Serializable {

    @PrimaryKey
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("kelas")
    @Expose
    private String kelas;

    @SerializedName("jurusan_id")
    @Expose
    private String jurusanId;

    @SerializedName("in_time")
    @Expose
    private String inTime;

    @SerializedName("out_time")
    @Expose
    private String outTime;

    public String getId(){return id;}
    public void setId(String id){this.id=id;}

    public String getKelas(){return kelas;}
    public void setKelas(String kelas){this.kelas=kelas;}

    public String getJurusanId(){return jurusanId;}
    public void setJurusanId(String jurusanId){this.jurusanId=jurusanId;}

    public String getInTime(){return inTime;}
    public void setInTime(String inTime){this.inTime=inTime;}

    public String getOutTime(){return outTime;}
    public void setOutTime(String outTime){this.outTime=outTime;}


}
