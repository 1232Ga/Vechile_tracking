package com.example.vts.LiveTracking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultData {

    @SerializedName("generateAuthTokenAPI")
    @Expose
    private GenerateAuthTokenAPI generateAuthTokenAPI;

    public GenerateAuthTokenAPI getGenerateAuthTokenAPI() {
        return generateAuthTokenAPI;
    }

    public void setGenerateAuthTokenAPI(GenerateAuthTokenAPI generateAuthTokenAPI) {
        this.generateAuthTokenAPI = generateAuthTokenAPI;
    }
}
