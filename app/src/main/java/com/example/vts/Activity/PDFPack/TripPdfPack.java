package com.example.vts.Activity.PDFPack;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface TripPdfPack {
    @Headers("Content-Type:application/json;charset=UTF-8")
    @POST("services/Report/GetTripReportPdf")
    Call<ResponseBody> downloadFileWithDynamicUrl(@Body PdfParam parameter);
}
