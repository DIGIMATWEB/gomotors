package com.gomotorscompany.gomotors.Zonas.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Zona {

    @SerializedName("coloniaid")
    @Expose
    private Integer coloniaid;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("puntos")
    @Expose
    private List<Puntos> puntos = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public Zona() {
    }

    /**
     *
     * @param coloniaid
     * @param puntos
     * @param nombre
     */
    public Zona(Integer coloniaid, String nombre, List<Puntos> puntos) {
        super();
        this.coloniaid = coloniaid;
        this.nombre = nombre;
        this.puntos = puntos;
    }

    public Integer getColoniaid() {
        return coloniaid;
    }

    public void setColoniaid(Integer coloniaid) {
        this.coloniaid = coloniaid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Puntos> getPuntos() {
        return puntos;
    }

    public void setPuntos(List<Puntos> puntos) {
        this.puntos = puntos;
    }
}
