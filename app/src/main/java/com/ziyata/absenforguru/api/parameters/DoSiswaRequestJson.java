package com.ziyata.absenforguru.api.parameters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DoSiswaRequestJson {

    @SerializedName("siswa_id")
    @Expose
    private String siswaId;



    public String getSiswaId(){return siswaId;}
    public void setSiswaId(String siswaId){this.siswaId=siswaId;}
}
