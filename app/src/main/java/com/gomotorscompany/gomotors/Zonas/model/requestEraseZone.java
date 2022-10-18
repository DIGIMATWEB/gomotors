package com.gomotorscompany.gomotors.Zonas.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class requestEraseZone {

    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("id_colonia")
    @Expose
    private Integer idColonia;


    /**
     *
     * @param idColonia
     * @param token
     */
    public requestEraseZone(String token, Integer idColonia) {
        super();
        this.token = token;
        this.idColonia = idColonia;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getIdColonia() {
        return idColonia;
    }

    public void setIdColonia(Integer idColonia) {
        this.idColonia = idColonia;
    }
}
