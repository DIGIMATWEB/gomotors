package com.gomotorscompany.gomotors.Zonas.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class modelLocation {
    @SerializedName("location")
    @Expose
    private List<Location> location = null;
    /**
     *
     * @param location
     */
    public modelLocation(List<Location> location) {
        super();
        this.location = location;
    }

    public List<Location> getLocation() {
        return location;
    }

    public void setLocation(List<Location> location) {
        this.location = location;
    }

}

