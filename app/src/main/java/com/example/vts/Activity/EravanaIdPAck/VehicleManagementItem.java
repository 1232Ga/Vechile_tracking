package com.example.vts.Activity.EravanaIdPAck;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VehicleManagementItem {
    @SerializedName("VehicleId")
    @Expose
    private String vehicleId;
    @SerializedName("VehicleName")
    @Expose
    private String vehicleName;
    @SerializedName("VehicleNo")
    @Expose
    private String vehicleNo;
    @SerializedName("vehicleType")
    @Expose
    private String vehicleType;
    @SerializedName("vehicleModel")
    @Expose
    private String vehicleModel;
    @SerializedName("RFId")
    @Expose
    private String rFId;
    @SerializedName("IsActive")
    @Expose
    private Boolean isActive;
    @SerializedName("IsDeleted")
    @Expose
    private Boolean isDeleted;
    @SerializedName("DeviceId")
    @Expose
    private String deviceId;
    @SerializedName("OrganizationId")
    @Expose
    private String organizationId;
    @SerializedName("OrganizationName")
    @Expose
    private Object organizationName;
    @SerializedName("VehicleOwnerId")
    @Expose
    private String vehicleOwnerId;
    @SerializedName("VehicleOwnerName")
    @Expose
    private Object vehicleOwnerName;
    @SerializedName("DriverId")
    @Expose
    private String driverId;
    @SerializedName("DriverName")
    @Expose
    private String driverName;
    @SerializedName("DateCreated")
    @Expose
    private String dateCreated;
    @SerializedName("DateModified")
    @Expose
    private String dateModified;
    @SerializedName("idlingStatus")
    @Expose
    private Boolean idlingStatus;
    @SerializedName("haltStatus")
    @Expose
    private Boolean haltStatus;
    @SerializedName("Speed")
    @Expose
    private Integer speed;
    @SerializedName("AvgSpeed")
    @Expose
    private Integer avgSpeed;
    @SerializedName("IsOverSpeed")
    @Expose
    private Object isOverSpeed;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("gpsStatus")
    @Expose
    private Object gpsStatus;
    @SerializedName("SourceName")
    @Expose
    private String sourceName;
    @SerializedName("DestinationName")
    @Expose
    private String destinationName;


    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
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

    public String getRFId() {
        return rFId;
    }

    public void setRFId(String rFId) {
        this.rFId = rFId;
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

    public String getVehicleOwnerId() {
        return vehicleOwnerId;
    }

    public void setVehicleOwnerId(String vehicleOwnerId) {
        this.vehicleOwnerId = vehicleOwnerId;
    }

    public Object getVehicleOwnerName() {
        return vehicleOwnerName;
    }

    public void setVehicleOwnerName(Object vehicleOwnerName) {
        this.vehicleOwnerName = vehicleOwnerName;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public Object getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateModified() {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
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

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getAvgSpeed() {
        return avgSpeed;
    }

    public void setAvgSpeed(Integer avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

    public Object getIsOverSpeed() {
        return isOverSpeed;
    }

    public void setIsOverSpeed(Object isOverSpeed) {
        this.isOverSpeed = isOverSpeed;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Object getGpsStatus() {
        return gpsStatus;
    }

    public void setGpsStatus(Object gpsStatus) {
        this.gpsStatus = gpsStatus;
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









}
