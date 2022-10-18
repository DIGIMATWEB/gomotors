package com.gomotorscompany.gomotors.pedido.model.repartidores;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class requestRepartidoresCercanos {


    @SerializedName("token")
    @Expose
    private String token;

    public requestRepartidoresCercanos(String token) {
        super();
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
