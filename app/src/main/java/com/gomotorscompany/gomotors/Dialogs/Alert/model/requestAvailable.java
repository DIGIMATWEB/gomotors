package com.gomotorscompany.gomotors.Dialogs.Alert.model;

import com.google.gson.annotations.SerializedName;

public class requestAvailable {

        @SerializedName("idApp")
        private String idApp;
        @SerializedName("appName")
        private String appName;
        public requestAvailable(String idApp, String appName) {
            super();
            this.idApp = idApp;
            this.appName = appName;
        }

        public String getIdApp() {
            return idApp;
        }

        public void setIdApp(String idApp) {
            this.idApp = idApp;
        }

        public String getAppName() {
            return appName;
        }

        public void setAppName(String appName) {
            this.appName = appName;
        }

    }
