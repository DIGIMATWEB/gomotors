package com.gomotorscompany.gomotors.Zonas.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class dataRapartidores {

    @SerializedName("Nombre")
    @Expose
    private String nombre;
    @SerializedName("Status")
    @Expose
    private Integer status;
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("Latitud")
    @Expose
    private String latitud;
    @SerializedName("Longitud")
    @Expose
    private String longitud;

    public dataRapartidores(String nombre, Integer status, Integer id, String latitud, String longitud) {
        super();
        this.nombre = nombre;
        this.status = status;
        this.id = id;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
