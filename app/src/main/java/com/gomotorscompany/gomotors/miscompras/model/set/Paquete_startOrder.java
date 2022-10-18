package com.gomotorscompany.gomotors.miscompras.model.set;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Paquete_startOrder {

    @SerializedName("id_paquetes")
    @Expose
    private Integer idPaquetes;
    @SerializedName("cantidad")
    @Expose
    private Integer cantidad;
    @SerializedName("status")
    @Expose
    private Boolean status;

    public Paquete_startOrder(Integer idPaquetes, Integer cantidad, Boolean status) {
        super();
        this.idPaquetes = idPaquetes;
        this.cantidad = cantidad;
        this.status = status;
    }

    public Integer getIdPaquetes() {
        return idPaquetes;
    }

    public void setIdPaquetes(Integer idPaquetes) {
        this.idPaquetes = idPaquetes;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}