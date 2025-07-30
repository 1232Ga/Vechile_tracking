package com.example.vts.RoleListPack;

import com.example.vts.RolePack.RoleParameter;
import com.example.vts.RolePack.Rolegetset;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RolelistInterface {
    @Headers("Content-Type:application/json;charset=UTF-8")
    @GET("services/api/UserRoles/listing")
    Call<List<RoleListgetset>> registration2();
}
