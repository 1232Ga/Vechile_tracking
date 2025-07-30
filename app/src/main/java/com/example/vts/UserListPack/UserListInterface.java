package com.example.vts.UserListPack;

import com.example.vts.RoleListPack.RoleListgetset;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface UserListInterface {
    @Headers("Content-Type:application/json;charset=UTF-8")
    @GET("services/api/Users/listing")
    Call<UserListgetset> registration2();

}
