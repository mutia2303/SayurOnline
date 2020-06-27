package com.mobile.mutia.sayuronline.model;

public class Profile {

    private int id_pengguna;
    private String username, password, nama, no_hp, email, alamat;

    public Profile(int id_pengguna, String username, String password, String nama, String no_hp, String email, String alamat) {
        this.id_pengguna = id_pengguna;
        this.username = username;
        this.password = password;
        this.nama = nama;
        this.no_hp = no_hp;
        this.email = email;
        this.alamat = alamat;
    }

    public int getId_pengguna() {
        return id_pengguna;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getNama() {
        return nama;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public String getEmail() {
        return email;
    }

    public String getAlamat() {
        return alamat;
    }
}
