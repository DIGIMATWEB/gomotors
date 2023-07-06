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

    @SerializedName("fecha")
    private String date;
    @SerializedName("negocio")
    private String negocio;
    @SerializedName("cliente")
    private String cliente;
    @SerializedName("monto_articulos")
    private String apaga;
    @SerializedName("envio")
    private String envio;
    @SerializedName("direccion")
    private String direccion;


    public dataorderspending(Integer orden, String suc, Float latSuc, Float longSuc, List<ListaRepa> listaRepa,String date, String negocio, String cliente, String apaga,String envio,String direccion) {
        super();
        this.orden = orden;
        this.suc = suc;
        this.latSuc = latSuc;
        this.longSuc = longSuc;
        this.listaRepa = listaRepa;
        this.date= date;
        this.negocio=negocio;
        this.cliente=cliente;
        this.apaga=apaga;
        this.envio=envio;
        this.direccion=direccion;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNegocio() {
        return negocio;
    }

    public void setNegocio(String negocio) {
        this.negocio = negocio;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getApaga() {
        return apaga;
    }

    public void setApaga(String apaga) {
        this.apaga = apaga;
    }

    public String getEnvio() {
        return envio;
    }

    public void setEnvio(String envio) {
        this.envio = envio;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
