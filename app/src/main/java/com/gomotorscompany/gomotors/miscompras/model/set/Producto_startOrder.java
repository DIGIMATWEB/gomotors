package com.gomotorscompany.gomotors.miscompras.model.set;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Producto_startOrder {
    @SerializedName("sku_producto")
    @Expose
    private String skuProducto;
    @SerializedName("cantidad")
    @Expose
    private Integer cantidad;
    @SerializedName("status")
    @Expose
    private Boolean status;

    public Producto_startOrder(String skuProducto, Integer cantidad, Boolean status) {
        super();
        this.skuProducto = skuProducto;
        this.cantidad = cantidad;
        this.status = status;
    }

    public String getSkuProducto() {
        return skuProducto;
    }

    public void setSkuProducto(String skuProducto) {
        this.skuProducto = skuProducto;
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