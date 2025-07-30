package com.example.vts.LiveTracking.DeviceLocation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultData {

    @SerializedName("getDeviceLatestLocation")
    @Expose
    private GetDeviceLatestLocation getDeviceLatestLocation;

    public GetDeviceLatestLocation getGetDeviceLatestLocation() {
        return getDeviceLatestLocation;
    }
    public void setGetDeviceLatestLocation(GetDeviceLatestLocation getDeviceLatestLocation) {
        this.getDeviceLatestLocation = getDeviceLatestLocation;
    }
}
