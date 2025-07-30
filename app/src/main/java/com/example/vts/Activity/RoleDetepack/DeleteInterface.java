package com.example.vts.Activity.RoleDetepack;

import com.example.vts.LoginPack.Example;
import com.example.vts.LoginPack.Prameter;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface DeleteInterface {
    @Headers("Content-Type:application/json;charset=UTF-8")
    @DELETE("services/api/UserRoles/{UserRolesId}")
    Call<Deletegetset> registration(@Path("UserRolesId") String itemId);
}
