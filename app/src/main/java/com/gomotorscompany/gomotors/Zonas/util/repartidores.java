package com.gomotorscompany.gomotors.Zonas.util;

import com.gomotorscompany.gomotors.Zonas.model.requestRepartidores;
import com.gomotorscompany.gomotors.Zonas.model.responseRepartidores;
import com.gomotorscompany.gomotors.retrofit.RetrofitEndPointsV2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface repartidores {
    @POST(RetrofitEndPointsV2.REQUEST_REPARTIDORES)
    Call<responseRepartidores> getRepartidores(@Body requestRepartidores request);
}
