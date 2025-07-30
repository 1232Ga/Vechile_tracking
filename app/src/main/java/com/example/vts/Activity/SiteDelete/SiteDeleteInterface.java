package com.example.vts.Activity.SiteDelete;

import com.example.vts.Activity.UserDelete.UserDeletegetset;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface SiteDeleteInterface {
    @Headers("Content-Type:application/json;charset=UTF-8")
    @DELETE("services/api/Site/{SiteId}")
    Call<UserDeletegetset> registration(@Path("SiteId") String itemId);
}
