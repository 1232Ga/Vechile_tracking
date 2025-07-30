package com.example.vts.LiveTracking.DeviceLocation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetDeviceLatestLocation {
    @SerializedName("vehicleNumber")
    @Expose
    private String vehicleNumber;
    @SerializedName("vehicleType")
    @Expose
    private String vehicleType;
    @SerializedName("vehicleModel")
    @Expose
    private String vehicleModel;
    @SerializedName("uniqueId")
    @Expose
    private String uniqueId;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("gpsStatus")
    @Expose
    private String gpsStatus;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("isOverspeed")
    @Expose
    private Boolean isOverspeed;
    @SerializedName("isHA")
    @Expose
    private Boolean isHA;
    @SerializedName("isHB")
    @Expose
    private Boolean isHB;
    @SerializedName("speed")
    @Expose
    private Double speed;
    @SerializedName("idlingStatus")
    @Expose
    private Boolean idlingStatus;
    @SerializedName("haltStatus")
    @Expose
    private Boolean haltStatus;
    @SerializedName("isNoGps")
    @Expose
    private Boolean isNoGps;

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getGpsStatus() {
        return gpsStatus;
    }

    public void setGpsStatus(String gpsStatus) {
        this.gpsStatus = gpsStatus;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Boolean getIsOverspeed() {
        return isOverspeed;
    }

    public void setIsOverspeed(Boolean isOverspeed) {
        this.isOverspeed = isOverspeed;
    }

    public Boolean getIsHA() {
        return isHA;
    }

    public void setIsHA(Boolean isHA) {
        this.isHA = isHA;
    }

    public Boolean getIsHB() {
        return isHB;
    }

    public void setIsHB(Boolean isHB) {
        this.isHB = isHB;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Boolean getIdlingStatus() {
        return idlingStatus;
    }

    public void setIdlingStatus(Boolean idlingStatus) {
        this.idlingStatus = idlingStatus;
    }

    public Boolean getHaltStatus() {
        return haltStatus;
    }

    public void setHaltStatus(Boolean haltStatus) {
        this.haltStatus = haltStatus;
    }

    public Boolean getIsNoGps() {
        return isNoGps;
    }

    public void setIsNoGps(Boolean isNoGps) {
        this.isNoGps = isNoGps;
    }

}
