package com.example.vts.Activity.IdleReport;

import com.example.vts.Activity.DaywiseReport.DayWisegetset;
import com.example.vts.Activity.DaywiseReport.DaywiseParameter;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface IdleInterface {
    @Headers("Content-Type:application/json;charset=UTF-8")
    @POST("services/Report/getReportPagination")
    Call<Idlegetset> registration(@Body IdleParameter parameter);
}
