package com.example.vts.Activity.AddDriver;

import com.example.vts.Activity.AddDevice.AddDeviceParameter;
import com.example.vts.Activity.AddDevice.AddDevicegetset;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface AddDriverInterface {
    @Headers("Content-Type:application/json;charset=UTF-8")
    @POST("services/api/DriverManagement/Save")
    Call<AddDevicegetset> registration2(@Query("DriverManagementItem") AddDriverParameter roleParameter,
                                        @Part MultipartBody.Part image);
}
