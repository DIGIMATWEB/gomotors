package com.gomotorscompany.gomotors.Dialogs.mapswaze.interactor;

import androidx.fragment.app.FragmentActivity;

public interface LocateVehicleInteractor {
    void validateAppsInstalled(FragmentActivity activity);

    void validateLocationPermission(FragmentActivity activity);

}
