package com.example.vts.TriplistPack;

import com.example.vts.Activity.UserDelete.UserDeletegetset;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface EditTripInterface {
    @Headers("Content-Type:application/json;charset=UTF-8")
    @POST("services/api/EravanaInfo")
    Call<UserDeletegetset> registration(@Body EditParameter editParameter);
}
