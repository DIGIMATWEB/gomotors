package com.gomotorscompany.gomotors.Ordenar.model.newmenu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class requesMenun {



    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("modo")
    @Expose
    private Integer modo;
    @SerializedName("lat_susario")
    @Expose
    private Double latSusario;
    @SerializedName("long_usuario")
    @Expose
    private Double longUsuario;


    public requesMenun(String token, Integer modo, Double latSusario, Double longUsuario) {
        super();
        this.token = token;
        this.modo = modo;
        this.latSusario = latSusario;
        this.longUsuario = longUsuario;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getModo() {
        return modo;
    }

    public void setModo(Integer modo) {
        this.modo = modo;
    }

    public Double getLatSusario() {
        return latSusario;
    }

    public void setLatSusario(Double latSusario) {
        this.latSusario = latSusario;
    }

    public Double getLongUsuario() {
        return longUsuario;
    }

    public void setLongUsuario(Double longUsuario) {
        this.longUsuario = longUsuario;
    }
}
