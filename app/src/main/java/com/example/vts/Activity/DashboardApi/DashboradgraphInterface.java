package com.example.vts.Activity.DashboardApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface DashboradgraphInterface {
    @Headers("Content-Type:application/json;charset=UTF-8")
    @GET("services/DashBoard/GetDispatchDetails")
    Call<List<Dashboardgraphgeset>> registration(@Query("SiteId") String SiteId);

        }
