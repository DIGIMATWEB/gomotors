package com.gomotorscompany.gomotors.miscompras.model.get;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class responseGetOrder  implements Serializable {

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
    private List<datagetOrders> data = null;

    public responseGetOrder(String responseCode,String message,Integer activo,List<datagetOrders> data)
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

    public List<datagetOrders> getData() {
        return data;
    }

    public void setData(List<datagetOrders> data) {
        this.data = data;
    }

}

