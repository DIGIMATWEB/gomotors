package com.gomotorscompany.gomotors.Ordenar.model.newmenu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Sucursale {

    @SerializedName("codigo_sucursal")
    @Expose
    private String codigoSucursal;
    @SerializedName("nombre_suc")
    @Expose
    private String nombreSuc;
    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("long")
    @Expose
    private Double _long;


    @SerializedName("direccion")
    @Expose
    private String direccion;
    @SerializedName("paquetes")
    @Expose
    private List<Paquete> paquetes = null;
    @SerializedName("productos")
    @Expose
    private List<clasificaciones> productos = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public Sucursale() {
    }

    /**
     *
     * @param _long
     * @param paquetes
     * @param codigoSucursal
     * @param nombreSuc
     * @param productos
     * @param lat
     */
    public Sucursale(String codigoSucursal, String nombreSuc, Double lat, Double _long,String direccion, List<Paquete> paquetes, List<clasificaciones> productos) {
        super();
        this.codigoSucursal = codigoSucursal;
        this.nombreSuc = nombreSuc;
        this.lat = lat;
        this._long = _long;
        this.direccion=direccion;
        this.paquetes = paquetes;
        this.productos = productos;
    }

    public String getCodigoSucursal() {
        return codigoSucursal;
    }

    public void setCodigoSucursal(String codigoSucursal) {
        this.codigoSucursal = codigoSucursal;
    }

    public String getNombreSuc() {
        return nombreSuc;
    }

    public void setNombreSuc(String nombreSuc) {
        this.nombreSuc = nombreSuc;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLong() {
        return _long;
    }

    public void setLong(Double _long) {
        this._long = _long;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public List<Paquete> getPaquetes() {
        return paquetes;
    }

    public void setPaquetes(List<Paquete> paquetes) {
        this.paquetes = paquetes;
    }

    public List<clasificaciones> getProductos() {
        return productos;
    }

    public void setProductos(List<clasificaciones> productos) {
        this.productos = productos;
    }

}