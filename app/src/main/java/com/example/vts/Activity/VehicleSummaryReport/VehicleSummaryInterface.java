package com.example.vts.Activity.VehicleSummaryReport;

import com.example.vts.LoginPack.Example;
import com.example.vts.LoginPack.Prameter;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface VehicleSummaryInterface {
    @Headers("Content-Type:application/json;charset=UTF-8")
    @POST("services/Report/GetVehicleReport")
    Call<List<VehicleSummaryGetset>> registration(@Body VechicleSummaryParam parameter);
}
