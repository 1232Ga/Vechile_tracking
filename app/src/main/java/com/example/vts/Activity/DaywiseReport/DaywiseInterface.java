package com.example.vts.Activity.DaywiseReport;

import com.example.vts.Activity.TrackingRepor.TrackingParameter;
import com.example.vts.Activity.TrackingRepor.Trackinggetset;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface DaywiseInterface {
    @Headers("Content-Type:application/json;charset=UTF-8")
    @POST("services/Report/getReportPagination")
    Call<DayWisegetset> registration(@Body DaywiseParameter parameter);
}
