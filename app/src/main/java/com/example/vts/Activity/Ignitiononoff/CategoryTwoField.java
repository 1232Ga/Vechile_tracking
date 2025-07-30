package com.example.vts.Activity.Ignitiononoff;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryTwoField {
    @SerializedName("groupName")
    @Expose
    private Object groupName;
    @SerializedName("startTime")
    @Expose
    private String startTime;
    @SerializedName("endTime")
    @Expose
    private String endTime;
    @SerializedName("startLoc")
    @Expose
    private String startLoc;
    @SerializedName("startAddress")
    @Expose
    private String startAddress;
    @SerializedName("endAddress")
    @Expose
    private String endAddress;
    @SerializedName("endLoc")
    @Expose
    private String endLoc;
    @SerializedName("totalDist")
    @Expose
    private String totalDist;
    @SerializedName("avgSpeed")
    @Expose
    private String avgSpeed;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("alertDur")
    @Expose
    private Object alertDur;
    @SerializedName("alertLoc")
    @Expose
    private Object alertLoc;
    @SerializedName("alertAddress")
    @Expose
    private String alertAddress;
    @SerializedName("totalOsDist")
    @Expose
    private Object totalOsDist;
    @SerializedName("maxSpeed")
    @Expose
    private String maxSpeed;
    @SerializedName("uniqueid")
    @Expose
    private String uniqueid;
    @SerializedName("totalRunningTime")
    @Expose
    private Integer totalRunningTime;

    public Object getGroupName() {
        return groupName;
    }

    public void setGroupName(Object groupName) {
        this.groupName = groupName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartLoc() {
        return startLoc;
    }

    public void setStartLoc(String startLoc) {
        this.startLoc = startLoc;
    }

    public String getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(String startAddress) {
        this.startAddress = startAddress;
    }

    public String getEndAddress() {
        return endAddress;
    }

    public void setEndAddress(String endAddress) {
        this.endAddress = endAddress;
    }

    public String getEndLoc() {
        return endLoc;
    }

    public void setEndLoc(String endLoc) {
        this.endLoc = endLoc;
    }



    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Object getAlertDur() {
        return alertDur;
    }

    public void setAlertDur(Object alertDur) {
        this.alertDur = alertDur;
    }

    public Object getAlertLoc() {
        return alertLoc;
    }

    public void setAlertLoc(Object alertLoc) {
        this.alertLoc = alertLoc;
    }

    public String getAlertAddress() {
        return alertAddress;
    }

    public void setAlertAddress(String alertAddress) {
        this.alertAddress = alertAddress;
    }

    public Object getTotalOsDist() {
        return totalOsDist;
    }

    public void setTotalOsDist(Object totalOsDist) {
        this.totalOsDist = totalOsDist;
    }

    public String getTotalDist() {
        return totalDist;
    }

    public void setTotalDist(String totalDist) {
        this.totalDist = totalDist;
    }

    public String getAvgSpeed() {
        return avgSpeed;
    }

    public void setAvgSpeed(String avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

    public String getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(String maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public String getUniqueid() {
        return uniqueid;
    }

    public void setUniqueid(String uniqueid) {
        this.uniqueid = uniqueid;
    }

    public Integer getTotalRunningTime() {
        return totalRunningTime;
    }

    public void setTotalRunningTime(Integer totalRunningTime) {
        this.totalRunningTime = totalRunningTime;
    }
}
