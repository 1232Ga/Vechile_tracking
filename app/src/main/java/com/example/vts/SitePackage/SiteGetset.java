package com.example.vts.SitePackage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SiteGetset {
    @SerializedName("SiteId")
    @Expose
    private String SiteId;
    @SerializedName("Name")
    @Expose
    private String Name;
    @SerializedName("Address")
    @Expose
    private String Address;
    @SerializedName("GeoLocation")
    @Expose
    private String GeoLocation;
    @SerializedName("Country")
    @Expose
    private String Country;
    @SerializedName("State")
    @Expose
    private String State;
    @SerializedName("City")
    @Expose
    private String City;
    @SerializedName("IsActive")
    @Expose
    private Boolean isActive;
    @SerializedName("IsDeleted")
    @Expose
    private Boolean isDeleted;

    public String getSiteId() {
        return SiteId;
    }

    public void setSiteId(String siteId) {
        SiteId = siteId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getGeoLocation() {
        return GeoLocation;
    }

    public void setGeoLocation(String geoLocation) {
        GeoLocation = geoLocation;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
