package com.example.vts.Activity.Addsitr;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface EditSiteInterface {
    @Headers("Content-Type:application/json;charset=UTF-8")
    @POST("services/api/Site")
    Call<AddSitegetset> registration2(@Body EditParameter roleParameter);
}
