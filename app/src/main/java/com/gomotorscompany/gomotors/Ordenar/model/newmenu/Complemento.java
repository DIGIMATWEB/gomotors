package com.gomotorscompany.gomotors.Ordenar.model.newmenu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Complemento implements Serializable {
    @SerializedName("nombre_producto_clasificacion")
    @Expose
    private String nombreProductoClasificacion;
    @SerializedName("producto")
    @Expose
    private List<productocomplemento> producto = null;//Producto__3  complemento

    public Complemento(String nombreProductoClasificacion, List<productocomplemento> producto) {
        super();
        this.nombreProductoClasificacion = nombreProductoClasificacion;
        this.producto = producto;
    }

    public String getNombreProductoClasificacion() {
        return nombreProductoClasificacion;
    }

    public void setNombreProductoClasificacion(String nombreProductoClasificacion) {
        this.nombreProductoClasificacion = nombreProductoClasificacion;
    }

    public List<productocomplemento> getProducto() {
        return producto;
    }

    public void setProducto(List<productocomplemento> producto) {
        this.producto = producto;
    }
}