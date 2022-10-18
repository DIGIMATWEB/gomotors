package com.gomotorscompany.gomotors.Ordenar.model.newmenu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class sucursalesn {

    @SerializedName("Codigo_Sucursal")
    @Expose
    private String codigoSucursal;
    @SerializedName("Sucursal_Nombre")
    @Expose
    private String sucursalNombre;
    @SerializedName("Id_Sucursal")
    @Expose
    private Integer idSucursal;
    @SerializedName("sucursalLat")
    @Expose
    private String sucursalLat;
    @SerializedName("sucursalLong")
    @Expose
    private String sucursalLong;
    @SerializedName("sucursalApertura")
    @Expose
    private String sucursalApertura;
    @SerializedName("sucursalCierre")
    @Expose
    private String sucursalCierre;

    @SerializedName("Clasificaciones")
    @Expose
    private List<Clasificacionen> clasificaciones = null;

    public sucursalesn(String codigoSucursal,String sucursalNombre,Integer idSucursal,String sucursalLat,String sucursalLong,String sucursalApertura,String sucursalCierre, List<Clasificacionen> clasificaciones)
    {
        this.codigoSucursal=codigoSucursal;
        this.sucursalNombre=sucursalNombre;
        this.idSucursal=idSucursal;
        this.sucursalLat=sucursalLat;
        this.sucursalLong=sucursalLong;
        this.sucursalApertura=sucursalApertura;
        this.sucursalCierre=sucursalCierre;
        this. clasificaciones=clasificaciones;

    }


    public String getCodigoSucursal() {
        return codigoSucursal;
    }

    public void setCodigoSucursal(String codigoSucursal) {
        this.codigoSucursal = codigoSucursal;
    }

    public String getSucursalNombre() {
        return sucursalNombre;
    }

    public void setSucursalNombre(String sucursalNombre) {
        this.sucursalNombre = sucursalNombre;
    }

    public Integer getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Integer idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getSucursalLat() {
        return sucursalLat;
    }

    public void setSucursalLat(String sucursalLat) {
        this.sucursalLat = sucursalLat;
    }

    public String getSucursalLong() {
        return sucursalLong;
    }

    public void setSucursalLong(String sucursalLong) {
        this.sucursalLong = sucursalLong;
    }

    public String getSucursalApertura() {
        return sucursalApertura;
    }

    public void setSucursalApertura(String sucursalApertura) {
        this.sucursalApertura = sucursalApertura;
    }

    public String getSucursalCierre() {
        return sucursalCierre;
    }

    public void setSucursalCierre(String sucursalCierre) {
        this.sucursalCierre = sucursalCierre;
    }

    public List<Clasificacionen> getClasificaciones() {
        return clasificaciones;
    }

    public void setClasificaciones(List<Clasificacionen> clasificaciones) {
        this.clasificaciones = clasificaciones;
    }

}
