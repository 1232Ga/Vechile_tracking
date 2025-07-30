package com.example.vts.SitePackage;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;


public interface Sitedeteilainterface {
    @Headers("Content-Type:application/json;charset=UTF-8")
    @GET("services/api/Site/{id}")
    Call<Sitedetailgetset> registration2(@Path("id") String itemId);
}
