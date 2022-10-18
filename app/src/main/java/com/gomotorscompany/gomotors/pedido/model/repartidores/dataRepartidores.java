package com.gomotorscompany.gomotors.pedido.model.repartidores;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class dataRepartidores {

    @SerializedName("orden")
    @Expose
    private Integer orden;
    @SerializedName("suc")
    @Expose
    private String suc;
    @SerializedName("lat_suc")
    @Expose
    private Double latSuc;
    @SerializedName("long_suc")
    @Expose
    private Double longSuc;
    @SerializedName("listaRepa")
    @Expose
    private List<repartidoresData> listaRepa = null;

    public dataRepartidores(Integer orden, String suc, Double latSuc, Double longSuc, List<repartidoresData> listaRepa) {
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

    public Double getLatSuc() {
        return latSuc;
    }

    public void setLatSuc(Double latSuc) {
        this.latSuc = latSuc;
    }

    public Double getLongSuc() {
        return longSuc;
    }

    public void setLongSuc(Double longSuc) {
        this.longSuc = longSuc;
    }

    public List<repartidoresData> getListaRepa() {
        return listaRepa;
    }

    public void setListaRepa(List<repartidoresData> listaRepa) {
        this.listaRepa = listaRepa;
    }
}
