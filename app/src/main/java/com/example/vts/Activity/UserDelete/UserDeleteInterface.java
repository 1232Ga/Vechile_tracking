package com.example.vts.Activity.UserDelete;

import com.example.vts.Activity.RoleDetepack.DeleteRoleParam;
import com.example.vts.Activity.RoleDetepack.Deletegetset;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserDeleteInterface {
          /* @FormUrlEncoded // annotation used in POST type requests
    @POST("services/api/Auth")     // API's endpoints
    Call<SignUpResponse> registration(@Field("Email") String name,
                                      @Field("Password") String password);*/


    @Headers("Content-Type:application/json;charset=UTF-8")
    @DELETE("services/api/Users/{id}")
    Call<UserDeletegetset> registration(@Path("id") String itemId);
}
