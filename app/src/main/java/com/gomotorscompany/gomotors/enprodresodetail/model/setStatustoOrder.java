package com.gomotorscompany.gomotors.enprodresodetail.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class setStatustoOrder {

    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("id_order")
    @Expose
    private Integer idOrder;
    @SerializedName("status_semaforo")
    @Expose
    private Integer statusSemaforo;

    public setStatustoOrder(String token, Integer idOrder, Integer statusSemaforo) {
        super();
        this.token = token;
        this.idOrder = idOrder;
        this.statusSemaforo = statusSemaforo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Integer idOrder) {
        this.idOrder = idOrder;
    }

    public Integer getStatusSemaforo() {
        return statusSemaforo;
    }

    public void setStatusSemaforo(Integer statusSemaforo) {
        this.statusSemaforo = statusSemaforo;
    }

}
