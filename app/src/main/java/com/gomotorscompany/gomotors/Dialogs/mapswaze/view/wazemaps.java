package com.gomotorscompany.gomotors.Dialogs.mapswaze.view;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;

import com.gomotorscompany.gomotors.Dialogs.mapswaze.presenter.ExternalGPSPresenterImpl;
import com.gomotorscompany.gomotors.Dialogs.mapswaze.presenter.LocateVehiclePresenter;
import com.gomotorscompany.gomotors.R;
import com.gomotorscompany.gomotors.retrofit.GeneralConstantsV2;

public class wazemaps   extends DialogFragment  implements View.OnClickListener, CheckBox.OnCheckedChangeListener,LocateVehicleView {
    public static final String TAG = wazemaps.class.getSimpleName();
    private boolean externalAppsaved = false;
    private LocateVehiclePresenter presenter;
    private ProgressBar progressBarRoute;
    private LinearLayout googleMaps, waze;
    private TextView txtCloseDialog;
    private boolean mapsIsInstalled, wazeIsInstalled;
    private double latitude, longitude;
    private double sucursallat, sucursallong,clientLat,clientLong;
    private CheckBox checkBoxExternalGPSApp;
    private SharedPreferences externalGPSAppPrefs;
    private SharedPreferences.Editor externalGPSAppEditor;
    private int semaforoorden;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Light_NoActionBar);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.external_gps_dialog, container, false);
        getDialog().getWindow().setBackgroundDrawableResource(R.color.customTransparent) ;
        setCancelable(false);
        initDialog(view);
        //setFonts();
        return view;
    }
    private void initDialog(View view) {
        googleMaps = view.findViewById(R.id.googlemaps);
        waze = view.findViewById(R.id.waze);
        txtCloseDialog = view.findViewById(R.id.cancelar);
        checkBoxExternalGPSApp = view.findViewById(R.id.checkbox_external_gps_app);
        checkBoxExternalGPSApp.setOnCheckedChangeListener(this);
        ConstraintLayout constraintLayout = view.findViewById(R.id.externalgpsconstraint);
        SharedPreferences prefs = getContext().getSharedPreferences("ExternalGPSApp", Context.MODE_PRIVATE);
        String valueGPSAppPrefs = prefs.getString("Prefs", "");
        if (valueGPSAppPrefs.equals("GoogleMaps")) {
            openGoogleMaps();
        } else if (valueGPSAppPrefs.equals("Waze")) {
            openWaze();
        }
        constraintLayout.setOnClickListener(this);
        googleMaps.setOnClickListener(this);
        waze.setOnClickListener(this);
        txtCloseDialog.setOnClickListener(this);
        externalGPSAppPrefs = getContext().getSharedPreferences("ExternalGPSApp", Context.MODE_PRIVATE);
        externalGPSAppEditor = externalGPSAppPrefs.edit();
        presenter = new ExternalGPSPresenterImpl();
        presenter.setView(this);
        presenter.validateAppsInstalled(getActivity());
        presenter.validateLocationPermission(getActivity());

    }

    private void closeDialog() {
        this.dismiss();
    }

    public void setLocationVehicle(double mainlatitude, double mainlongitude,double sucursalDestinationlatitude, double sucursalDestinationlongitude,int semaforo) {
        Log.e("codelocaitonwazemaps","datafromdetails "+mainlatitude+"   "+mainlongitude+"   "+sucursalDestinationlatitude+"   "+sucursalDestinationlongitude+"  semaforo "+semaforo);
         //this.longitude = mainlongitude;
        this.semaforoorden=semaforo;
        this.clientLat=mainlatitude;
        this.clientLong=mainlongitude;
        this.sucursallat = sucursalDestinationlatitude;
        this.sucursallong = sucursalDestinationlongitude;
    }
    @Override
    public void setLocation(double latitude, double longitude,int code) {
        this.latitude = latitude;
        this.longitude = longitude;
        Log.e("codelocaitonwazemaps","datafrominteractor "+latitude+"   "+longitude);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.googlemaps:
                initGoogleRequest();
                break;
            case R.id.waze:
                initWazeRequest();
                break;
            case R.id.cancelar:
                closeDialog();
                break;
            case R.id.externalgpsconstraint:
                closeDialog();
                break;
        }
    }
    private void initGoogleRequest() {
        if (externalAppsaved) {
            externalGPSAppEditor.putString("Prefs", "GoogleMaps");
            externalGPSAppEditor.commit();
            openGoogleMaps();
        } else {
            externalGPSAppEditor.putString("Prefs", "");
            externalGPSAppEditor.commit();
            openGoogleMaps();
        }
    }
    private void initWazeRequest() {
        if (externalAppsaved) {
            externalGPSAppEditor.putString("Prefs", "Waze");
            externalGPSAppEditor.commit();
            openWaze();
        } else {
            externalGPSAppEditor.putString("Prefs", "");
            externalGPSAppEditor.commit();
            openWaze();
        }
    }
    private void openGoogleMaps() {
        if (mapsIsInstalled) {
            if(semaforoorden==4){
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/dir/?api=1&" + "origin=" + latitude + "," + longitude + "&" + "destination=" + clientLat + "," + clientLong + "&travelmode=driving"));
                startActivity(intent);
            }else{
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/dir/?api=1&" + "origin=" + latitude + "," + longitude + "&" + "destination=" + sucursallat + "," + sucursallong + "&travelmode=driving"));
                startActivity(intent);
            }


        } else {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.googleMapsPlayStore))));
        }
        closeDialog();
    }

    private void openWaze() {
        if (wazeIsInstalled) {
            //String uri = "waze://?ll=" + vehicleLatitude + "," + vehicleLongitude + "&navigate=yes";
            if(semaforoorden==4) {
                String uri = "https://waze.com/ul?ll=" + clientLat + "," + clientLong;
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(uri)));
            }else
            {
                String uri = "https://waze.com/ul?ll=" + sucursallat + "," + sucursallong;
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(uri)));
            }
        } else {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.wazePlayStore))));
        }
        closeDialog();
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if (isChecked) {
            externalAppsaved = true;
        } else {
            externalAppsaved = false;
        }
    }


    @Override
    public void showLoader() {

    }

    @Override
    public void hideLoader() {

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void successLocationPermission() {

    }

    @Override
    public void appsInstalled(boolean mapsIsInstalled, boolean wazeIsInstaled) {
        this.mapsIsInstalled = mapsIsInstalled;
        this.wazeIsInstalled = wazeIsInstaled;
    }

    @Override
    public void requestLocationPermission() {
        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, GeneralConstantsV2.LOCATION_PERMISSION);
    }


}
