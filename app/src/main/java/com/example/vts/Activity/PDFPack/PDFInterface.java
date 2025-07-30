package com.example.vts.Activity.PDFPack;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.vts.Activity.VehicleSummaryReport.VechicleSummaryParam;
import com.example.vts.Activity.VehicleSummaryReport.VehicleSummaryGetset;
import com.example.vts.base.CommonUtils;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface PDFInterface {

    @Headers("Content-Type:application/json;charset=UTF-8")
    @POST("services/Report/GetVehicleReportPdf")
    Call<ResponseBody> downloadFileWithDynamicUrl(@Body PdfParam parameter);
}
