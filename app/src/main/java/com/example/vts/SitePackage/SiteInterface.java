package com.example.vts.SitePackage;

import com.example.vts.RoleListPack.RoleListgetset;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface SiteInterface {
    @Headers("Content-Type:application/json;charset=UTF-8")
    @GET("services/api/Site/listing")
    Call<List<SiteGetset>> registration2();
}
