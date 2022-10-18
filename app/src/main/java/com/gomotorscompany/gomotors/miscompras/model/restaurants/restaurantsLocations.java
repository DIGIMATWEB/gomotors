package com.gomotorscompany.gomotors.miscompras.model.restaurants;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class restaurantsLocations implements Serializable {

    @SerializedName("latitude")
    private Double latitude;
    @SerializedName("longitud")
    private Double longitud;
    @SerializedName("Sku")
    private String sku;
    @SerializedName("Name")
    private String Name;
    @SerializedName("icon")
    private String icon;

    public restaurantsLocations(Double latitude,Double longitud,String sku,String Name, String icon)
    {
        this.latitude=latitude;
        this.longitud=longitud;
        this.sku=sku;
        this.Name=Name;
        this.icon=icon;
    }
    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
