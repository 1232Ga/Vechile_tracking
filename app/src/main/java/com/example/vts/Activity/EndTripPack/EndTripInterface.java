package com.example.vts.Activity.EndTripPack;
import com.example.vts.Activity.UserDelete.UserDeletegetset;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface EndTripInterface {
    @Headers("Content-Type:application/json;charset=UTF-8")
    @GET("services/api/EravanaInfo/MarkTripComplete")
    Call<TripEndgetset> registration(@Query("EravanaId") String EravanaId,@Query("flag") String flag);
}
