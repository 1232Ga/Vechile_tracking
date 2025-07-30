package com.example.vts.AllVechdetails;

import com.example.vts.VechoeownerPack.Vechicleownergetset;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface AllVechidetilaInterface {
    @Headers("Content-Type:application/json;charset=UTF-8")
    @GET("services/api/VehicleManagement/{id}")
    Call<Allvechiclownergetset> registration2(@Path("id") String itemId);
}
