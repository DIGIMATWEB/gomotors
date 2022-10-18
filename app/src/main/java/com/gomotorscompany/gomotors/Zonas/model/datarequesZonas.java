package com.gomotorscompany.gomotors.Zonas.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class datarequesZonas {
    @SerializedName("coloniaID")
    @Expose
    private Integer coloniaID;

    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("location")
    @Expose
    private List<Location> location = null;

//    @SerializedName("color")
//    @Expose
//    private String color;
     /**
     * "color": "#0095C4 "
        */
    public datarequesZonas(Integer coloniaID,String nombre, List<Location> location) {
        super();
        this.coloniaID = coloniaID;
        this.nombre = nombre;
        this.location = location;
       // this.color=color;
    }

    public Integer getColoniaID() {
        return coloniaID;
    }

    public void setColoniaID(Integer coloniaID) {
        this.coloniaID = coloniaID;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public List<Location> getLocation() {
        return location;
    }

    public void setLocation(List<Location> location) {
        this.location = location;
    }

//    public String getColor() {
//        return color;
//    }
//
//    public void setColor(String color) {
//        this.color = color;
//    }

}
