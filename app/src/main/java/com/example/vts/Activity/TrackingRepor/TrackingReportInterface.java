package com.example.vts.Activity.TrackingRepor;

import com.example.vts.Activity.KmReport.KmParamter;
import com.example.vts.Activity.KmReport.Kmgetset;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface TrackingReportInterface {
    @Headers("Content-Type:application/json;charset=UTF-8")
    @POST("services/Report/getReportPagination")
    Call<Trackinggetset> registration(@Body TrackingParameter parameter);
}
