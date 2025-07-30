package com.example.vts.Activity.CurrentSummaryReport;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.vts.base.CommonUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CurrentSummaryApi {
    private static Retrofit retrofit = null;
    public static Retrofit getRetrofitInstance(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(CommonUtils.MyPREFERENCES,Context.MODE_PRIVATE);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                System.out.println("lksjddlfsdf__ "+ sharedPreferences.getString(CommonUtils.shared_TOKENS,""));
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
