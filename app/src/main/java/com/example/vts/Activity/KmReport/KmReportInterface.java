package com.example.vts.Activity.KmReport;

import com.example.vts.Activity.DashboardApi.Dashbaordgetset;
import com.example.vts.Activity.DashboardApi.DashboardParamter;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface KmReportInterface {
    @Headers("Content-Type:application/json;charset=UTF-8")
    @POST("services/api/LiveDataDetails/Report/getKmSummary")
    Call<Kmgetset> registration(@Body KmParamter parameter);
}
