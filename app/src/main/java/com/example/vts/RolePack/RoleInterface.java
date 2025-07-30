package com.example.vts.RolePack;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RoleInterface {
    @Headers("Content-Type:application/json;charset=UTF-8")
    @POST("services/api/UserRoles")
    Call<Rolegetset> registration2(@Body RoleParameter roleParameter);
}
