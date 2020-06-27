package com.mobile.mutia.sayuronline.model;

public class User {

    String id, username, password, nama, no_hp, email, alamat, search;
    String status;

    public User() {
    }

    public User(String id, String username, String password, String nama, String no_hp, String email, String alamat, String search, String status) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nama = nama;
        this.no_hp = no_hp;
        this.email = email;
        this.alamat = alamat;
        this.search = search;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String  getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
