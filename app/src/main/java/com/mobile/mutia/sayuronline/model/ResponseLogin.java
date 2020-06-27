package com.mobile.mutia.sayuronline.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseLogin {

    @SerializedName("pesan")
    @Expose
    private String pesan;
    @SerializedName("sukses")
    @Expose
    private boolean sukses;
    @SerializedName("data_login")
    @Expose
    private Pengguna pengguna;

    public ResponseLogin(String pesan, boolean sukses, Pengguna pengguna) {
        this.pesan = pesan;
        this.sukses = sukses;
        this.pengguna = pengguna;
    }

    public String getPesan() {
        return pesan;
    }

    public boolean isSukses() {
        return sukses;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }
}
