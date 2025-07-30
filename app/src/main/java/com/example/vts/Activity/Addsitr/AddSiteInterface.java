package com.example.vts.Activity.Addsitr;

import com.example.vts.RolePack.RoleParameter;
import com.example.vts.RolePack.Rolegetset;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface AddSiteInterface {
    @Headers("Content-Type:application/json;charset=UTF-8")
    @POST("services/api/Site")
    Call<AddSitegetset> registration2(@Body AddSiteParamter roleParameter);
}
