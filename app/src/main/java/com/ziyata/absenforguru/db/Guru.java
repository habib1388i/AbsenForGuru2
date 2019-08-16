package com.ziyata.absenforguru.db;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;

public class Guru extends RealmObject implements Serializable{

    @SerializedName("name")
    private String name;

    @SerializedName("id")
    private String id;

    @SerializedName("status")
    private String status;

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

    public void setStatus(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }

    @Override
    public String toString(){
        return
                "Guru{" +
                        "name = '" + name + '\'' +
                        ",id = '" + id + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}
