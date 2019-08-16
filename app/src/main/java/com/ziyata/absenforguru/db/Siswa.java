package com.ziyata.absenforguru.db;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Siswa extends RealmObject implements Serializable {

    @PrimaryKey
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("nama")
    @Expose
    private String nama;

    @SerializedName("gender_id")
    @Expose
    private String genderId;

    @SerializedName("kelas_id")
    @Expose
    private String kelasId;

    @SerializedName("alamat")
    @Expose
    private String alamat;

    @SerializedName("phone")
    @Expose
    private String phone;

    @SerializedName("status_id")
    @Expose
    private String statusId;

    @SerializedName("photo")
    @Expose
    private String photo;

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("email")
    @Expose
    private String email;

    public String getId(){return id;}
    public void setId(String id){this.id=id;}

    public String getNama(){return nama;}
    public void setNama(String nama){this.nama=nama;}

    public String getGenderId(){return genderId;}
    public void setGenderId(String genderId){this.genderId=genderId;}

    public String getKelasId(){return kelasId;}
    public void setKelasId(String kelasId){this.kelasId=kelasId;}

    public String getAlamat(){return alamat;}
    public void setAlamat(String alamat){this.alamat=alamat;}

    public String getPhone(){return phone;}
    public void setPhone(String phone){this.phone=phone;}

    public String getStatusId(){return statusId;}
    public void setStatusId(String statusId){this.statusId=statusId;}

    public String getPhoto(){return photo;}
    public void setPhoto(String photo){this.photo=photo;}

    public String getUsername(){return username;}
    public void setUsername(String username){this.username=username;}

    public String getEmail(){return email;}
    public void setEmail(String email){this.email=email;}
}
