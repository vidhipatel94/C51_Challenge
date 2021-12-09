package com.c51.c51challenge.apis;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIHelper {

    public static final String BASE_URL = "https://api.jsonbin.io/";

    private static RestAPIs restAPIs;

    public static RestAPIs getAPIs() {
        if (restAPIs == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build();
            restAPIs = retrofit.create(RestAPIs.class);
        }
        return restAPIs;
    }
}
