package com.gomotorscompany.gomotors.enprogreso.model.chekpending;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class requestpending {

    @SerializedName("token")
    @Expose
    private String token;



    public requestpending(String token) {
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
