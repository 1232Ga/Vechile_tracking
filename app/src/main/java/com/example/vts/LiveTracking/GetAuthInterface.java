package com.example.vts.LiveTracking;

import com.example.vts.DevicePack.Deveicegetset;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface GetAuthInterface {
    @Headers("Content-Type:application/json;charset=UTF-8")
    @GET("services/api/LiveDataDetails/GetAuthToken")
    Call<GetAuthGetSet> registration2();
}
