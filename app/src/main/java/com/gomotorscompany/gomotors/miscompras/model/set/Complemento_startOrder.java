package com.gomotorscompany.gomotors.miscompras.model.set;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Complemento_startOrder {
    @SerializedName("sku_complemento")
    @Expose
    private String skuComplemento;
    @SerializedName("cantidad")
    @Expose
    private Integer cantidad;
    @SerializedName("status")
    @Expose
    private Boolean status;


    public Complemento_startOrder(String skuComplemento, Integer cantidad, Boolean status) {
        super();
        this.skuComplemento = skuComplemento;
        this.cantidad = cantidad;
        this.status = status;
    }


    public String getSkuComplemento() {
        return skuComplemento;
    }

    public void setSkuComplemento(String skuComplemento) {
        this.skuComplemento = skuComplemento;
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