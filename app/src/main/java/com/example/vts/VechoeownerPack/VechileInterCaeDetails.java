package com.example.vts.VechoeownerPack;

import com.example.vts.Activity.UserDelete.UserDeletegetset;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface VechileInterCaeDetails {
    @Headers("Content-Type:application/json;charset=UTF-8")
    @GET("services/api/VehicleOwnerManagement/{id}")
    Call<Vechicleownergetset> registration(@Path("id") String itemId);
}
