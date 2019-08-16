package com.ziyata.absenforguru.db;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Gender extends RealmObject implements Serializable {

    @PrimaryKey
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String name;

    public String getId(){return id;}
    public void setId(String id){this.id=id;}

    public String getName(){return name;}
    public void setName(){this.name=name;}


}