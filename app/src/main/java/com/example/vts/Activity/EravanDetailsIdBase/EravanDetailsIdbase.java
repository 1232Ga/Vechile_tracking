package com.example.vts.Activity.EravanDetailsIdBase;

import com.example.vts.Activity.EravanaIdPAck.Eravanaidgetset;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface EravanDetailsIdbase {
    @Headers("Content-Type:application/json;charset=UTF-8")
    @GET("services/api/EravanaInfo/{Id}")
    Call<EravanIdgetSet> registration(@Path("Id") String itemId);
}
