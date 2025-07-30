package com.example.vts.Activity.VehicleSummaryReport;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VehicleSummaryGetset {

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
    private String totalRunningTime;
    @SerializedName("totalHaltTime")
    @Expose
    private String totalHaltTime;
    @SerializedName("totalHaCount")
    @Expose
    private String totalHaCount;
    @SerializedName("totalHbCount")
    @Expose
    private String totalHbCount;
    @SerializedName("avgSpeed")
    @Expose
    private String avgSpeed;
    @SerializedName("totalIdlingTime")
    @Expose
    private String totalIdlingTime;
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
    @SerializedName("totalIdlingTimeInHrs")
    @Expose
    private Double totalIdlingTimeInHrs;
    @SerializedName("totalHaltTimeInHrs")
    @Expose
    private Double totalHaltTimeInHrs;
    @SerializedName("totalRunningInHrs")
    @Expose
    private Double totalRunningInHrs;
    @SerializedName("startTimeDate")
    @Expose
    private String startTimeDate;
    @SerializedName("endTimeDate")
    @Expose
    private String endTimeDate;

    @SerializedName("vehicleDetail")
    @Expose
    private VehicleDetail vehicleDetail;

    public VehicleDetail getVehicleDetail() {
        return vehicleDetail;
    }

    public void setVehicleDetail(VehicleDetail vehicleDetail) {
        this.vehicleDetail = vehicleDetail;
    }
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

    public String getTotalRunningTime() {
        return totalRunningTime;
    }

    public void setTotalRunningTime(String totalRunningTime) {
        this.totalRunningTime = totalRunningTime;
    }

    public String getTotalHaltTime() {
        return totalHaltTime;
    }

    public void setTotalHaltTime(String totalHaltTime) {
        this.totalHaltTime = totalHaltTime;
    }

    public String getTotalHaCount() {
        return totalHaCount;
    }

    public void setTotalHaCount(String totalHaCount) {
        this.totalHaCount = totalHaCount;
    }

    public String getTotalHbCount() {
        return totalHbCount;
    }

    public void setTotalHbCount(String totalHbCount) {
        this.totalHbCount = totalHbCount;
    }

    public String getAvgSpeed() {
        return avgSpeed;
    }

    public void setAvgSpeed(String avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

    public String getTotalIdlingTime() {
        return totalIdlingTime;
    }

    public void setTotalIdlingTime(String totalIdlingTime) {
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

    public Double getTotalIdlingTimeInHrs() {
        return totalIdlingTimeInHrs;
    }

    public void setTotalIdlingTimeInHrs(Double totalIdlingTimeInHrs) {
        this.totalIdlingTimeInHrs = totalIdlingTimeInHrs;
    }

    public Double getTotalHaltTimeInHrs() {
        return totalHaltTimeInHrs;
    }

    public void setTotalHaltTimeInHrs(Double totalHaltTimeInHrs) {
        this.totalHaltTimeInHrs = totalHaltTimeInHrs;
    }

    public Double getTotalRunningInHrs() {
        return totalRunningInHrs;
    }

    public void setTotalRunningInHrs(Double totalRunningInHrs) {
        this.totalRunningInHrs = totalRunningInHrs;
    }

    public String getStartTimeDate() {
        return startTimeDate;
    }

    public void setStartTimeDate(String startTimeDate) {
        this.startTimeDate = startTimeDate;
    }

    public String getEndTimeDate() {
        return endTimeDate;
    }

    public void setEndTimeDate(String endTimeDate) {
        this.endTimeDate = endTimeDate;
    }
}
