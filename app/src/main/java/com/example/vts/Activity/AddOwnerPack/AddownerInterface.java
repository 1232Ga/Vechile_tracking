package com.example.vts.Activity.AddOwnerPack;

import com.example.vts.Activity.AddDevice.AddDevicegetset;
import com.example.vts.Activity.AddDriver.AddDriverParameter;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface AddownerInterface {
    @Headers("Content-Type:application/json;charset=UTF-8")
    @POST("services/api/VehicleOwnerManagement")
    Call<AddDevicegetset> registration2(@Body AddOwnerParameter roleParameter);
}
