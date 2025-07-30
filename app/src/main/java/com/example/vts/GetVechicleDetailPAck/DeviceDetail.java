package com.example.vts.GetVechicleDetailPAck;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeviceDetail {

    @SerializedName("uniqueDeviceId")
    @Expose
    private String uniqueDeviceId;
    @SerializedName("serial_num")
    @Expose
    private String serialNum;
    @SerializedName("imei_num")
    @Expose
    private String imeiNum;
    @SerializedName("ownerLoginId")
    @Expose
    private Integer ownerLoginId;

    public String getUniqueDeviceId() {
        return uniqueDeviceId;
    }

    public void setUniqueDeviceId(String uniqueDeviceId) {
        this.uniqueDeviceId = uniqueDeviceId;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public String getImeiNum() {
        return imeiNum;
    }

    public void setImeiNum(String imeiNum) {
        this.imeiNum = imeiNum;
    }

    public Integer getOwnerLoginId() {
        return ownerLoginId;
    }

    public void setOwnerLoginId(Integer ownerLoginId) {
        this.ownerLoginId = ownerLoginId;
    }

}
