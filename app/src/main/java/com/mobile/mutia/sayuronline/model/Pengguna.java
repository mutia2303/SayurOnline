package com.mobile.mutia.sayuronline.model;

public class Pengguna {

    private String id_pengguna;
    private String username, password, nama, no_hp, email, alamat, status;

    public Pengguna(String id_pengguna, String username, String password, String nama, String no_hp, String email, String alamat, String status) {
        this.id_pengguna = id_pengguna;
        this.username = username;
        this.password = password;
        this.nama = nama;
        this.no_hp = no_hp;
        this.email = email;
        this.alamat = alamat;
        this.status = status;
    }

    public String getId_pengguna() {
        return this.id_pengguna;
    }

    public void setId_pengguna(String  id_pengguna) {
        this.id_pengguna = id_pengguna;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
