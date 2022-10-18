package com.gomotorscompany.gomotors.Ordenar.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Banners {

    @SerializedName("urls")
    @Expose
    private String urls;


    public Banners(String urls) {
        super();
        this.urls = urls;
    }

    public String getUrls() {
        return urls;
    }

    public void setUrls(String urls) {
        this.urls = urls;
    }

}
