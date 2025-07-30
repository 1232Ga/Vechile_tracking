package com.example.vts.Activity.AddDevice;

import com.example.vts.Activity.Addsitr.AddSiteParamter;
import com.example.vts.Activity.Addsitr.AddSitegetset;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface AddDeviceInterface {
    @Headers("Content-Type:application/json;charset=UTF-8")
    @POST("services/api/DeviceManagement")
    Call<AddDevicegetset> registration2(@Body AddDeviceParameter roleParameter);
}
