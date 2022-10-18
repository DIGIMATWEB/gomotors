package com.gomotorscompany.gomotors.Ordenar.model.newmenu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class clasificaciones {


    @SerializedName("clasificacion")
    @Expose
    private String clasificacion;
    @SerializedName("producto")
    @Expose
    private List<listaproductos> producto = null;//Producto__2

    public clasificaciones(String clasificacion, List<listaproductos> producto) {
        super();
        this.clasificacion = clasificacion;
        this.producto = producto;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public List<listaproductos> getProducto() {
        return producto;
    }

    public void setProducto(List<listaproductos> producto) {
        this.producto = producto;
    }

}