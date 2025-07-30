package com.example.vts.GetVechicleDetailPAck;

import com.example.vts.RoleListPack.RoleListgetset;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface GetAllVechicleInterface {
    @Headers("Content-Type:application/json;charset=UTF-8")
    @GET("services/api/LiveDataDetails/getAllVehicleDetailsAPI")
    Call<GetAllVechilegetset> registration2();
}
