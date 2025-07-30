package com.example.vts.Activity.CurrentSummaryReport;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryThreeFields {
    @SerializedName("dayStartAddress")
    @Expose
    private String dayStartAddress;
    @SerializedName("dayStartTime")
    @Expose
    private String dayStartTime;
    @SerializedName("currAddress")
    @Expose
    private String currAddress;
    @SerializedName("batStatus")
    @Expose
    private Object batStatus;
    @SerializedName("currSpeed")
    @Expose
    private String currSpeed;
    @SerializedName("lastTrackTime")
    @Expose
    private String lastTrackTime;
    @SerializedName("distCoverToday")
    @Expose
    private String distCoverToday;

    public String getDayStartAddress() {
        return dayStartAddress;
    }

    public void setDayStartAddress(String dayStartAddress) {
        this.dayStartAddress = dayStartAddress;
    }



    public String getCurrAddress() {
        return currAddress;
    }

    public void setCurrAddress(String currAddress) {
        this.currAddress = currAddress;
    }

    public Object getBatStatus() {
        return batStatus;
    }

    public void setBatStatus(Object batStatus) {
        this.batStatus = batStatus;
    }



    public String getLastTrackTime() {
        return lastTrackTime;
    }

    public void setLastTrackTime(String lastTrackTime) {
        this.lastTrackTime = lastTrackTime;
    }

    public String getDayStartTime() {
        return dayStartTime;
    }

    public void setDayStartTime(String dayStartTime) {
        this.dayStartTime = dayStartTime;
    }

    public String getCurrSpeed() {
        return currSpeed;
    }

    public void setCurrSpeed(String currSpeed) {
        this.currSpeed = currSpeed;
    }

    public String getDistCoverToday() {
        return distCoverToday;
    }

    public void setDistCoverToday(String distCoverToday) {
        this.distCoverToday = distCoverToday;
    }
}
