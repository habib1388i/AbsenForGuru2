package com.ziyata.absenforguru.db;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;

public class Pelajaran extends RealmObject implements Serializable {
    @SerializedName("name")
    private String name;

    @SerializedName("id")

    private String id;

    @SerializedName("guru_id")
    private String guruId;

    @SerializedName("kelas_id")
    private String kelasId;

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return id;
    }

    public void setGuruId(String guruId){
        this.guruId = guruId;
    }

    public String getGuruId(){
        return guruId;
    }

    public void setKelasId(String kelasId){
        this.kelasId = kelasId;
    }

    public String getKelasId(){
        return kelasId;
    }

    @Override
    public String toString(){
        return
                "Pelajaran{" +
                        "name = '" + name + '\'' +
                        ",id = '" + id + '\'' +
                        ",guru_id = '" + guruId + '\'' +
                        ",kelas_id = '" + kelasId + '\'' +
                        "}";
    }
}
