package com.example.vts.GetVechicleDetailPAck;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultData {
    @SerializedName("getAllVehicleDetailsAPI")
    @Expose
    private List<GetAllVehicleDetailsAPus> getAllVehicleDetailsAPI = null;

    public List<GetAllVehicleDetailsAPus> getGetAllVehicleDetailsAPI() {
        return getAllVehicleDetailsAPI;
    }

    public void setGetAllVehicleDetailsAPI(List<GetAllVehicleDetailsAPus> getAllVehicleDetailsAPI) {
        this.getAllVehicleDetailsAPI = getAllVehicleDetailsAPI;
    }
}
