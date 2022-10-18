package com.gomotorscompany.gomotors.enprogreso.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class setNewlocationDriver {

    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("lon")
    @Expose
    private String lon;

    public setNewlocationDriver(String token, String lat, String lon) {
        super();
        this.token = token;
        this.lat = lat;
        this.lon = lon;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

}