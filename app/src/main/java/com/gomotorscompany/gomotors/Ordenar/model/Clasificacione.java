package com.gomotorscompany.gomotors.Ordenar.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Clasificacione {

    @SerializedName("Clasificacion")
    @Expose
    private String clasificacion;
    @SerializedName("Productos")
    @Expose
    private List<Producto> productos = null;


    /**
     *
     * @param clasificacion
     * @param productos
     */
    public Clasificacione(String clasificacion, List<Producto> productos) {
        super();
        this.clasificacion = clasificacion;
        this.productos = productos;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}
