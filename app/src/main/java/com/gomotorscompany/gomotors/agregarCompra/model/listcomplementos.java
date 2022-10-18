package com.gomotorscompany.gomotors.agregarCompra.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class listcomplementos {


    @SerializedName("complementos")
    @Expose
    private List<Complementoold> complementos = null;


    public listcomplementos(List<Complementoold> complementos) {
        super();
        this.complementos = complementos;
    }

    public List<Complementoold> getComplementos() {
        return complementos;
    }

    public void setComplementos(List<Complementoold> complementos) {
        this.complementos = complementos;
    }
}
