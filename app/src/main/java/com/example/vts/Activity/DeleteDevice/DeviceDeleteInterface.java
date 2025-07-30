package com.example.vts.Activity.DeleteDevice;

import com.example.vts.Activity.UserDelete.UserDeletegetset;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface DeviceDeleteInterface {
    @Headers("Content-Type:application/json;charset=UTF-8")
    @DELETE("services/api/DeviceManagement/{DeviceId}")
    Call<UserDeletegetset> registration(@Path("DeviceId") String itemId);
}
