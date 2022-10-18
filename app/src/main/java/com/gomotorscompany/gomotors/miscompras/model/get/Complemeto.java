package com.gomotorscompany.gomotors.miscompras.model.get;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Complemeto implements Serializable {
    @SerializedName("clave")
    @Expose
    private String clave;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("descripcion")
    @Expose
    private String descripcion;
    @SerializedName("precio")
    @Expose
    private String precio;
    @SerializedName("cantidad")
    @Expose
    private Integer cantidad;
    @SerializedName("estatus")
    @Expose
    private Boolean estatus;
    @SerializedName("imabegn")
    @Expose
    private String imabegn;

    /**
     * No args constructor for use in serialization
     *
     */
    public Complemeto() {
    }

    /**
     *
     * @param descripcion
     * @param clave
     * @param precio
     * @param estatus
     * @param imabegn
     * @param cantidad
     * @param nombre
     */
    public Complemeto(String clave, String nombre, String descripcion, String precio, Integer cantidad, Boolean estatus, String imabegn) {
        super();
        this.clave = clave;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
        this.estatus = estatus;
        this.imabegn = imabegn;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Boolean getEstatus() {
        return estatus;
    }

    public void setEstatus(Boolean estatus) {
        this.estatus = estatus;
    }

    public String getImabegn() {
        return imabegn;
    }

    public void setImabegn(String imabegn) {
        this.imabegn = imabegn;
    }

}