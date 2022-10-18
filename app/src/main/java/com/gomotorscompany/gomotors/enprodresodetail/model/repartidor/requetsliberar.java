package com.gomotorscompany.gomotors.enprodresodetail.model.repartidor;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class requetsliberar {

    @SerializedName("token")
    @Expose
    private String token;

    public requetsliberar(String token) {
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
