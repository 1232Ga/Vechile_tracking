package com.example.vts.TriplistPack;

import com.example.vts.Activity.EravanaIdPAck.Eravanaidgetset;
import com.example.vts.LoginPack.Example;
import com.example.vts.LoginPack.Prameter;
import com.example.vts.RoleListPack.RoleListgetset;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TripListInterface {
//    @Headers("Content-Type:application/json;charset=UTF-8")
//    @GET("vts-services/api/EravanaInfo/TripInfo")
//    Call<List<Eravanaidgetset>> registration2(@Query("SiteId") String siteid);

    @Headers("Content-Type:application/json;charset=UTF-8")
    @POST("services/api/EravanaInfo/GetTrips")
    Call<List<Eravanaidgetset>> registration(@Body TripParameter parameter);
}
