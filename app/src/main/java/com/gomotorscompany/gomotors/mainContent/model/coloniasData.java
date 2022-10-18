package com.gomotorscompany.gomotors.mainContent.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class coloniasData {

    @SerializedName("Nombre")
    @Expose
    private String nombre;
    @SerializedName("Latitud")
    @Expose
    private String latitud;
    @SerializedName("Longitud")
    @Expose
    private String longitud;

    public coloniasData(String nombre,String latitud,String longitud)
    {
        this.nombre=nombre;
        this.latitud=latitud;
        this.longitud=longitud;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
