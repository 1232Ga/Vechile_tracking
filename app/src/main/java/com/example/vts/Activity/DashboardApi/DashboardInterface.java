package com.example.vts.Activity.DashboardApi;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface DashboardInterface {
    @Headers("Content-Type:application/json;charset=UTF-8")
    @POST("services/DashBoard/getAllDeviceLocations")
    Call<Dashbaordgetset> registration(@Body DashboardParamter parameter);
}
