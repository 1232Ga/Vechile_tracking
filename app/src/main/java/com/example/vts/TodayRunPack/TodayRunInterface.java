package com.example.vts.TodayRunPack;

import com.example.vts.LoginPack.Prameter;
import com.example.vts.Vechilelistpack.Vechiclegetset;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface TodayRunInterface {
    @Headers("Content-Type:application/json;charset=UTF-8")
    @POST("services/api/EravanaInfo/DashBoard/TodayRunVehicle")
    Call<List<TodayVechiclegetset>> registration2(@Body ToodayRunParameter parameter);
}
