package com.mobile.mutia.sayuronline.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseTransaksi {

    @SerializedName("pesan")
    private String pesan;

    @SerializedName("data")
    private int data;

    @SerializedName("datanya")
    private List<Transaksi> datanya;

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

    public List<Transaksi> getDatanya() {
        return datanya;
    }

    public void setDatanya(List<Transaksi> datanya) {
        this.datanya = datanya;
    }
}
