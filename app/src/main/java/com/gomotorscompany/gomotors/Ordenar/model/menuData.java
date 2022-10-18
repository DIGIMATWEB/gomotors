package com.gomotorscompany.gomotors.Ordenar.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class menuData {

    @SerializedName("Empresa")
    @Expose
    private String empresaNombre;
    @SerializedName("descripcionAnuncio")
    @Expose
    private String descripcioncompania;
    @SerializedName("imagenComercial")
    @Expose
    private String imagenComercial;

    @SerializedName("Id_Empresa")
    @Expose
    private Integer idEmpresa;

    @SerializedName("Categoria")
    @Expose
    private String Categoria;

    @SerializedName("Sucursales")
    @Expose
    private List<sucursales> sucursales = null;

    /**faltan los banners*/
    @SerializedName("banners")
    @Expose
    private List<Banners> banner = null;

    public menuData(String empresaNombre,String descripcioncompania,String imagenComercial,Integer idEmpresa,String Categoria,List<sucursales> sucursales, List<Banners> banner)
    {
        this.empresaNombre=empresaNombre;
        this.descripcioncompania=descripcioncompania;
        this.imagenComercial=imagenComercial;
        this.idEmpresa=idEmpresa;
        this.Categoria=Categoria;
        this.sucursales=sucursales;
        this.banner=banner;

    }

    public String getEmpresaNombre() {
        return empresaNombre;
    }

    public void setEmpresaNombre(String empresaNombre) {
        this.empresaNombre = empresaNombre;
    }


    public String getImagenComercial() {
        return imagenComercial;
    }

    public void setImagenComercial(String imagenComercial) {
        this.imagenComercial = imagenComercial;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public List<sucursales> getSucursales() {
        return sucursales;
    }

    public void setSucursales(List<sucursales> sucursales) {
        this.sucursales = sucursales;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String categoria) {
        Categoria = categoria;
    }

    public String getDescripcioncompania() {
        return descripcioncompania;
    }

    public void setDescripcioncompania(String descripcioncompania) {
        this.descripcioncompania = descripcioncompania;
    }

    public List<Banners> getBanner() {
        return banner;
    }

    public void setBanner(List<Banners> banner) {
        this.banner = banner;
    }

}