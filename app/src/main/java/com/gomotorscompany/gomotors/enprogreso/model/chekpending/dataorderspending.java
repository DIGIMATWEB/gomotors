package com.gomotorscompany.gomotors.enprogreso.model.chekpending;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class dataorderspending {


    @SerializedName("orden")
    @Expose
    private Integer orden;
    @SerializedName("suc")
    @Expose
    private String suc;
    @SerializedName("lat_suc")
    @Expose
    private Float latSuc;
    @SerializedName("long_suc")
    @Expose
    private Float longSuc;
    @SerializedName("listaRepa")
    @Expose
    private List<ListaRepa> listaRepa = null;

    public dataorderspending(Integer orden, String suc, Float latSuc, Float longSuc, List<ListaRepa> listaRepa) {
        super();
        this.orden = orden;
        this.suc = suc;
        this.latSuc = latSuc;
        this.longSuc = longSuc;
        this.listaRepa = listaRepa;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public String getSuc() {
        return suc;
    }

    public void setSuc(String suc) {
        this.suc = suc;
    }

    public Float getLatSuc() {
        return latSuc;
    }

    public void setLatSuc(Float latSuc) {
        this.latSuc = latSuc;
    }

    public Float getLongSuc() {
        return longSuc;
    }

    public void setLongSuc(Float longSuc) {
        this.longSuc = longSuc;
    }

    public List<ListaRepa> getListaRepa() {
        return listaRepa;
    }

    public void setListaRepa(List<ListaRepa> listaRepa) {
        this.listaRepa = listaRepa;
    }
}
