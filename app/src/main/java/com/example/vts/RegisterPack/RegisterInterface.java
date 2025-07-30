package com.example.vts.RegisterPack;

import com.example.vts.LoginPack.Example;
import com.example.vts.LoginPack.Prameter;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RegisterInterface {
    @Headers("Content-Type:application/json;charset=UTF-8")
    @POST("services/api/Organization")
    Call<Registergetset> registration1(@Body RegiterParamter regiterParamter);
}
