package com.example.vts.DriverPack;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface DriverIDetilalnterface {

    @Headers("Content-Type:application/json;charset=UTF-8")
    @GET("services//api/DriverManagement/{id}")
    Call<Drivergetset> registration2(@Path("id") String itemId);
}
