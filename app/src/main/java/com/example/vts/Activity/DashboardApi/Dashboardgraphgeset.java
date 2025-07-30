package com.example.vts.Activity.DashboardApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Dashboardgraphgeset {
    @SerializedName("Id")
    @Expose
    private String Id;
    @SerializedName("Name")
    @Expose
    private String Name;
    @SerializedName("TotalNetWeight")
    @Expose
    private String TotalNetWeight;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTotalNetWeight() {
        return TotalNetWeight;
    }

    public void setTotalNetWeight(String totalNetWeight) {
        TotalNetWeight = totalNetWeight;
    }
}
