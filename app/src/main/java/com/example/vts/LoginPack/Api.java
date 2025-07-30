package com.example.vts.LoginPack;



import android.content.Context;
import android.content.SharedPreferences;

import com.example.vts.base.CommonUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {
    SharedPreferences sharedPreferences;
    Context context;
    private static Retrofit retrofit = null;
//crash ho gaye

    /*public static ApiInterface getClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(logging).build();
        // change your base URL
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl("https://uat.bluehawk.ai/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        //Creating object for our interface
        ApiInterface api = retrofit.create(ApiInterface.class);
        return api; // return the APIInterface object
    }*/

    Api (Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(CommonUtils.MyPREFERENCES,0);
    }

    private static Retrofit getRetrofitInstance()
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



    public static ApiInterface getApiService()
    {
        return  getRetrofitInstance().create(ApiInterface.class);
    }



}
