package com.example.projectda.utils.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClientSTT {
    public static final String BASE_URL="https://viettelgroup.ai";
    public static Retrofit retrofit=null;

    public static Retrofit getApiClient(){
        if (retrofit==null){
            retrofit= new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
