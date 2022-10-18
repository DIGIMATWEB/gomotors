package com.gomotorscompany.gomotors.agregarCompra.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Complementoold implements Serializable {
    @SerializedName("sku_complemento")
    @Expose
    private String skuComplemento;
    @SerializedName("cantidad")
    @Expose
    private Integer cantidad;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("imagen")
    @Expose
    private String  Imagen;


    @SerializedName("categoria")
    @Expose
    private String  categoria;
    public Complementoold(String skuComplemento, Integer cantidad, Boolean status,String  Imagen,String  categoria) {
        super();
        this.skuComplemento = skuComplemento;
        this.cantidad = cantidad;
        this.status = status;
        this.Imagen=Imagen;
        this.categoria=categoria;
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

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String imagen) {
        Imagen = imagen;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
