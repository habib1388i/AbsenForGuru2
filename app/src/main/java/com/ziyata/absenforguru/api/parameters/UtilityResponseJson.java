package com.ziyata.absenforguru.api.parameters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ziyata.absenforguru.db.Config;
import com.ziyata.absenforguru.db.Flag;
import com.ziyata.absenforguru.db.Gender;
import com.ziyata.absenforguru.db.Guru;
import com.ziyata.absenforguru.db.Jurusan;
import com.ziyata.absenforguru.db.Kelas;
import com.ziyata.absenforguru.db.Pelajaran;
import com.ziyata.absenforguru.db.Status;

import java.util.List;

public class UtilityResponseJson {

    @SerializedName("responseStatus")
    @Expose
    private String message;

    @SerializedName("config")
    @Expose
    private com.ziyata.absenforguru.db.Config config;

    @SerializedName("flag")
    @Expose
    private List<Flag> flag;

    @SerializedName("gender")
    @Expose
    private List<Gender> gender;

    @SerializedName("jurusan")
    @Expose
    private List<Jurusan> jurusan;

    @SerializedName("kelas")
    @Expose
    private List<Kelas> kelas;

    @SerializedName("guru")
    @Expose
    private List<Guru> guru;

    @SerializedName("pelajaran")
    private List<Pelajaran> pelajaran;

    @SerializedName("status")
    @Expose
    private List<Status> status;

    public String getMessage(){return message;}
    public void setMessage(String message){this.message = message;}

    public Config getConfig(){return config;}
    public void setConfig(Config config){this.config = config;}

    public List<Flag> getFlag(){return flag;}
    public void setFlag(List<Flag> flag){this.flag = flag;}

    public List<Gender> getGender(){return gender;}
    public void setGender(List<Gender> gender){this.gender = gender;}

    public List<Jurusan> getJurusan(){return jurusan;}
    public void setJurusan(List<Jurusan> jurusan){this.jurusan = jurusan;}

    public List<Kelas> getKelas(){return kelas;}
    public void setKelas(List<Kelas> kelas){this.kelas = kelas;}

    public List<Guru> getGuru(){return guru;}
    public void setGuru(List<Guru> guru){this.guru = guru;}

    public List<Pelajaran> getPelajaran(){return pelajaran;}
    public void setPelajaran(List<Pelajaran> pelajaran){this.pelajaran = pelajaran;}

    public List<Status> getStatus(){return status;}
    public void setStatus(List<Status> status){this.status = status;}
}
