package com.example.vts.Activity.DasboradAllVechicleActivity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultData {
    @SerializedName("getAllActivitiesByClientLoginId")
    @Expose
    private List<GetAllActivitiesByClientLoginId> getAllActivitiesByClientLoginId = null;

    public List<GetAllActivitiesByClientLoginId> getGetAllActivitiesByClientLoginId() {
        return getAllActivitiesByClientLoginId;
    }

    public void setGetAllActivitiesByClientLoginId(List<GetAllActivitiesByClientLoginId> getAllActivitiesByClientLoginId) {
        this.getAllActivitiesByClientLoginId = getAllActivitiesByClientLoginId;
    }

}
