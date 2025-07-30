package com.example.vts.LoginPack;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {

   /* @FormUrlEncoded // annotation used in POST type requests
    @POST("services/api/Auth")     // API's endpoints
    Call<SignUpResponse> registration(@Field("Email") String name,
                                      @Field("Password") String password);*/

    @Headers("Content-Type:application/json;charset=UTF-8")
    @POST("services/api/Auth")
    Call<Example> registration(@Body Prameter parameter);

}
