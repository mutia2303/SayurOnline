package com.mobile.mutia.sayuronline.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseRegister {

    @SerializedName("pesan")
    private String pesan;

    @SerializedName("data")
    private int data;

    public ResponseRegister(String pesan, int data) {
        this.pesan = pesan;
        this.data = data;
    }

    public void setPesan(String pesan){
        this.pesan = pesan;
    }

    public String getPesan(){
        return pesan;
    }

    public void setData(int data){
        this.data = data;
    }

    public int getData(){
        return data;
    }

}
