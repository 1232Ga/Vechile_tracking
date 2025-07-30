package com.example.vts.RoleListPack;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.vts.RolePack.RoleInterface;
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

public class RoleListApi {
//    private  static Retrofit getRetrofitInstance()
//    {
//        HttpLoggingInterceptor httpLoggingInterceptor= new HttpLoggingInterceptor();
//        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .readTimeout(60, TimeUnit.SECONDS)
//                .connectTimeout(60, TimeUnit.SECONDS)
//                .addInterceptor(httpLoggingInterceptor)
//                .build();
//        Gson gson= new GsonBuilder().setLenient().create();
//        return  new  Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson)).baseUrl("http://staging.bluehawk.ai/")
//                .client(okHttpClient)
//                .build();
//
//    }
//    public static RolelistInterface getApiService()
//    {
//        return  getRetrofitInstance().create(RolelistInterface.class);
//    }





    private static Retrofit retrofit = null;
    public static Retrofit getRetrofitInstance(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(CommonUtils.MyPREFERENCES,Context.MODE_PRIVATE);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request newRequest  = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer " + sharedPreferences.getString(CommonUtils.shared_TOKENS,""))
                        .build();
                return chain.proceed(newRequest);
            }
        }).build();
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://vtsdev.bluehawk.ai/")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
