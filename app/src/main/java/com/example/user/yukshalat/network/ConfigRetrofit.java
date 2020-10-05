package com.example.user.yukshalat.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConfigRetrofit {

    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://muslimsalat.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static ApiService service = retrofit.create(ApiService.class);

}
