package com.example.vts.DevicePack;

import com.example.vts.RoleListPack.RoleListgetset;
import com.example.vts.UserListPack.UserListgetset;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface DeviceInterface {

    @Headers("Content-Type:application/json;charset=UTF-8")
    @GET("services/api/DeviceManagement/listing")
    Call<List<Deveicegetset>> registration2();
}
