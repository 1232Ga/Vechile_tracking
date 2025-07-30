package com.example.vts.VechoeownerPack;

import com.example.vts.DriverPack.Drivergetset;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface VechicleInterface {
    @Headers("Content-Type:application/json;charset=UTF-8")
    @GET("services/api/VehicleOwnerManagement/listing")
    Call<List<Vechicleownergetset>> registration2();
}
