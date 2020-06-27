package com.mobile.mutia.sayuronline.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConfigRetrofit {

    private static final String BASE_URL = "http://192.168.43.144:80/sayur/Rest_api/";
    private static ConfigRetrofit mInstance;
    private Retrofit retrofit;

    public static Retrofit setInit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized ConfigRetrofit getInstance() {
        if (mInstance == null) {
            mInstance = new ConfigRetrofit();
        }

        return mInstance;
    }

    public static ApiService getApi() {
        return setInit().create(ApiService.class);
    }
}
