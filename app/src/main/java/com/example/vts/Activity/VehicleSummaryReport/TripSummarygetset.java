package com.example.vts.Activity.VehicleSummaryReport;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TripSummarygetset {
    @SerializedName("uniqueid")
    @Expose
    private String uniqueid;
    @SerializedName("startTime")
    @Expose
    private Object startTime;
    @SerializedName("startLoc")
    @Expose
    private Object startLoc;
    @SerializedName("endTime")
    @Expose
    private Object endTime;
    @SerializedName("endLoc")
    @Expose
    private Object endLoc;
    @SerializedName("startAddress")
    @Expose
    private String startAddress;
    @SerializedName("endAddress")
    @Expose
    private String endAddress;
    @SerializedName("totalDist")
    @Expose
    private Integer totalDist;
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
    @SerializedName("totalIdlingTimeInHrs")
    @Expose
    private Integer totalIdlingTimeInHrs;
    @SerializedName("totalHaltTimeInHrs")
    @Expose
    private Integer totalHaltTimeInHrs;
    @SerializedName("totalRunningInHrs")
    @Expose
    private Integer totalRunningInHrs;
    @SerializedName("startTimeDate")
    @Expose
    private String startTimeDate;
    @SerializedName("endTimeDate")
    @Expose
    private String endTimeDate;
    @SerializedName("vehicleNo")
    @Expose
    private String vehicleNo;
    @SerializedName("driveName")
    @Expose
    private String driveName;
    @SerializedName("tripname")
    @Expose
    private String tripname;
    @SerializedName("startdate")
    @Expose
    private String startdate;
    @SerializedName("enddate")
    @Expose
    private String enddate;
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("destination")
    @Expose
    private String destination;
    @SerializedName("netWeight")
    @Expose
    private Integer netWeight;
    @SerializedName("vehicleDetail")
    @Expose
    private VehicleDetail vehicleDetail;
    @SerializedName("eravanaInfoDetail")
    @Expose
    private EravanaInfoDetail eravanaInfoDetail;

    public String getUniqueid() {
        return uniqueid;
    }

    public void setUniqueid(String uniqueid) {
        this.uniqueid = uniqueid;
    }

    public Object getStartTime() {
        return startTime;
    }

    public void setStartTime(Object startTime) {
        this.startTime = startTime;
    }

    public Object getStartLoc() {
        return startLoc;
    }

    public void setStartLoc(Object startLoc) {
        this.startLoc = startLoc;
    }

    public Object getEndTime() {
        return endTime;
    }

    public void setEndTime(Object endTime) {
        this.endTime = endTime;
    }

    public Object getEndLoc() {
        return endLoc;
    }

    public void setEndLoc(Object endLoc) {
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

    public Integer getTotalDist() {
        return totalDist;
    }

    public void setTotalDist(Integer totalDist) {
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

    public Integer getTotalIdlingTimeInHrs() {
        return totalIdlingTimeInHrs;
    }

    public void setTotalIdlingTimeInHrs(Integer totalIdlingTimeInHrs) {
        this.totalIdlingTimeInHrs = totalIdlingTimeInHrs;
    }

    public Integer getTotalHaltTimeInHrs() {
        return totalHaltTimeInHrs;
    }

    public void setTotalHaltTimeInHrs(Integer totalHaltTimeInHrs) {
        this.totalHaltTimeInHrs = totalHaltTimeInHrs;
    }

    public Integer getTotalRunningInHrs() {
        return totalRunningInHrs;
    }

    public void setTotalRunningInHrs(Integer totalRunningInHrs) {
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

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getDriveName() {
        return driveName;
    }

    public void setDriveName(String driveName) {
        this.driveName = driveName;
    }

    public String getTripname() {
        return tripname;
    }

    public void setTripname(String tripname) {
        this.tripname = tripname;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Integer getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(Integer netWeight) {
        this.netWeight = netWeight;
    }

    public VehicleDetail getVehicleDetail() {
        return vehicleDetail;
    }

    public void setVehicleDetail(VehicleDetail vehicleDetail) {
        this.vehicleDetail = vehicleDetail;
    }

    public EravanaInfoDetail getEravanaInfoDetail() {
        return eravanaInfoDetail;
    }

    public void setEravanaInfoDetail(EravanaInfoDetail eravanaInfoDetail) {
        this.eravanaInfoDetail = eravanaInfoDetail;
    }
}
