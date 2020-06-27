package com.mobile.mutia.sayuronline.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseProfile {

    @SerializedName("pesan")
    @Expose
    private String pesan;

    @SerializedName("sukses")
    @Expose
    private int sukses;

    @SerializedName("data_profile")
    @Expose
    private Pengguna pengguna;

    public ResponseProfile(String pesan, int sukses, Pengguna pengguna) {
        this.pesan = pesan;
        this.sukses = sukses;
        this.pengguna = pengguna;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public int getSukses() {
        return sukses;
    }

    public void setSukses(int sukses) {
        this.sukses = sukses;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }
}
