package com.mobile.mutia.sayuronline.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseSayur {

    @SerializedName("pesan")
    private String pesan;

    @SerializedName("data")
    private int data;

    @SerializedName("datanya")
    private List<Sayur> datanya;

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

    public List<Sayur> getDatanya() {
        return datanya;
    }

    public void setDatanya(List<Sayur> datanya) {
        this.datanya = datanya;
    }
}
