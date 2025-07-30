package com.example.vts.Activity.KmReport;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultData {

    @SerializedName("getKmSummary")
    @Expose
    private List<GetKmSummary> getKmSummary = null;

    public List<GetKmSummary> getGetKmSummary() {
        return getKmSummary;
    }

    public void setGetKmSummary(List<GetKmSummary> getKmSummary) {
        this.getKmSummary = getKmSummary;
    }
}
