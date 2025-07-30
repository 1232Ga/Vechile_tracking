package com.example.vts.Activity.ForgetpasswordPack;

import com.example.vts.Activity.RoleDetepack.DeleteRoleParam;
import com.example.vts.Activity.RoleDetepack.Deletegetset;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ForgetInterface {
    @Headers("Content-Type:application/json;charset=UTF-8")
    @POST("services/api/forgetpassword")
    Call<Forgetgetset> registration(@Body Forgetparam parameter);
}
