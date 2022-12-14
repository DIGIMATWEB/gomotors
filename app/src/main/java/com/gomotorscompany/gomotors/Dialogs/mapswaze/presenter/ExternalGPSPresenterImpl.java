package com.gomotorscompany.gomotors.Dialogs.mapswaze.presenter;

import androidx.fragment.app.FragmentActivity;

import com.gomotorscompany.gomotors.Dialogs.mapswaze.interactor.DialogLocateVehicleInteractorImpl;
import com.gomotorscompany.gomotors.Dialogs.mapswaze.interactor.LocateVehicleInteractor;
import com.gomotorscompany.gomotors.Dialogs.mapswaze.view.LocateVehicleView;

public class ExternalGPSPresenterImpl implements LocateVehiclePresenter {

    private LocateVehicleView view;
    private LocateVehicleInteractor interactor;

    @Override
    public void setView(LocateVehicleView view) {
        this.view = view;
        interactor = new DialogLocateVehicleInteractorImpl(this);
    }

    @Override
    public void validateAppsInstalled(FragmentActivity activity) {
        if (view != null) {
            interactor.validateAppsInstalled(activity);
        }
    }

    @Override
    public void validateLocationPermission(FragmentActivity activity) {
        if (view != null) {
            interactor.validateLocationPermission(activity);
        }
    }

    @Override
    public void showLoaderFromInteractor() {
        if (view != null) {
            view.showLoader();
        }
    }

    @Override
    public void hideLoaderFromInteractor() {
        if (view != null) {
            view.hideLoader();
        }
    }

    @Override
    public void setMessageToView(String message) {
        if (view != null) {
            view.showMessage(message);
        }
    }

    @Override
    public void setAppsInstalledToView(boolean mapsIsInstalled, boolean wazeIsInstaled) {
        if (view != null) {
            view.appsInstalled(mapsIsInstalled, wazeIsInstaled);
        }
    }

    @Override
    public void requestLocationPermission() {
        if (view != null) {
            view.requestLocationPermission();
        }
    }

    @Override
    public void setLocationToView(double latitude, double longitude ,int code) {
        if (view != null) {
            view.setLocation(latitude, longitude,code);
        }
    }

}
