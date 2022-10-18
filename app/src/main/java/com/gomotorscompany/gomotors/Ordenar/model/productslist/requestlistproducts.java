package com.gomotorscompany.gomotors.Ordenar.model.productslist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class requestlistproducts {
    @SerializedName("tkn")
    @Expose
    private String token;

    public requestlistproducts(String token) {
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