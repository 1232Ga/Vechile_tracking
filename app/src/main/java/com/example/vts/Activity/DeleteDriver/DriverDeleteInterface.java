package com.example.vts.Activity.DeleteDriver;

import com.example.vts.Activity.UserDelete.UserDeletegetset;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface DriverDeleteInterface {
    @Headers("Content-Type:application/json;charset=UTF-8")
    @DELETE("services/api/DriverManagement/{DriverId}")
    Call<UserDeletegetset> registration(@Path("DriverId") String itemId);
}
