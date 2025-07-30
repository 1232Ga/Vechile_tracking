package com.example.vts.GetVechicleDetailPAck;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SimDetail {
    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("simNumber")
    @Expose
    private String simNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSimNumber() {
        return simNumber;
    }

    public void setSimNumber(String simNumber) {
        this.simNumber = simNumber;
    }
}
