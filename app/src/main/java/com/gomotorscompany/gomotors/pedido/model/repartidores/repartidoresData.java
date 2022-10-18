package com.gomotorscompany.gomotors.pedido.model.repartidores;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class repartidoresData {


    @SerializedName("id_repartidor")
    @Expose
    private Integer idRepartidor;
    @SerializedName("distancia")
    @Expose
    private Double distancia;
    @SerializedName("disponible")
    @Expose
    private Boolean disponible;
    @SerializedName("token")
    private String token;

    public repartidoresData(Integer idRepartidor, Double distancia, Boolean disponible,String token) {
        super();
        this.idRepartidor = idRepartidor;
        this.distancia = distancia;
        this.disponible = disponible;
        this.token=token;
    }

    public Integer getIdRepartidor() {
        return idRepartidor;
    }

    public void setIdRepartidor(Integer idRepartidor) {
        this.idRepartidor = idRepartidor;
    }

    public Double getDistancia() {
        return distancia;
    }

    public void setDistancia(Double distancia) {
        this.distancia = distancia;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
