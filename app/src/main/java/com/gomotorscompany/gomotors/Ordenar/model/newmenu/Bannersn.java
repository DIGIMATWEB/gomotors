package com.gomotorscompany.gomotors.Ordenar.model.newmenu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bannersn {

    @SerializedName("urls")
    @Expose
    private String urls;


    public Bannersn(String urls) {
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
