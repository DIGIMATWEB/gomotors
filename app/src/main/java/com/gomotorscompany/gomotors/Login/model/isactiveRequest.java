package com.gomotorscompany.gomotors.Login.model;

import com.google.gson.annotations.SerializedName;

public class isactiveRequest {
    @SerializedName("token")
    private String token;

    public isactiveRequest(String token) {
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
