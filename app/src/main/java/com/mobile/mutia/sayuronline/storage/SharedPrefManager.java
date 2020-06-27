package com.mobile.mutia.sayuronline.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.mobile.mutia.sayuronline.model.Pengguna;
import com.mobile.mutia.sayuronline.model.User;

public class SharedPrefManager {

    private static SharedPrefManager mInstance;
    private Context context;

    private static final String PREF_NAME = "SayurOnlinePreff";

    private SharedPrefManager(Context context) {
        this.context = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    public void saveUser (Pengguna pengguna) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("id_pengguna", pengguna.getId_pengguna());
        editor.putString("username", pengguna.getUsername());
        editor.putString("password", pengguna.getPassword());
        editor.putString("nama", pengguna.getNama());
        editor.putString("no_hp", pengguna.getNo_hp());
        editor.putString("email", pengguna.getEmail());
        editor.putString("alamat", pengguna.getAlamat());
        editor.putString("status", pengguna.getStatus());

        editor.apply();
    }

    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        return sharedPreferences.getString("id_pengguna", null) != null;
    }

    public boolean isLoggedOut() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        return sharedPreferences.getString("id_pengguna", null) == null;
    }

    public Pengguna getUser() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        return new Pengguna(
                sharedPreferences.getString("id_pengguna", null),
                sharedPreferences.getString("username", null),
                sharedPreferences.getString("password", null),
                sharedPreferences.getString("nama", null),
                sharedPreferences.getString("no_hp", null),
                sharedPreferences.getString("email", null),
                sharedPreferences.getString("alamat", null),
                sharedPreferences.getString("status", null)
        );
    }

    public void clear() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.clear();
        editor.apply();
    }

    public void saveUserFB (User user) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("id_pengguna", user.getId());
        editor.putString("username", user.getUsername());
        editor.putString("password", user.getPassword());
        editor.putString("nama", user.getNama());
        editor.putString("no_hp", user.getNo_hp());
        editor.putString("email", user.getEmail());
        editor.putString("alamat", user.getAlamat());
        editor.putString("status", user.getStatus());

        editor.apply();
    }

    public User getUserFB() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        return new User(
                sharedPreferences.getString("id_pengguna", null),
                sharedPreferences.getString("username", null),
                sharedPreferences.getString("password", null),
                sharedPreferences.getString("nama", null),
                sharedPreferences.getString("no_hp", null),
                sharedPreferences.getString("email", null),
                sharedPreferences.getString("alamat", null),
                sharedPreferences.getString("search", null),
                sharedPreferences.getString("status", null)
        );
    }

}