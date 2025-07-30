package com.example.vts.Activity.VehicleSummaryReport;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EravanaInfoDetail {

    @SerializedName("EravanaId")
    @Expose
    private String eravanaId;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("EravanaNo")
    @Expose
    private Object eravanaNo;
    @SerializedName("VehicleStatus")
    @Expose
    private String vehicleStatus;
    @SerializedName("GeoLocation")
    @Expose
    private Object geoLocation;
    @SerializedName("SourceName")
    @Expose
    private String sourceName;
    @SerializedName("DestinationName")
    @Expose
    private String destinationName;
    @SerializedName("S_GeoLocation")
    @Expose
    private String sGeoLocation;
    @SerializedName("D_GeoLocation")
    @Expose
    private String dGeoLocation;
    @SerializedName("AvgSpeed")
    @Expose
    private String avgSpeed;
    @SerializedName("DistanceCovered")
    @Expose
    private String distanceCovered;
    @SerializedName("TripTime")
    @Expose
    private Object tripTime;
    @SerializedName("LastUpdatedOn")
    @Expose
    private String lastUpdatedOn;
    @SerializedName("LastUpdatedAt")
    @Expose
    private Object lastUpdatedAt;
    @SerializedName("IsActive")
    @Expose
    private Boolean isActive;
    @SerializedName("IsDeleted")
    @Expose
    private Boolean isDeleted;
    @SerializedName("VehicleId")
    @Expose
    private String vehicleId;
    @SerializedName("VehicleNo")
    @Expose
    private String vehicleNo;
    @SerializedName("SiteId")
    @Expose
    private String siteId;
    @SerializedName("DeviceId")
    @Expose
    private String deviceId;
    @SerializedName("DeviceNo")
    @Expose
    private Object deviceNo;
    @SerializedName("OrganizationId")
    @Expose
    private String organizationId;
    @SerializedName("OrganizationName")
    @Expose
    private Object organizationName;
    @SerializedName("EravanaDate")
    @Expose
    private String eravanaDate;
    @SerializedName("EravanaEndDate")
    @Expose
    private String eravanaEndDate;
    @SerializedName("Mineral")
    @Expose
    private Object mineral;
    @SerializedName("Royalty_Amt")
    @Expose
    private Object royaltyAmt;
    @SerializedName("GrossDate")
    @Expose
    private String grossDate;
    @SerializedName("GrossWeight")
    @Expose
    private Integer grossWeight;
    @SerializedName("TareDate")
    @Expose
    private String tareDate;
    @SerializedName("TareWeight")
    @Expose
    private Integer tareWeight;
    @SerializedName("idlingcount")
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
    @SerializedName("haltcount")
    @Expose
    private Integer haltcount;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("NetWeight")
    @Expose
    private String netWeight;
    @SerializedName("RunnungVehicleManagementlst")
    @Expose
    private Object runnungVehicleManagementlst;
    @SerializedName("VehicleManagementItems")
    @Expose
    private Object vehicleManagementItems;
    @SerializedName("VehicleManagementItem")
    @Expose
    private Object vehicleManagementItem;
    @SerializedName("SiteItem")
    @Expose
    private Object siteItem;
    @SerializedName("DeviceManagement")
    @Expose
    private Object deviceManagement;
    @SerializedName("IsCompleted")
    @Expose
    private Boolean isCompleted;
    @SerializedName("IsSuccess")
    @Expose
    private Boolean isSuccess;
    @SerializedName("ErrorMessage")
    @Expose
    private Object errorMessage;
    @SerializedName("OperationData")
    @Expose
    private Object operationData;
    @SerializedName("SuccessMessage")
    @Expose
    private Object successMessage;
    @SerializedName("ID")
    @Expose
    private Object id;
    @SerializedName("ResultData")
    @Expose
    private Object resultData;
    @SerializedName("ReturnString")
    @Expose
    private Object returnString;

    public String getEravanaId() {
        return eravanaId;
    }

    public void setEravanaId(String eravanaId) {
        this.eravanaId = eravanaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getEravanaNo() {
        return eravanaNo;
    }

    public void setEravanaNo(Object eravanaNo) {
        this.eravanaNo = eravanaNo;
    }

    public String getVehicleStatus() {
        return vehicleStatus;
    }

    public void setVehicleStatus(String vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }

    public Object getGeoLocation() {
        return geoLocation;
    }

    public void setGeoLocation(Object geoLocation) {
        this.geoLocation = geoLocation;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public String getSGeoLocation() {
        return sGeoLocation;
    }

    public void setSGeoLocation(String sGeoLocation) {
        this.sGeoLocation = sGeoLocation;
    }

    public String getDGeoLocation() {
        return dGeoLocation;
    }

    public void setDGeoLocation(String dGeoLocation) {
        this.dGeoLocation = dGeoLocation;
    }

    public String getAvgSpeed() {
        return avgSpeed;
    }

    public void setAvgSpeed(String avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

    public String getDistanceCovered() {
        return distanceCovered;
    }

    public void setDistanceCovered(String distanceCovered) {
        this.distanceCovered = distanceCovered;
    }

    public Object getTripTime() {
        return tripTime;
    }

    public void setTripTime(Object tripTime) {
        this.tripTime = tripTime;
    }

    public String getLastUpdatedOn() {
        return lastUpdatedOn;
    }

    public void setLastUpdatedOn(String lastUpdatedOn) {
        this.lastUpdatedOn = lastUpdatedOn;
    }

    public Object getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(Object lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Object getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(Object deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public Object getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(Object organizationName) {
        this.organizationName = organizationName;
    }

    public String getEravanaDate() {
        return eravanaDate;
    }

    public void setEravanaDate(String eravanaDate) {
        this.eravanaDate = eravanaDate;
    }

    public String getEravanaEndDate() {
        return eravanaEndDate;
    }

    public void setEravanaEndDate(String eravanaEndDate) {
        this.eravanaEndDate = eravanaEndDate;
    }

    public Object getMineral() {
        return mineral;
    }

    public void setMineral(Object mineral) {
        this.mineral = mineral;
    }

    public Object getRoyaltyAmt() {
        return royaltyAmt;
    }

    public void setRoyaltyAmt(Object royaltyAmt) {
        this.royaltyAmt = royaltyAmt;
    }

    public String getGrossDate() {
        return grossDate;
    }

    public void setGrossDate(String grossDate) {
        this.grossDate = grossDate;
    }

    public Integer getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(Integer grossWeight) {
        this.grossWeight = grossWeight;
    }

    public String getTareDate() {
        return tareDate;
    }

    public void setTareDate(String tareDate) {
        this.tareDate = tareDate;
    }

    public Integer getTareWeight() {
        return tareWeight;
    }

    public void setTareWeight(Integer tareWeight) {
        this.tareWeight = tareWeight;
    }

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

    public Integer getHaltcount() {
        return haltcount;
    }

    public void setHaltcount(Integer haltcount) {
        this.haltcount = haltcount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(String netWeight) {
        this.netWeight = netWeight;
    }

    public Object getRunnungVehicleManagementlst() {
        return runnungVehicleManagementlst;
    }

    public void setRunnungVehicleManagementlst(Object runnungVehicleManagementlst) {
        this.runnungVehicleManagementlst = runnungVehicleManagementlst;
    }

    public Object getVehicleManagementItems() {
        return vehicleManagementItems;
    }

    public void setVehicleManagementItems(Object vehicleManagementItems) {
        this.vehicleManagementItems = vehicleManagementItems;
    }

    public Object getVehicleManagementItem() {
        return vehicleManagementItem;
    }

    public void setVehicleManagementItem(Object vehicleManagementItem) {
        this.vehicleManagementItem = vehicleManagementItem;
    }

    public Object getSiteItem() {
        return siteItem;
    }

    public void setSiteItem(Object siteItem) {
        this.siteItem = siteItem;
    }

    public Object getDeviceManagement() {
        return deviceManagement;
    }

    public void setDeviceManagement(Object deviceManagement) {
        this.deviceManagement = deviceManagement;
    }

    public Boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public Object getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(Object errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Object getOperationData() {
        return operationData;
    }

    public void setOperationData(Object operationData) {
        this.operationData = operationData;
    }

    public Object getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(Object successMessage) {
        this.successMessage = successMessage;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Object getResultData() {
        return resultData;
    }

    public void setResultData(Object resultData) {
        this.resultData = resultData;
    }

    public Object getReturnString() {
        return returnString;
    }

    public void setReturnString(Object returnString) {
        this.returnString = returnString;
    }
}
