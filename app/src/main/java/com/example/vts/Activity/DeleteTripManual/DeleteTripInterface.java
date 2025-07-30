package com.example.vts.Activity.DeleteTripManual;

import com.example.vts.Activity.UserDelete.UserDeletegetset;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface DeleteTripInterface {
    @Headers("Content-Type:application/json;charset=UTF-8")
    @DELETE("services/api/EravanaInfo/{EravanaId}")
    Call<UserDeletegetset> registration(@Path("EravanaId") String itemId);
}
