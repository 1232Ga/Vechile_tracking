package com.example.vts.Activity.ConsolidateReportpack;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultData {
    @SerializedName("getConsolidatedData")
    @Expose
    private GetConsolidatedData getConsolidatedData;

    public GetConsolidatedData getGetConsolidatedData() {
        return getConsolidatedData;
    }

    public void setGetConsolidatedData(GetConsolidatedData getConsolidatedData) {
        this.getConsolidatedData = getConsolidatedData;
    }
}
