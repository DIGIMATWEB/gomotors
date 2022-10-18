package com.gomotorscompany.gomotors.Ordenar.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class responseMenu {

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
    private List<menuData> data = null;

    public responseMenu(String responseCode,String message,Integer activo,List<menuData> data)
    {
        this.responseCode=responseCode;
        this.message=message;
        this.activo=activo;
        this.data=data;

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

    public List<menuData> getData() {
        return data;
    }

    public void setData(List<menuData> data) {
        this.data = data;
    }

}
