package com.example.vts.Activity.DashboardApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultData {

    @SerializedName("Idlingcount")
    @Expose
    private Integer idlingcount;
    @SerializedName("OverSpeedCount")
    @Expose
    private Integer overSpeedCount;
    @SerializedName("ViolationCount")
    @Expose
    private Integer violationCount;
    @SerializedName("TotalCount")
    @Expose
    private Integer totalCount;
    @SerializedName("RunningCount")
    @Expose
    private Integer runningCount;
    @SerializedName("CompletedCount")
    @Expose
    private Integer completedCount;
    @SerializedName("Haltcount")
    @Expose
    private Integer haltcount;
    @SerializedName("NoGPS")
    @Expose
    private Integer noGPS;
    @SerializedName("OffLine")
    @Expose
    private Integer offLine;
    @SerializedName("DelayCount")
    @Expose
    private Integer delayCount;
    @SerializedName("ScheduledCount")
    @Expose
    private Integer scheduledCount;
    @SerializedName("RunnungVehicleManagementlst")
    @Expose
    private List<RunnungVehicleManagementlst> runnungVehicleManagementlst = null;
    @SerializedName("VehicleManagementItems")
    @Expose
    private List<VehicleManagementItem> vehicleManagementItems = null;
    @SerializedName("SiteItem")
    @Expose
    private List<SiteItem> siteItem = null;

    public Integer getIdlingcount() {
        return idlingcount;
    }

    public void setIdlingcount(Integer idlingcount) {
        this.idlingcount = idlingcount;
    }

    public Integer getOverSpeedCount() {
        return overSpeedCount;
    }

    public void setOverSpeedCount(Integer overSpeedCount) {
        this.overSpeedCount = overSpeedCount;
    }

    public Integer getViolationCount() {
        return violationCount;
    }

    public void setViolationCount(Integer violationCount) {
        this.violationCount = violationCount;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getRunningCount() {
        return runningCount;
    }

    public void setRunningCount(Integer runningCount) {
        this.runningCount = runningCount;
    }

    public Integer getCompletedCount() {
        return completedCount;
    }

    public void setCompletedCount(Integer completedCount) {
        this.completedCount = completedCount;
    }

    public Integer getHaltcount() {
        return haltcount;
    }

    public void setHaltcount(Integer haltcount) {
        this.haltcount = haltcount;
    }

    public Integer getNoGPS() {
        return noGPS;
    }

    public void setNoGPS(Integer noGPS) {
        this.noGPS = noGPS;
    }

    public Integer getOffLine() {
        return offLine;
    }

    public void setOffLine(Integer offLine) {
        this.offLine = offLine;
    }

    public Integer getDelayCount() {
        return delayCount;
    }

    public void setDelayCount(Integer delayCount) {
        this.delayCount = delayCount;
    }

    public Integer getScheduledCount() {
        return scheduledCount;
    }

    public void setScheduledCount(Integer scheduledCount) {
        this.scheduledCount = scheduledCount;
    }

    public List<RunnungVehicleManagementlst> getRunnungVehicleManagementlst() {
        return runnungVehicleManagementlst;
    }

    public void setRunnungVehicleManagementlst(List<RunnungVehicleManagementlst> runnungVehicleManagementlst) {
        this.runnungVehicleManagementlst = runnungVehicleManagementlst;
    }

    public List<VehicleManagementItem> getVehicleManagementItems() {
        return vehicleManagementItems;
    }

    public void setVehicleManagementItems(List<VehicleManagementItem> vehicleManagementItems) {
        this.vehicleManagementItems = vehicleManagementItems;
    }

    public List<SiteItem> getSiteItem() {
        return siteItem;
    }

    public void setSiteItem(List<SiteItem> siteItem) {
        this.siteItem = siteItem;
    }

}
