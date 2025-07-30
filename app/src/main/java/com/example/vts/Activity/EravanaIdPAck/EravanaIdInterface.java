package com.example.vts.Activity.EravanaIdPAck;

import com.example.vts.Activity.UserDelete.UserDeletegetset;
import com.example.vts.TriplistPack.TripParameter;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface EravanaIdInterface {
//    @Headers("Content-Type:application/json;charset=UTF-8")
//    @GET("vts-services/api/EravanaInfo/TripInfo")
//    Call<List<Eravanaidgetset>>registration(@Query("SiteId") String siteid);
@Headers("Content-Type:application/json;charset=UTF-8")
@POST("services/api/EravanaInfo/GetTrips")
Call<List<Eravanaidgetset>> registration(@Body TripParameter parameter);
}
