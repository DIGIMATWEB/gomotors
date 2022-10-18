package com.gomotorscompany.gomotors.miscompras.model.get;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Paquete  implements Serializable{//implements Serializable
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("descripcion")
    @Expose
    private String descripcion;
    @SerializedName("precio")
    @Expose
    private String precio;
    @SerializedName("cantidad")
    @Expose
    private Integer cantidad;
    @SerializedName("estatus")
    @Expose
    private Boolean estatus;
    @SerializedName("banner")
    @Expose
    private String banner;
    @SerializedName("productos")
    @Expose
    private List<Producto> productos = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public Paquete() {
    }

    /**
     *
     * @param descripcion
     * @param precio
     * @param estatus
     * @param banner
     * @param id
     * @param cantidad
     * @param nombre
     * @param productos
     */
    public Paquete(Integer id, String nombre, String descripcion, String precio, Integer cantidad, Boolean estatus, String banner, List<Producto> productos) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
        this.estatus = estatus;
        this.banner = banner;
        this.productos = productos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Boolean getEstatus() {
        return estatus;
    }

    public void setEstatus(Boolean estatus) {
        this.estatus = estatus;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

}
