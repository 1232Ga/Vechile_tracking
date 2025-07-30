package com.example.vts.Activity.VehicleSummaryReport;

import com.example.vts.Activity.PDFPack.PdfParam;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface TripSummaryReport {
    @Headers("Content-Type:application/json;charset=UTF-8")
    @POST("services/Report/GetTripReport")
    Call<List<TripSummarygetset>> registration(@Body PdfParam parameter);
}
