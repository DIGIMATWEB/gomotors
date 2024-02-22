package com.gomotorscompany.gomotors.Dialogs.Alert.model;

import com.google.gson.annotations.SerializedName;

public class responseAvailable {
    @SerializedName("resconseCode")
    private Integer resconseCode;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private dataAvailable data;
    public responseAvailable(Integer resconseCode, String message, dataAvailable data) {
        super();
        this.resconseCode = resconseCode;
        this.message = message;
        this.data = data;
    }

    public Integer getResconseCode() {
        return resconseCode;
    }

    public void setResconseCode(Integer resconseCode) {
        this.resconseCode = resconseCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public dataAvailable getData() {
        return data;
    }

    public void setData(dataAvailable data) {
        this.data = data;
    }

}
