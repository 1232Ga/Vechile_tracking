package com.example.vts.Activity.HaltrepoertActivity;

import com.example.vts.Activity.IdleReport.IdleParameter;
import com.example.vts.Activity.IdleReport.Idlegetset;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface HaltInterface {
    @Headers("Content-Type:application/json;charset=UTF-8")
    @POST("services/Report/getReportPagination")
    Call<Haltgetset> registration(@Body HaltParameter parameter);
}
