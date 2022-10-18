package com.gomotorscompany.gomotors.Dialogs.mapswaze.presenter;

import androidx.fragment.app.FragmentActivity;

import com.gomotorscompany.gomotors.Dialogs.mapswaze.view.LocateVehicleView;

public interface LocateVehiclePresenter {
    void setView(LocateVehicleView view);

    /**RequestDataToInteractor*/
    void validateAppsInstalled(FragmentActivity activity);
    void validateLocationPermission(FragmentActivity activity);


    /**SetDataToView*/
    void showLoaderFromInteractor();
    void hideLoaderFromInteractor();
    void setMessageToView(String message);
    void setAppsInstalledToView(boolean mapsIsInstalled, boolean wazeIsInstaled);
    void requestLocationPermission();
    void setLocationToView(double latitude, double longitude,int code);

}
