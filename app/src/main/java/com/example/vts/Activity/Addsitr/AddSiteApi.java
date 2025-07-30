package com.example.vts.Activity.Addsitr;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddSiteApi {
    private  static Retrofit getRetrofitInstance()
    {
        HttpLoggingInterceptor httpLoggingInterceptor= new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .build();
        Gson gson= new GsonBuilder().setLenient().create();
        return  new  Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson)).baseUrl("http://vtsdev.bluehawk.ai/")
                .client(okHttpClient)
                .build();

    }
    public static AddSiteInterface getApiService()
    {
        return  getRetrofitInstance().create(AddSiteInterface.class);
    }
}
