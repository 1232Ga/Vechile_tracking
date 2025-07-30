package com.example.vts.TriplistPack;

import com.example.vts.Activity.EravanaIdPAck.Eravanaidgetset;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;


public interface AlltripList {
    @Headers("Content-Type:application/json;charset=UTF-8")
    @GET("services/api/EravanaInfo/listing")
    Call<List<Eravanaidgetset>> registration();
}
