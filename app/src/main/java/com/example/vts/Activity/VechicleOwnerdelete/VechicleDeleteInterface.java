package com.example.vts.Activity.VechicleOwnerdelete;

import com.example.vts.Activity.UserDelete.UserDeletegetset;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface VechicleDeleteInterface {
    @Headers("Content-Type:application/json;charset=UTF-8")
    @DELETE("services/api/VehicleOwnerManagement/{VehicleOwnerId}")
    Call<UserDeletegetset> registration(@Path("VehicleOwnerId") String itemId);
}
