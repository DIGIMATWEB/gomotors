package com.gomotorscompany.gomotors.Zonas.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class requestZonas {

    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("swiitch")
    @Expose
    private Boolean swiitch;


    public requestZonas(String token, Boolean swiitch) {
        super();
        this.token = token;
        this.swiitch = swiitch;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getSwiitch() {
        return swiitch;
    }

    public void setSwiitch(Boolean swiitch) {
        this.swiitch = swiitch;
    }
}
