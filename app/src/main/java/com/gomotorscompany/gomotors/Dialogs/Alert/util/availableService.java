package com.gomotorscompany.gomotors.Dialogs.Alert.util;

import com.gomotorscompany.gomotors.Dialogs.Alert.model.requestAvailable;
import com.gomotorscompany.gomotors.Dialogs.Alert.model.responseAvailable;
import com.gomotorscompany.gomotors.Splash.model.requestSplash;
import com.gomotorscompany.gomotors.Splash.model.responseSplash;
import com.gomotorscompany.gomotors.enprogreso.model.digimatlocation.requestLocations;
import com.gomotorscompany.gomotors.enprogreso.model.digimatlocation.responseLocation;
import com.gomotorscompany.gomotors.retrofit.RetrofitEndPointsV2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface availableService {
    @POST(RetrofitEndPointsV2.AVAILABLE_APP)
    Call<responseAvailable> getAvaileble(@Body requestAvailable request);
    @POST(RetrofitEndPointsV2.AVAILABLE_APP)
    Call<responseLocation> getlocationVehicle(@Body requestLocations request);
}
