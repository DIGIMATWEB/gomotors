package com.gomotorscompany.gomotors.Zonas.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class requestUpdateZones {
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("zonas")
    @Expose
    private List<Zona> zonas = null;
    public requestUpdateZones(String token, List<Zona> zonas) {
        super();
        this.token = token;
        this.zonas = zonas;
    }
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<Zona> getZonas() {
        return zonas;
    }

    public void setZonas(List<Zona> zonas) {
        this.zonas = zonas;
    }

}
