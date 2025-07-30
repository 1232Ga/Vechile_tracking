package com.example.vts.LiveTracking.DeviceLocation;

import com.example.vts.LiveTracking.GetAuthGetSet;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface DeviceLatestInterface {
    @Headers("Content-Type:application/json;charset=UTF-8")
    @GET("services/api/LiveDataDetails/deviceLiveTracking")
    Call<Livedevicegetset> registration2(@Query("deviceId") String deviceid);
}
