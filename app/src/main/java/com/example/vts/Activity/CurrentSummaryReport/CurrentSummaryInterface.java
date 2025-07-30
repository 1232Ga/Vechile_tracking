package com.example.vts.Activity.CurrentSummaryReport;

import com.example.vts.Activity.DaywiseReport.DayWisegetset;
import com.example.vts.Activity.DaywiseReport.DaywiseParameter;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface CurrentSummaryInterface {
    @Headers("Content-Type:application/json;charset=UTF-8")
    @POST("services/Report/getReportPagination")
    Call<CurrentSummarygetset> registration(@Body CurrentSummayParamter parameter);
}
