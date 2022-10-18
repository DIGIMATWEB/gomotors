package com.gomotorscompany.gomotors.Splash.utils;

import com.gomotorscompany.gomotors.Splash.model.requestSplash;
import com.gomotorscompany.gomotors.Splash.model.responseSplash;
import com.gomotorscompany.gomotors.retrofit.RetrofitEndPointsV2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface splashService {

    @POST(RetrofitEndPointsV2.GET_SPLASHDATAV2)
    Call<responseSplash> getSplashData(@Body requestSplash request);
}
