package com.example.vts.DriverPack;

import com.example.vts.DevicePack.Deveicegetset;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface DriverInterface {

    @Headers("Content-Type:application/json;charset=UTF-8")
    @GET("services/api/DriverManagement/listing")
    Call<List<Drivergetset>> registration2();
}
