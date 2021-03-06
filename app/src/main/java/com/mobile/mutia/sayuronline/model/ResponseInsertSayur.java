package com.mobile.mutia.sayuronline.model;

import com.google.gson.annotations.SerializedName;

public class ResponseInsertSayur {

    @SerializedName("pesan")
    private String pesan;

    @SerializedName("data")
    private int data;

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}
