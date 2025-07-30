package com.example.vts.TriplistPack;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TripListGetset {
    @SerializedName("TripId")
    @Expose
    private String tripId;
    @SerializedName("TripName")
    @Expose
    private String tripName;
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
    @SerializedName("IsActive")
    @Expose
    private Boolean isActive;
    @SerializedName("IsDeleted")
    @Expose
    private Boolean isDeleted;
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
    @SerializedName("NetWeight")
    @Expose
    private Integer netWeight;
    @SerializedName("VehicleId")
    @Expose
    private String vehicleId;
    @SerializedName("SiteId")
    @Expose
    private Object siteId;
    @SerializedName("DeviceId")
    @Expose
    private String deviceId;
    @SerializedName("OrganizationId")
    @Expose
    private String organizationId;
    @SerializedName("OrganizationName")
    @Expose
    private Object organizationName;

    public String getTripId() {
        return tripId;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
    }

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
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

    public Integer getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(Integer netWeight) {
        this.netWeight = netWeight;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Object getSiteId() {
        return siteId;
    }

    public void setSiteId(Object siteId) {
        this.siteId = siteId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
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
}
