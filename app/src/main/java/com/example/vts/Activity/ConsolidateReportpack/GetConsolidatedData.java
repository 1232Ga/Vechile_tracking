package com.example.vts.Activity.ConsolidateReportpack;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetConsolidatedData {
    @SerializedName("uniqueid")
    @Expose
    private String uniqueid;
    @SerializedName("startTime")
    @Expose
    private String startTime;
    @SerializedName("startLoc")
    @Expose
    private String startLoc;
    @SerializedName("endTime")
    @Expose
    private String endTime;
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
    @SerializedName("totalHaCount")
    @Expose
    private Integer totalHaCount;
    @SerializedName("totalHbCount")
    @Expose
    private Integer totalHbCount;
    @SerializedName("avgSpeed")
    @Expose
    private String avgSpeed;
    @SerializedName("totalIdlingTime")
    @Expose
    private Integer totalIdlingTime;
    @SerializedName("totalOsDist")
    @Expose
    private Integer totalOsDist;
    @SerializedName("dayStartTime")
    @Expose
    private Object dayStartTime;
    @SerializedName("dayStartLoc")
    @Expose
    private Object dayStartLoc;
    @SerializedName("dayStartAddress")
    @Expose
    private String dayStartAddress;
    @SerializedName("currLoc")
    @Expose
    private Object currLoc;
    @SerializedName("currAddress")
    @Expose
    private String currAddress;
    @SerializedName("batStatus")
    @Expose
    private Object batStatus;
    @SerializedName("currSpeed")
    @Expose
    private Object currSpeed;

    public String getUniqueid() {
        return uniqueid;
    }

    public void setUniqueid(String uniqueid) {
        this.uniqueid = uniqueid;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStartLoc() {
        return startLoc;
    }

    public void setStartLoc(String startLoc) {
        this.startLoc = startLoc;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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

    public String getAvgSpeed() {
        return avgSpeed;
    }

    public void setAvgSpeed(String avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

    public Integer getTotalIdlingTime() {
        return totalIdlingTime;
    }

    public void setTotalIdlingTime(Integer totalIdlingTime) {
        this.totalIdlingTime = totalIdlingTime;
    }

    public Integer getTotalOsDist() {
        return totalOsDist;
    }

    public void setTotalOsDist(Integer totalOsDist) {
        this.totalOsDist = totalOsDist;
    }

    public Object getDayStartTime() {
        return dayStartTime;
    }

    public void setDayStartTime(Object dayStartTime) {
        this.dayStartTime = dayStartTime;
    }

    public Object getDayStartLoc() {
        return dayStartLoc;
    }

    public void setDayStartLoc(Object dayStartLoc) {
        this.dayStartLoc = dayStartLoc;
    }

    public String getDayStartAddress() {
        return dayStartAddress;
    }

    public void setDayStartAddress(String dayStartAddress) {
        this.dayStartAddress = dayStartAddress;
    }

    public Object getCurrLoc() {
        return currLoc;
    }

    public void setCurrLoc(Object currLoc) {
        this.currLoc = currLoc;
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

    public Object getCurrSpeed() {
        return currSpeed;
    }

    public void setCurrSpeed(Object currSpeed) {
        this.currSpeed = currSpeed;
    }
}
