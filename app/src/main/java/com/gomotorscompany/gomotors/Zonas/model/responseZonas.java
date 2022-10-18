package com.gomotorscompany.gomotors.Zonas.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class responseZonas {

    @SerializedName("ResponseCode")
    @Expose
    private String responseCode;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("Activo")
    @Expose
    private Integer activo;
    @SerializedName("data")
    @Expose
    private List<datarequesZonas> data = null;

    /**
     *
     * @param data
     * @param message
     * @param responseCode
     * @param activo
     */
    public responseZonas(String responseCode, String message, Integer activo, List<datarequesZonas> data) {
        super();
        this.responseCode = responseCode;
        this.message = message;
        this.activo = activo;
        this.data = data;
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

    public List<datarequesZonas> getData() {
        return data;
    }

    public void setData(List<datarequesZonas> data) {
        this.data = data;
    }

}
