package com.example.vts.UserRole;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UserRoleInterface {
    @Headers("Content-Type:application/json;charset=UTF-8")
    @POST("services/api/Users/SaveUser")
    Call<Usergetset> registration3(@Body UserParameter roleParameter);
}
