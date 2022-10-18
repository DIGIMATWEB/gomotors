package com.gomotorscompany.gomotors.Zonas.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Location {
    @SerializedName("Latitud")
    @Expose
    private String latitud;
    @SerializedName("Longitud")
    @Expose
    private String longitud;

    /**
     *
     * @param latitud
     * @param longitud
     */
    public Location(String latitud, String longitud) {
        super();
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }
}
