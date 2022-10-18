package com.gomotorscompany.gomotors.Ordenar.model.productslist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class datatipeproducts {


    @SerializedName("id_categoria")
    @Expose
    private Integer idCategoria;
    @SerializedName("categoria")
    @Expose
    private String categoria;

    public datatipeproducts(Integer idCategoria, String categoria) {
        super();
        this.idCategoria = idCategoria;
        this.categoria = categoria;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}