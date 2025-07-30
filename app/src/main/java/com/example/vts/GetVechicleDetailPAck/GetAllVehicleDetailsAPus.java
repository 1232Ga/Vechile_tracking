package com.example.vts.GetVechicleDetailPAck;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAllVehicleDetailsAPus {
    @SerializedName("entityId")
    @Expose
    private Integer entityId;
    @SerializedName("vehicleNumber")
    @Expose
    private String vehicleNumber;
    @SerializedName("engineNumber")
    @Expose
    private Object engineNumber;
    @SerializedName("chassisNumber")
    @Expose
    private Object chassisNumber;
    @SerializedName("vehicleGroupAssign")
    @Expose
    private Object vehicleGroupAssign;
    @SerializedName("speedSensorType")
    @Expose
    private String speedSensorType;
    @SerializedName("speedLimit")
    @Expose
    private Integer speedLimit;
    @SerializedName("vehicleModel")
    @Expose
    private String vehicleModel;
    @SerializedName("vehicleType")
    @Expose
    private String vehicleType;
    @SerializedName("vehicleCapacity")
    @Expose
    private Integer vehicleCapacity;
    @SerializedName("deviceDetail")
    @Expose
    private DeviceDetail deviceDetail;
    @SerializedName("SimDetail")
    @Expose
    private SimDetail simDetail;

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public Object getEngineNumber() {
        return engineNumber;
    }

    public void setEngineNumber(Object engineNumber) {
        this.engineNumber = engineNumber;
    }

    public Object getChassisNumber() {
        return chassisNumber;
    }

    public void setChassisNumber(Object chassisNumber) {
        this.chassisNumber = chassisNumber;
    }

    public Object getVehicleGroupAssign() {
        return vehicleGroupAssign;
    }

    public void setVehicleGroupAssign(Object vehicleGroupAssign) {
        this.vehicleGroupAssign = vehicleGroupAssign;
    }

    public String getSpeedSensorType() {
        return speedSensorType;
    }

    public void setSpeedSensorType(String speedSensorType) {
        this.speedSensorType = speedSensorType;
    }

    public Integer getSpeedLimit() {
        return speedLimit;
    }

    public void setSpeedLimit(Integer speedLimit) {
        this.speedLimit = speedLimit;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Integer getVehicleCapacity() {
        return vehicleCapacity;
    }

    public void setVehicleCapacity(Integer vehicleCapacity) {
        this.vehicleCapacity = vehicleCapacity;
    }

    public DeviceDetail getDeviceDetail() {
        return deviceDetail;
    }

    public void setDeviceDetail(DeviceDetail deviceDetail) {
        this.deviceDetail = deviceDetail;
    }

    public SimDetail getSimDetail() {
        return simDetail;
    }

    public void setSimDetail(SimDetail simDetail) {
        this.simDetail = simDetail;
    }



        }
