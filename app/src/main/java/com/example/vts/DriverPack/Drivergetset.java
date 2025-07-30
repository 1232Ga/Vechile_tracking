package com.example.vts.DriverPack;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Drivergetset {
    @SerializedName("DriverId")
    @Expose
    private String DriverId;
    @SerializedName("DriverName")
    @Expose
    private String DriverName;
    @SerializedName("PhoneNo")
    @Expose
    private String PhoneNo;
    @SerializedName("Address")
    @Expose
    private String Address;
    @SerializedName("ProofImage")
    @Expose
    private String ProofImage;
    @SerializedName("IdentityNo")
    @Expose
    private String IdentityNo;

    public String getDriverId() {
        return DriverId;
    }

    public void setDriverId(String driverId) {
        DriverId = driverId;
    }

    public String getDriverName() {
        return DriverName;
    }

    public String getProofImage() {
        return ProofImage;
    }

    public void setProofImage(String proofImage) {
        ProofImage = proofImage;
    }

    public void setDriverName(String driverName) {
        DriverName = driverName;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getIdentityNo() {
        return IdentityNo;
    }

    public void setIdentityNo(String identityNo) {
        IdentityNo = identityNo;
    }
}
