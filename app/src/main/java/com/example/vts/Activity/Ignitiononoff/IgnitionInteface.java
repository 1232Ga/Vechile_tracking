package com.example.vts.Activity.Ignitiononoff;

import com.example.vts.Activity.IdleReport.IdleParameter;
import com.example.vts.Activity.IdleReport.Idlegetset;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface IgnitionInteface {
    @Headers("Content-Type:application/json;charset=UTF-8")
    @POST("services/Report/getReportPagination")
    Call<Ignitiongetset> registration(@Body IgnitionParameter parameter);
}
