package com.gomotorscompany.gomotors.enprogreso.model.digimatlocation;

import com.gomotorscompany.gomotors.Splash.model.dataSplash;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class responseLocation {

    @SerializedName("ResponseCode")
    @Expose
    private Integer responseCode;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("Activo")
    @Expose
    private Integer activo;
//    @SerializedName("data")
//    @Expose
//    private List<dataSplash> data = null;

    public responseLocation(Integer responseCode, String message, Integer activo){//, List<dataSplash> data) {
        super();
        this.responseCode = responseCode;
        this.message = message;
        this.activo = activo;
       // this.data = data;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
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

//    public List<dataSplash> getData() {
//        return data;
//    }
//
//    public void setData(List<dataSplash> data) {
//        this.data = data;
//    }
}
