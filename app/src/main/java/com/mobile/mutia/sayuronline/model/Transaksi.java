package com.mobile.mutia.sayuronline.model;

import com.google.gson.annotations.SerializedName;

public class Transaksi {

    @SerializedName("id_transaksi")
    private String id_transaksi;
    @SerializedName("id_pengguna")
    private String id_pengguna;
    @SerializedName("username_penjual")
    private String username_penjual;
    @SerializedName("nama_penjual")
    private String nama_penjual;
    @SerializedName("nama")
    private String nama;
    @SerializedName("id_sayur")
    private String id_sayur;
    @SerializedName("id_pasar")
    private String id_pasar;
    @SerializedName("nama_pasar")
    private String nama_pasar;
    @SerializedName("nama_sayur")
    private String nama_sayur;
    @SerializedName("jumlah_beli")
    private String jumlah_beli;
    @SerializedName("total")
    private String total;
    @SerializedName("keterangan")
    private String keterangan;

    public Transaksi(String id_transaksi, String id_pengguna, String username_penjual, String nama_penjual, String nama, String id_sayur, String id_pasar, String nama_pasar, String nama_sayur, String jumlah_beli, String total, String keterangan) {
        this.id_transaksi = id_transaksi;
        this.id_pengguna = id_pengguna;
        this.username_penjual = username_penjual;
        this.nama_penjual = nama_penjual;
        this.nama = nama;
        this.id_sayur = id_sayur;
        this.id_pasar = id_pasar;
        this.nama_pasar = nama_pasar;
        this.nama_sayur = nama_sayur;
        this.jumlah_beli = jumlah_beli;
        this.total = total;
        this.keterangan = keterangan;
    }

    public String getId_transaksi() {
        return id_transaksi;
    }

    public void setId_transaksi(String id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    public String getId_pengguna() {
        return id_pengguna;
    }

    public void setId_pengguna(String id_pengguna) {
        this.id_pengguna = id_pengguna;
    }

    public String getUsername_penjual() {
        return username_penjual;
    }

    public void setUsername_penjual(String username_penjual) {
        this.username_penjual = username_penjual;
    }

    public String getNama_penjual() {
        return nama_penjual;
    }

    public void setNama_penjual(String nama_penjual) {
        this.nama_penjual = nama_penjual;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
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

    public String getNama_pasar() {
        return nama_pasar;
    }

    public void setNama_pasar(String nama_pasar) {
        this.nama_pasar = nama_pasar;
    }

    public String getNama_sayur() {
        return nama_sayur;
    }

    public void setNama_sayur(String nama_sayur) {
        this.nama_sayur = nama_sayur;
    }

    public String getJumlah_beli() {
        return jumlah_beli;
    }

    public void setJumlah_beli(String jumlah_beli) {
        this.jumlah_beli = jumlah_beli;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
