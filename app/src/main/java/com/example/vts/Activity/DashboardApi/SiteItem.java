package com.example.vts.Activity.DashboardApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SiteItem {
    @SerializedName("SiteId")
    @Expose
    private String siteId;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("GeoLocation")
    @Expose
    private String geoLocation;
    @SerializedName("Country")
    @Expose
    private String country;
    @SerializedName("State")
    @Expose
    private String state;
    @SerializedName("City")
    @Expose
    private String city;
    @SerializedName("District")
    @Expose
    private String district;
    @SerializedName("PinCode")
    @Expose
    private String pinCode;
    @SerializedName("Notes")
    @Expose
    private Object notes;
    @SerializedName("IsActive")
    @Expose
    private Boolean isActive;
    @SerializedName("IsDeleted")
    @Expose
    private Boolean isDeleted;
    @SerializedName("IsAdmin")
    @Expose
    private Boolean isAdmin;
    @SerializedName("UserId")
    @Expose
    private String userId;
    @SerializedName("UserName")
    @Expose
    private Object userName;
    @SerializedName("OrganizationId")
    @Expose
    private String organizationId;
    @SerializedName("OrganizationName")
    @Expose
    private Object organizationName;
    @SerializedName("DateCreated")
    @Expose
    private String dateCreated;
    @SerializedName("DateModified")
    @Expose
    private String dateModified;
    @SerializedName("Payload")
    @Expose
    private Object payload;
    @SerializedName("TotalNetDispatched")
    @Expose
    private Double totalNetDispatched;

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGeoLocation() {
        return geoLocation;
    }

    public void setGeoLocation(String geoLocation) {
        this.geoLocation = geoLocation;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
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

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Object getUserName() {
        return userName;
    }

    public void setUserName(Object userName) {
        this.userName = userName;
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

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }

    public Double getTotalNetDispatched() {
        return totalNetDispatched;
    }

    public void setTotalNetDispatched(Double totalNetDispatched) {
        this.totalNetDispatched = totalNetDispatched;
    }

}
