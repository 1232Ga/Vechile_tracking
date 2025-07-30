package com.example.vts.Passwordchangepack;

import com.example.vts.LoginPack.Example;
import com.example.vts.LoginPack.Prameter;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ChangepasswordInterface {

    @Headers("Content-Type:application/json;charset=UTF-8")
    @POST("services/api/Users/Change_Password")
    Call<ChangePasswordgetset> registration(@Body PasswordParam parameter);
}
