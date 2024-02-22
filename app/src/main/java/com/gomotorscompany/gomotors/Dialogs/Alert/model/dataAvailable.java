package com.gomotorscompany.gomotors.Dialogs.Alert.model;

import com.google.gson.annotations.SerializedName;

public class dataAvailable {

    @SerializedName("nameApp")
    private String nameApp;
    @SerializedName("available")
    private String available;

    public dataAvailable(String nameApp, String available) {
        super();
        this.nameApp = nameApp;
        this.available = available;
    }

    public String getNameApp() {
        return nameApp;
    }

    public void setNameApp(String nameApp) {
        this.nameApp = nameApp;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

}
