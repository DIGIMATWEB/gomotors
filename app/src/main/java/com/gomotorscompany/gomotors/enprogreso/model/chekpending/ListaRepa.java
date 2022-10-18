package com.gomotorscompany.gomotors.enprogreso.model.chekpending;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListaRepa {


    @SerializedName("id_repartidor")
    @Expose
    private Integer idRepartidor;
    @SerializedName("distancia")
    @Expose
    private Float distancia;
    @SerializedName("disponible")
    @Expose
    private Boolean disponible;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("long")
    @Expose
    private String _long;
    @SerializedName("token")
    @Expose
    private String token;


    public ListaRepa(Integer idRepartidor, Float distancia, Boolean disponible, String lat, String _long, String token) {
        super();
        this.idRepartidor = idRepartidor;
        this.distancia = distancia;
        this.disponible = disponible;
        this.lat = lat;
        this._long = _long;
        this.token = token;
    }

    public Integer getIdRepartidor() {
        return idRepartidor;
    }

    public void setIdRepartidor(Integer idRepartidor) {
        this.idRepartidor = idRepartidor;
    }

    public Float getDistancia() {
        return distancia;
    }

    public void setDistancia(Float distancia) {
        this.distancia = distancia;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
