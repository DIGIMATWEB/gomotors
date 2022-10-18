package com.gomotorscompany.gomotors.register.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class responseRegister {


    @SerializedName("ResponseCode")
    @Expose
    private String responseCode;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("Activo")
    @Expose
    private Integer activo;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("apellido")
    @Expose
    private String apellido;
    @SerializedName("telefono")
    @Expose
    private String telefono;
    @SerializedName("level_permision")
    @Expose
    private int permision;

    public responseRegister(String responseCode, String message, Integer activo ,String token,String nombre,String apellido,String telefono,int permision) {
        super();
        this.responseCode = responseCode;
        this.message = message;
        this.activo = activo;

    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getPermision() {
        return permision;
    }

    public void setPermision(int permision) {
        this.permision = permision;
    }


}
