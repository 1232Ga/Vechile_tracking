package com.example.vts.DevicePack;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Deveicegetset {

    @SerializedName("DeviceId")
    @Expose
    private String DeviceId;
    @SerializedName("DeviceName")
    @Expose
    private String DeviceName;
    @SerializedName("OrganizationId")
    @Expose
    private String organizationId;
    @SerializedName("Notes")
    @Expose
    private String Notes;

    public String getDeviceId() {
        return DeviceId;
    }

    public void setDeviceId(String deviceId) {
        DeviceId = deviceId;
    }

    public String getDeviceName() {
        return DeviceName;
    }

    public void setDeviceName(String deviceName) {
        DeviceName = deviceName;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }
}
