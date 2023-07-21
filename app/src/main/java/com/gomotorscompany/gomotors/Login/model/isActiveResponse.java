package com.gomotorscompany.gomotors.Login.model;

import com.google.gson.annotations.SerializedName;

public class isActiveResponse {

    @SerializedName("ResponseCode")
    private String responseCode;
    @SerializedName("Message")
    private String message;
    @SerializedName("Activo")
    private Integer activo;
    public isActiveResponse(String responseCode, String message, Integer activo) {
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
}
