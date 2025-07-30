package com.example.vts.Activity.KmReport;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetKmSummary {

    @SerializedName("uniqueId")
    @Expose
    private String uniqueId;
    @SerializedName("vehicleNumber")
    @Expose
    private String vehicleNumber;
    @SerializedName("totaldist")
    @Expose
    private String totaldist;
    @SerializedName("noOfPackets")
    @Expose
    private Integer noOfPackets;
    @SerializedName("noOfDays")
    @Expose
    private Integer noOfDays;
    @SerializedName("month")
    @Expose
    private String month;

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getTotaldist() {
        return totaldist;
    }

    public void setTotaldist(String totaldist) {
        this.totaldist = totaldist;
    }

    public Integer getNoOfPackets() {
        return noOfPackets;
    }

    public void setNoOfPackets(Integer noOfPackets) {
        this.noOfPackets = noOfPackets;
    }

    public Integer getNoOfDays() {
        return noOfDays;
    }

    public void setNoOfDays(Integer noOfDays) {
        this.noOfDays = noOfDays;
    }



    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
