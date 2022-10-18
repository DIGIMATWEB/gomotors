package com.gomotorscompany.gomotors.Login.utils;

import com.gomotorscompany.gomotors.Login.model.LoginRequestV2;
import com.gomotorscompany.gomotors.Login.model.LoginResponseV2;
import com.gomotorscompany.gomotors.retrofit.RetrofitEndPointsV2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginServicesV2 {
    @POST(RetrofitEndPointsV2.LOGINV2)
    Call<LoginResponseV2> login(@Body LoginRequestV2 request);
}
