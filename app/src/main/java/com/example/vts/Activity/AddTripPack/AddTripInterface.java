package com.example.vts.Activity.AddTripPack;

import com.example.vts.Activity.AddVechicle.AddVechiclegetset;
import com.example.vts.Activity.AddVechicle.AddvechicleParameter;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface AddTripInterface {
    @Headers("Content-Type:application/json;charset=UTF-8")
    @POST("services/api/EravanaInfo")
    Call<AddTripgetset> registration2(@Body AddTripParameter roleParameter);
}
