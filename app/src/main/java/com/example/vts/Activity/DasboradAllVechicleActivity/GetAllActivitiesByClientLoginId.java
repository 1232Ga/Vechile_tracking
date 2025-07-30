package com.example.vts.Activity.DasboradAllVechicleActivity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAllActivitiesByClientLoginId {
    @SerializedName("uniqueId")
    @Expose
    private String uniqueId;
    @SerializedName("vehicleNumber")
    @Expose
    private String vehicleNumber;
    @SerializedName("fromTs")
    @Expose
    private String fromTs;
    @SerializedName("toTs")
    @Expose
    private String toTs;
    @SerializedName("activity_completed")
    @Expose
    private Boolean activityCompleted;
    @SerializedName("startLoc")
    @Expose
    private String startLoc;
    @SerializedName("endLoc")
    @Expose
    private String endLoc;
    @SerializedName("startAddress")
    @Expose
    private String startAddress;
    @SerializedName("endAddress")
    @Expose
    private String endAddress;
    @SerializedName("totalDist")
    @Expose
    private String totalDist;
    @SerializedName("totalRunningTime")
    @Expose
    private Integer totalRunningTime;
    @SerializedName("totalHaltTime")
    @Expose
    private Integer totalHaltTime;
    @SerializedName("totalIdlingTime")
    @Expose
    private Integer totalIdlingTime;
    @SerializedName("totalHaCount")
    @Expose
    private Integer totalHaCount;
    @SerializedName("totalHbCount")
    @Expose
    private Integer totalHbCount;
    @SerializedName("totalScore")
    @Expose
    private Integer totalScore;

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getFromTs() {
        return fromTs;
    }

    public void setFromTs(String fromTs) {
        this.fromTs = fromTs;
    }

    public String getToTs() {
        return toTs;
    }

    public void setToTs(String toTs) {
        this.toTs = toTs;
    }

    public Boolean getActivityCompleted() {
        return activityCompleted;
    }

    public void setActivityCompleted(Boolean activityCompleted) {
        this.activityCompleted = activityCompleted;
    }

    public String getStartLoc() {
        return startLoc;
    }

    public void setStartLoc(String startLoc) {
        this.startLoc = startLoc;
    }

    public String getEndLoc() {
        return endLoc;
    }

    public void setEndLoc(String endLoc) {
        this.endLoc = endLoc;
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

    public String getTotalDist() {
        return totalDist;
    }

    public void setTotalDist(String totalDist) {
        this.totalDist = totalDist;
    }

    public Integer getTotalRunningTime() {
        return totalRunningTime;
    }

    public void setTotalRunningTime(Integer totalRunningTime) {
        this.totalRunningTime = totalRunningTime;
    }

    public Integer getTotalHaltTime() {
        return totalHaltTime;
    }

    public void setTotalHaltTime(Integer totalHaltTime) {
        this.totalHaltTime = totalHaltTime;
    }

    public Integer getTotalIdlingTime() {
        return totalIdlingTime;
    }

    public void setTotalIdlingTime(Integer totalIdlingTime) {
        this.totalIdlingTime = totalIdlingTime;
    }

    public Integer getTotalHaCount() {
        return totalHaCount;
    }

    public void setTotalHaCount(Integer totalHaCount) {
        this.totalHaCount = totalHaCount;
    }

    public Integer getTotalHbCount() {
        return totalHbCount;
    }

    public void setTotalHbCount(Integer totalHbCount) {
        this.totalHbCount = totalHbCount;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

}
