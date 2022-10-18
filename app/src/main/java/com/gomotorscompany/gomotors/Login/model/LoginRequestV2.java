package com.gomotorscompany.gomotors.Login.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginRequestV2 {
    @SerializedName("user")
    @Expose
    private String user;
    @SerializedName("pwd")
    @Expose
    private String password;

    /**
     * @param user
     * @param password
     */
    public LoginRequestV2(String user, String password) {
        super();
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
