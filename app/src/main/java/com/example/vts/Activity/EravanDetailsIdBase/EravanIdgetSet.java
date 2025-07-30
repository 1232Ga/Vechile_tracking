package com.example.vts.Activity.EravanDetailsIdBase;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EravanIdgetSet {

    @SerializedName("EravanaId")
    @Expose
    private String eravanaId;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("EravanaNo")
    @Expose
    private String eravanaNo;
    @SerializedName("VehicleStatus")
    @Expose
    private String vehicleStatus;
    @SerializedName("GeoLocation")
    @Expose
    private String geoLocation;
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
    private Integer avgSpeed;
    @SerializedName("DistanceCovered")
    @Expose
    private Integer distanceCovered;
    @SerializedName("TripTime")
    @Expose
    private String tripTime;
    @SerializedName("LastUpdatedOn")
    @Expose
    private String lastUpdatedOn;
    @SerializedName("LastUpdatedAt")
    @Expose
    private String lastUpdatedAt;
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
    private String deviceNo;
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
    private String EravanaEndDate;

    @SerializedName("Mineral")
    @Expose
    private Object mineral;
    @SerializedName("Royalty_Amt")
    @Expose
    private String royaltyAmt;
    @SerializedName("GrossDate")
    @Expose
    private String grossDate;
    @SerializedName("GrossWeight")
    @Expose
    private String grossWeight;
    @SerializedName("TareDate")
    @Expose
    private String tareDate;
    @SerializedName("Status")
    @Expose
    private String Status;
    @SerializedName("TareWeight")
    @Expose
    private String tareWeight;
    @SerializedName("NetWeight")
    @Expose
    private String netWeight;

    @SerializedName("haltcount")
    @Expose
    private Integer haltcount;
    @SerializedName("idlingcount")
    @Expose
    private Integer idlingcount;
    @SerializedName("VehicleManagementItems")
    @Expose
    private Object vehicleManagementItems;

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

    public String getEravanaNo() {
        return eravanaNo;
    }

    public String getsGeoLocation() {
        return sGeoLocation;
    }

    public void setsGeoLocation(String sGeoLocation) {
        this.sGeoLocation = sGeoLocation;
    }

    public String getdGeoLocation() {
        return dGeoLocation;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public void setdGeoLocation(String dGeoLocation) {
        this.dGeoLocation = dGeoLocation;
    }

    public String getEravanaEndDate() {
        return EravanaEndDate;
    }

    public void setEravanaEndDate(String eravanaEndDate) {
        EravanaEndDate = eravanaEndDate;
    }

    public void setEravanaNo(String eravanaNo) {
        this.eravanaNo = eravanaNo;
    }

    public String getVehicleStatus() {
        return vehicleStatus;
    }

    public void setVehicleStatus(String vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }

    public String getGeoLocation() {
        return geoLocation;
    }

    public void setGeoLocation(String geoLocation) {
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

    public Integer getAvgSpeed() {
        return avgSpeed;
    }

    public void setAvgSpeed(Integer avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

    public Integer getDistanceCovered() {
        return distanceCovered;
    }

    public void setDistanceCovered(Integer distanceCovered) {
        this.distanceCovered = distanceCovered;
    }

    public String getTripTime() {
        return tripTime;
    }

    public void setTripTime(String tripTime) {
        this.tripTime = tripTime;
    }

    public String getLastUpdatedOn() {
        return lastUpdatedOn;
    }

    public void setLastUpdatedOn(String lastUpdatedOn) {
        this.lastUpdatedOn = lastUpdatedOn;
    }

    public String getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(String lastUpdatedAt) {
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

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
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

    public Object getMineral() {
        return mineral;
    }

    public void setMineral(Object mineral) {
        this.mineral = mineral;
    }

    public String getRoyaltyAmt() {
        return royaltyAmt;
    }

    public void setRoyaltyAmt(String royaltyAmt) {
        this.royaltyAmt = royaltyAmt;
    }

    public String getGrossDate() {
        return grossDate;
    }

    public void setGrossDate(String grossDate) {
        this.grossDate = grossDate;
    }

    public String getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(String grossWeight) {
        this.grossWeight = grossWeight;
    }

    public String getTareDate() {
        return tareDate;
    }

    public void setTareDate(String tareDate) {
        this.tareDate = tareDate;
    }

    public String getTareWeight() {
        return tareWeight;
    }

    public void setTareWeight(String tareWeight) {
        this.tareWeight = tareWeight;
    }

    public String getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(String netWeight) {
        this.netWeight = netWeight;
    }

    public Integer getHaltcount() {
        return haltcount;
    }

    public void setHaltcount(Integer haltcount) {
        this.haltcount = haltcount;
    }

    public Integer getIdlingcount() {
        return idlingcount;
    }

    public void setIdlingcount(Integer idlingcount) {
        this.idlingcount = idlingcount;
    }

    public Object getVehicleManagementItems() {
        return vehicleManagementItems;
    }

    public void setVehicleManagementItems(Object vehicleManagementItems) {
        this.vehicleManagementItems = vehicleManagementItems;
    }
}
