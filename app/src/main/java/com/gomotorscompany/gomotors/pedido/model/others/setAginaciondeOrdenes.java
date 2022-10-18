package com.gomotorscompany.gomotors.pedido.model.others;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class setAginaciondeOrdenes {

    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("id_orden")
    @Expose
    private Integer idOrden;

    public setAginaciondeOrdenes(String token, Integer idOrden) {
        super();
        this.token = token;
        this.idOrden = idOrden;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(Integer idOrden) {
        this.idOrden = idOrden;
    }

}
