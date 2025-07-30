package com.example.vts.Activity.TrackingRepor;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryOneField {

    @SerializedName("uniqueid")
    @Expose
    private String uniqueid;
    @SerializedName("dateTime")
    @Expose
    private String dateTime;
    @SerializedName("location")
    @Expose
    private Object location;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("cumDist")
    @Expose
    private Double cumDist;
    @SerializedName("obddistance")
    @Expose
    private Object obddistance;
    @SerializedName("speed")
    @Expose
    private Integer speed;
    @SerializedName("vehiclespeed")
    @Expose
    private Object vehiclespeed;
    @SerializedName("coolant")
    @Expose
    private Object coolant;
    @SerializedName("engine")
    @Expose
    private Object engine;
    @SerializedName("milege")
    @Expose
    private Object milege;
    @SerializedName("batStatus")
    @Expose
    private String batStatus;
    @SerializedName("ignitionstatus")
    @Expose
    private String ignitionstatus;

    public String getUniqueid() {
        return uniqueid;
    }

    public void setUniqueid(String uniqueid) {
        this.uniqueid = uniqueid;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Object getLocation() {
        return location;
    }

    public void setLocation(Object location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getCumDist() {
        return cumDist;
    }

    public void setCumDist(Double cumDist) {
        this.cumDist = cumDist;
    }

    public Object getObddistance() {
        return obddistance;
    }

    public void setObddistance(Object obddistance) {
        this.obddistance = obddistance;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Object getVehiclespeed() {
        return vehiclespeed;
    }

    public void setVehiclespeed(Object vehiclespeed) {
        this.vehiclespeed = vehiclespeed;
    }

    public Object getCoolant() {
        return coolant;
    }

    public void setCoolant(Object coolant) {
        this.coolant = coolant;
    }

    public Object getEngine() {
        return engine;
    }

    public void setEngine(Object engine) {
        this.engine = engine;
    }

    public Object getMilege() {
        return milege;
    }

    public void setMilege(Object milege) {
        this.milege = milege;
    }

    public String getBatStatus() {
        return batStatus;
    }

    public void setBatStatus(String batStatus) {
        this.batStatus = batStatus;
    }

    public String getIgnitionstatus() {
        return ignitionstatus;
    }

    public void setIgnitionstatus(String ignitionstatus) {
        this.ignitionstatus = ignitionstatus;
    }

}
