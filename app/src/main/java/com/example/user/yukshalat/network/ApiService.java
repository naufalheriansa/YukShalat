package com.example.user.yukshalat.network;

import com.example.user.yukshalat.responseApi.ResponseApi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("{Location}/{Date}/weekly.json")
    Call<ResponseApi> scheduleSholat
            (@Path("Location") String location,
             @Path("Date") String date);

}
