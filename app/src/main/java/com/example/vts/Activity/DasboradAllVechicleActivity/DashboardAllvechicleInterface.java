package com.example.vts.Activity.DasboradAllVechicleActivity;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DashboardAllvechicleInterface {
    @Headers("Content-Type:application/json;charset=UTF-8")
    @GET("services/api/LiveDataDetails/Dashboard/getAllActivitiesByClientLoginId")
    Call<DashbaordAllgetset> registration(@Query("clientLoginId") int clientLoginId, @Query("fromTs") String fromTs, @Query("toTs") String toTs);


//    @GET("vts-services/api/LiveDataDetails/Dashboard/getAllActivitiesByClientLoginId?clientLoginId={clientLoginId}&fromTs={fromTs}&toTs={toTs}")
//    Call<DashbaordAllgetset> registration(@Path("clientLoginId")int clientLoginId,@Path("fromTs")String fromTs,@Path("toTs")String toTs);
}
