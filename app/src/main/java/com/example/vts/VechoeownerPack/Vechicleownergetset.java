package com.example.vts.VechoeownerPack;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Vechicleownergetset {
    @SerializedName("VehicleOwnerId")
    @Expose
    private String VehicleOwnerId;
    @SerializedName("TransportName")
    @Expose
    private String TransportName;
    @SerializedName("OwnerName")
    @Expose
    private String OwnerName;
    @SerializedName("Mobile")
    @Expose
    private String Mobile;
    @SerializedName("Address")
    @Expose
    private String Address;
    @SerializedName("Email")
    @Expose
    private String Email;
    @SerializedName("TotalNoOfVehicle")
    @Expose
    private String TotalNoOfVehicle;
    @SerializedName("ProfileImagePath")
    @Expose
    private String ProfileImagePath;

    public String getProfileImagePath() {
        return ProfileImagePath;
    }

    public void setProfileImagePath(String profileImagePath) {
        ProfileImagePath = profileImagePath;
    }

    public String getVehicleOwnerId() {
        return VehicleOwnerId;
    }

    public void setVehicleOwnerId(String vehicleOwnerId) {
        VehicleOwnerId = vehicleOwnerId;
    }

    public String getTransportName() {
        return TransportName;
    }

    public void setTransportName(String transportName) {
        TransportName = transportName;
    }

    public String getOwnerName() {
        return OwnerName;
    }

    public void setOwnerName(String ownerName) {
        OwnerName = ownerName;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getTotalNoOfVehicle() {
        return TotalNoOfVehicle;
    }

    public void setTotalNoOfVehicle(String totalNoOfVehicle) {
        TotalNoOfVehicle = totalNoOfVehicle;
    }
}
