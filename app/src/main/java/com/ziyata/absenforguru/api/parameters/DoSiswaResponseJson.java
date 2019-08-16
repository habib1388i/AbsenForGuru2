package com.ziyata.absenforguru.api.parameters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ziyata.absenforguru.db.Siswa;

import java.util.List;

public class DoSiswaResponseJson {

    @SerializedName("responseStatus")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private List<Siswa> data;

    public String getStatus(){return status;}
    public void setStatus(String status){this.status=status;}

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public List<Siswa> getData() {
        return data;
    }
    public void setData(List<Siswa> data) {
        this.data = data;
    }
}
