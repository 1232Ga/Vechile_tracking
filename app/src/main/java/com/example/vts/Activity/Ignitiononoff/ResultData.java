package com.example.vts.Activity.Ignitiononoff;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultData {
    @SerializedName("getReportPagination")
    @Expose
    private GetReportPagination getReportPagination;

    public GetReportPagination getGetReportPagination() {
        return getReportPagination;
    }

    public void setGetReportPagination(GetReportPagination getReportPagination) {
        this.getReportPagination = getReportPagination;
    }
}
