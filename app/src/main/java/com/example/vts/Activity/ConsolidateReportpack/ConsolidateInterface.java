package com.example.vts.Activity.ConsolidateReportpack;

import com.example.vts.Activity.KmReport.KmParamter;
import com.example.vts.Activity.KmReport.Kmgetset;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ConsolidateInterface {
    @Headers("Content-Type:application/json;charset=UTF-8")
    @POST("services/api/LiveDataDetails/Report/getConsolidatedData")
    Call<ConsolidateReportgetset> registration(@Body ConsolidateParamter parameter);
}
