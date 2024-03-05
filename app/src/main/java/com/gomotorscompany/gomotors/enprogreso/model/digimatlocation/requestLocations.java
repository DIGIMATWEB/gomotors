package com.gomotorscompany.gomotors.enprogreso.model.digimatlocation;

import com.google.gson.annotations.SerializedName;

public class requestLocations {

    @SerializedName("serialNumber")
    private String serialNumber;
    @SerializedName("lat")
    private String lat;
    @SerializedName("long")
    private String _long;
    public requestLocations(String serialNumber, String lat, String _long) {
        super();
        this.serialNumber = serialNumber;
        this.lat = lat;
        this._long = _long;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLong() {
        return _long;
    }

    public void setLong(String _long) {
        this._long = _long;
    }
}
