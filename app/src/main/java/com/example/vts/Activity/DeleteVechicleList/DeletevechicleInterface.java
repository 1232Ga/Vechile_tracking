package com.example.vts.Activity.DeleteVechicleList;

import com.example.vts.Activity.UserDelete.UserDeletegetset;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface DeletevechicleInterface {
    @Headers("Content-Type:application/json;charset=UTF-8")
    @DELETE("services/api/VehicleManagement/{VehicleId}")
    Call<UserDeletegetset> registration(@Path("VehicleId") String itemId);
}
