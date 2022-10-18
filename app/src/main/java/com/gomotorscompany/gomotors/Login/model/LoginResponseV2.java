package com.gomotorscompany.gomotors.Login.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponseV2 {

    @SerializedName("ResponseCode")
    @Expose
    private String responseCode;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private UserDataV2[] data;

    /**
     * @param message
     * @param responseCode
     * @param data
     */
    public LoginResponseV2(String responseCode, String message, UserDataV2[] data) {
        super();
        this.responseCode = responseCode;
        this.message = message;
        this.data = data;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserDataV2[] getData() {
        return data;
    }

    public void setData(UserDataV2[] data) {
        this.data = data;
    }

}
