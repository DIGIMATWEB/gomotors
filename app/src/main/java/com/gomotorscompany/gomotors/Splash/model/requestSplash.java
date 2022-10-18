package com.gomotorscompany.gomotors.Splash.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class requestSplash {
    @SerializedName("token")
    @Expose
    private String token;

    public requestSplash(String token)
    {
        this.token=token;
    }
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
