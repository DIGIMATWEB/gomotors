package com.gomotorscompany.gomotors.Splash.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class dataSplash {
    @SerializedName("urlsplashimage")
    @Expose
    private String urlsplashimage;
    @SerializedName("rgbaColor")
    @Expose
    private String rgbaColor;
    @SerializedName("appName")
    @Expose
    private String appName;

    public dataSplash(String urlsplashimage, String rgbaColor, String appName) {
        super();
        this.urlsplashimage = urlsplashimage;
        this.rgbaColor = rgbaColor;
        this.appName = appName;
    }

    public String getUrlsplashimage() {
        return urlsplashimage;
    }

    public void setUrlsplashimage(String urlsplashimage) {
        this.urlsplashimage = urlsplashimage;
    }

    public String getRgbaColor() {
        return rgbaColor;
    }

    public void setRgbaColor(String rgbaColor) {
        this.rgbaColor = rgbaColor;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
}
