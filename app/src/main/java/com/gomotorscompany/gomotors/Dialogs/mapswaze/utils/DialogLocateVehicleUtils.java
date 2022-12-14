package com.gomotorscompany.gomotors.Dialogs.mapswaze.utils;

import android.content.pm.PackageManager;

import androidx.fragment.app.FragmentActivity;

public class DialogLocateVehicleUtils {
    public static boolean appInstalledOrNot(String uri, FragmentActivity activity) {
        PackageManager pm = activity.getPackageManager();
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
        }
        return false;
    }
}
