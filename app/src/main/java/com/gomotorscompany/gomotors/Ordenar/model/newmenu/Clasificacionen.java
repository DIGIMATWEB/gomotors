package com.gomotorscompany.gomotors.Ordenar.model.newmenu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Clasificacionen {

    @SerializedName("Clasificacion")
    @Expose
    private String clasificacion;
    @SerializedName("Productos")
    @Expose
    private List<Producton> productos = null;


    /**
     *
     * @param clasificacion
     * @param productos
     */
    public Clasificacionen(String clasificacion, List<Producton> productos) {
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

    public List<Producton> getProductos() {
        return productos;
    }

    public void setProductos(List<Producton> productos) {
        this.productos = productos;
    }
}
