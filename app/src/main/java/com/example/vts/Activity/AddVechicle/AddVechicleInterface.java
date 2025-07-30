package com.example.vts.Activity.AddVechicle;

import com.example.vts.Activity.Addsitr.AddSiteParamter;
import com.example.vts.Activity.Addsitr.AddSitegetset;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface AddVechicleInterface {
    @Headers("Content-Type:application/json;charset=UTF-8")
    @POST("services/api/VehicleManagement")
    Call<AddVechiclegetset> registration2(@Body AddvechicleParameter roleParameter);
}
