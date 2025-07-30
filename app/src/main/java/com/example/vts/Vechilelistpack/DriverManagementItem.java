package com.example.vts.Vechilelistpack;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DriverManagementItem {

    @SerializedName("DriverId")
    @Expose
    private String driverId;
    @SerializedName("DriverName")
    @Expose
    private String driverName;
    @SerializedName("PhoneNo")
    @Expose
    private String phoneNo;
    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("IdentityNo")
    @Expose
    private String identityNo;
    @SerializedName("OrganizationId")
    @Expose
    private String organizationId;
    @SerializedName("Notes")
    @Expose
    private Object notes;
    @SerializedName("IsActive")
    @Expose
    private Boolean isActive;
    @SerializedName("IsDeleted")
    @Expose
    private Boolean isDeleted;
    @SerializedName("DateCreated")
    @Expose
    private String dateCreated;
    @SerializedName("DateModified")
    @Expose
    private String dateModified;

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdentityNo() {
        return identityNo;
    }

    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public Object getNotes() {
        return notes;
    }

    public void setNotes(Object notes) {
        this.notes = notes;
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
}
