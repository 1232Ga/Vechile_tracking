package com.example.vts.Activity.EndTripPack;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sitenamegetse {
    @SerializedName("Name")
    @Expose
    private String Name;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
