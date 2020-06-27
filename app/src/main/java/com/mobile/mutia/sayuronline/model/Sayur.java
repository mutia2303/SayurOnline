package com.mobile.mutia.sayuronline.model;

import com.google.gson.annotations.SerializedName;

public class Sayur {

    @SerializedName("id_sayur")
    private String id_sayur;
    @SerializedName("id_pasar")
    private String id_pasar;
    @SerializedName("id_pengguna")
    private String id_pengguna;
    @SerializedName("nama_sayur")
    private String nama_sayur;
    @SerializedName("nama_pasar")
    private String nama_pasar;
    @SerializedName("nama")
    private String nama;
    @SerializedName("gambar")
    private String gambar;
    @SerializedName("stok")
    private String stok;
    @SerializedName("keterangan")
    private String keterangan;
    @SerializedName("harga")
    private String harga;
    @SerializedName("username")
    private String username;

    public Sayur(String id_sayur, String id_pasar, String id_pengguna, String nama_sayur, String nama_pasar, String nama, String gambar, String keterangan, String stok, String harga, String username) {
        this.id_sayur = id_sayur;
        this.id_pasar = id_pasar;
        this.id_pengguna = id_pengguna;
        this.nama_sayur = nama_sayur;
        this.nama_pasar = nama_pasar;
        this.nama = nama;
        this.gambar = gambar;
        this.keterangan = keterangan;
        this.stok = stok;
        this.harga = harga;
        this.username = username;
    }

    public String getId_sayur() {
        return id_sayur;
    }

    public void setId_sayur(String id_sayur) {
        this.id_sayur = id_sayur;
    }

    public String getId_pasar() {
        return id_pasar;
    }

    public void setId_pasar(String id_pasar) {
        this.id_pasar = id_pasar;
    }

    public String getId_pengguna() {
        return id_pengguna;
    }

    public void setId_pengguna(String id_pengguna) {
        this.id_pengguna = id_pengguna;
    }

    public String getNama_sayur() {
        return nama_sayur;
    }

    public void setNama_sayur(String nama_sayur) {
        this.nama_sayur = nama_sayur;
    }

    public String getNama_pasar() {
        return nama_pasar;
    }

    public void setNama_pasar(String nama_pasar) {
        this.nama_pasar = nama_pasar;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getStok() {
        return stok;
    }

    public void setStok(String stok) {
        this.stok = stok;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
