package com.gomotorscompany.gomotors.Ordenar.model.newmenu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Paquete {



    @SerializedName("id_grupo_productos")
    @Expose
    private Integer idGrupoProductos;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("descripcion")
    @Expose
    private String descripcion;
    @SerializedName("precio")
    @Expose
    private String precio;
    @SerializedName("banner_pos")
    @Expose
    private String bannerPos;
    @SerializedName("productos")
    @Expose
    private List<Producto> productos = null;

    public Paquete(Integer idGrupoProductos, String nombre, String descripcion, String precio, String bannerPos, List<Producto> productos) {
        super();
        this.idGrupoProductos = idGrupoProductos;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.bannerPos = bannerPos;
        this.productos = productos;
    }

    public Integer getIdGrupoProductos() {
        return idGrupoProductos;
    }

    public void setIdGrupoProductos(Integer idGrupoProductos) {
        this.idGrupoProductos = idGrupoProductos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getBannerPos() {
        return bannerPos;
    }

    public void setBannerPos(String bannerPos) {
        this.bannerPos = bannerPos;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

}
