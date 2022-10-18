package com.gomotorscompany.gomotors.Login.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserDataV2 {


    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("email")
    @Expose
    private String telefono;
    @SerializedName("tokken")
    @Expose
    private String token;
    @SerializedName("id_perfil")
    @Expose
    private int permissionsId;

    public UserDataV2(String nombre, String telefono,  String token,int permissionsId) {
        super();
        this.nombre = nombre;
        this.telefono = telefono;
        this.token = token;
        this.permissionsId=permissionsId;


    }

    public String getEmployeeName() {
        return nombre;
    }

    public void setEmployeeName(String employee_name) {
        this.nombre = employee_name;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String firstLogin) {
        this.telefono = firstLogin;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public int getPermissionsId() {
        return permissionsId;
    }

    public void setPermissionsId(int permissionsId) {
        this.permissionsId = permissionsId;
    }
}
