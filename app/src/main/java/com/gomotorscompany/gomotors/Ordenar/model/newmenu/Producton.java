package com.gomotorscompany.gomotors.Ordenar.model.newmenu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Producton {

    @SerializedName("Clave")
    @Expose
    private String clave;
    @SerializedName("Nombre")
    @Expose
    private String nombre;
    @SerializedName("Descripcion")
    @Expose
    private String descripcion;
    //  "Descripcion"

    @SerializedName("Precio")
    @Expose
    private String precio;
    @SerializedName("Imagen")
    @Expose
    private String imagen;
    @SerializedName("Complementos")
    @Expose
    private List<Complementosn> complementos = null;
    /**
     *
     * @param clave
     * @param precio
     * @param imagen
     * @param nombre
     */
    public Producton(String clave, String nombre,String descripcion, String precio, String imagen,List<Complementosn> complementos ) {
        super();
        this.clave = clave;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagen = imagen;
        this.complementos = complementos;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Complementosn> getComplementos() {
        return complementos;
    }

    public void setComplementos(List<Complementosn> complementos) {
        this.complementos = complementos;
    }

}
