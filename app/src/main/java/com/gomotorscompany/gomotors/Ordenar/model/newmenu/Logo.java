package com.gomotorscompany.gomotors.Ordenar.model.newmenu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Logo {

    @SerializedName("img")
    @Expose
    private String img;
    @SerializedName("sucursales")
    @Expose
    private List<Sucursale> sucursales = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public Logo() {
    }

    /**
     *
     * @param img
     * @param sucursales
     */
    public Logo(String img, List<Sucursale> sucursales) {
        super();
        this.img = img;
        this.sucursales = sucursales;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public List<Sucursale> getSucursales() {
        return sucursales;
    }

    public void setSucursales(List<Sucursale> sucursales) {
        this.sucursales = sucursales;
    }

}