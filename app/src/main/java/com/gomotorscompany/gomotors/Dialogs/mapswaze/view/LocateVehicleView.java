package com.gomotorscompany.gomotors.Dialogs.mapswaze.view;

public interface LocateVehicleView {
    void showLoader();
    void hideLoader();
    void showMessage(String message);
    void successLocationPermission();
    void appsInstalled(boolean mapsIsInstalled, boolean wazeIsInstaled);
    void requestLocationPermission();
    void setLocation(double latitude, double longitude,int code);

}
