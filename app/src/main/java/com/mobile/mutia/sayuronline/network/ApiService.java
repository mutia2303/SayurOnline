package com.mobile.mutia.sayuronline.network;

import com.mobile.mutia.sayuronline.model.ResponseInsertEditHapusSayur;
import com.mobile.mutia.sayuronline.model.ResponseInsertTransaksi;
import com.mobile.mutia.sayuronline.model.ResponseLogin;
import com.mobile.mutia.sayuronline.model.ResponsePassword;
import com.mobile.mutia.sayuronline.model.ResponseProfile;
import com.mobile.mutia.sayuronline.model.ResponseRegister;
import com.mobile.mutia.sayuronline.model.ResponseSayur;
import com.mobile.mutia.sayuronline.model.ResponseTransaksi;
import com.mobile.mutia.sayuronline.notification.Response;
import com.mobile.mutia.sayuronline.notification.Sender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @FormUrlEncoded
    @POST ("register")
    Call<ResponseRegister> insertRegister(
            @Field("username") String username,
            @Field("password") String password,
            @Field("nama") String nama,
            @Field("no_hp") String no_hp,
            @Field("email") String email,
            @Field("alamat") String alamat,
            @Field("status") String status
    );

    @FormUrlEncoded
    @POST ("login")
    Call<ResponseLogin> login (
            @Field("email") String email,
            @Field("password") String password
    );

    @GET("get_sayur1")
    Call<ResponseSayur> getSayur1();

    @GET("get_sayur2")
    Call<ResponseSayur> getSayur2();

    @GET("get_sayur3")
    Call<ResponseSayur> getSayur3();

    @GET("get_sayur4")
    Call<ResponseSayur> getSayur4();

    @GET("get_sayur5")
    Call<ResponseSayur> getSayur5();

    @FormUrlEncoded
    @POST("insert_sayur")
    Call<ResponseInsertEditHapusSayur> insertSayur(
            @Field("id_pengguna") String id_pengguna,
            @Field("id_pasar") String id_pasar,
            @Field("nama_sayur") String nama_sayur,
            @Field("stok") String stok,
            @Field("keterangan") String keterangan,
            @Field("harga") String harga,
            @Field("gambar") String gambar
            );

    @FormUrlEncoded
    @POST("edit_sayur")
    Call<ResponseInsertEditHapusSayur> edit_sayur(
            @Field("id_pengguna") String id_pengguna,
            @Field("id_sayur") String id_sayur,
            @Field("id_pasar") String id_pasar,
            @Field("nama_sayur") String nama_sayur,
            @Field("stok") String stok,
            @Field("keterangan") String keterangan,
            @Field("harga") String harga
    );

    @FormUrlEncoded
    @POST("edit_gambar")
    Call<ResponseInsertEditHapusSayur> edit_gambar(
            @Field("id_sayur") String id_sayur,
            @Field("gambar") String gambar
    );

    @FormUrlEncoded
    @POST("hapus_sayur")
    Call<ResponseInsertEditHapusSayur> hapus_sayur(
            @Field("id_sayur") String id_sayur
    );

    @FormUrlEncoded
    @POST("transaksi")
    Call<ResponseInsertTransaksi> transaksi(
            @Field("id_pengguna") String id_pengguna,
            @Field("id_sayur") String id_sayur,
            @Field("id_pasar") String id_pasar,
            @Field("username_penjual") String username_penjual,
            @Field("nama_penjual") String nama_penjual,
            @Field("jumlah_beli") String jumlah_beli
    );

    @FormUrlEncoded
    @POST("get_transaksi")
    Call<ResponseTransaksi> getTransaksi(
            @Field("pengguna") String pengguna,
            @Field("status") String status,
            @Field("username") String username
    );


    @FormUrlEncoded
    @POST("profile/{id_pengguna}")
    Call<ResponseProfile> updateProfile(
            @Path("id_pengguna") String id_pengguna,
            @Field("username") String username,
            @Field("nama") String nama,
            @Field("no_hp") String no_hp,
            @Field("alamat") String alamat

    );

    @FormUrlEncoded
    @POST("ubah_password/{id_pengguna}")
    Call<ResponsePassword> ubahPassword(
            @Path("id_pengguna") String id_pengguna,
            @Field("password") String password
    );

    //notification message

    @Headers({
            "Content-Type:application/json",
            "Authorization:key=AAAArQ3X9pM:APA91bH8oYc20Wtyyn493DuL3XGdlfiOuEIMCD1B0xwIjWvw8ymwOtgkiJYHCx0EAcLt0U6XREKz7MPXe-HtFG2pvYGYdw7-pdbUQ6EgXdv6Vc6j00nRRhS-cFWfHaQzU_DUELz5e3nF"
    })

    @POST("fcm/send")
    Call<Response> sendNotification(@Body Sender body);
}
