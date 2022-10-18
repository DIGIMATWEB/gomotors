package com.gomotorscompany.gomotors.Ordenar.model.ingredientesAlone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class requestIngredients {

    @SerializedName("token")
    @Expose
    private String token;

    public requestIngredients(String token) {
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