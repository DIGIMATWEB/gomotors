package com.gomotorscompany.gomotors.Zonas.util;

import com.gomotorscompany.gomotors.Zonas.model.requestEraseZone;
import com.gomotorscompany.gomotors.Zonas.model.requestUpdateZones;
import com.gomotorscompany.gomotors.Zonas.model.requestZonas;
import com.gomotorscompany.gomotors.Zonas.model.responseEraseZone;
import com.gomotorscompany.gomotors.Zonas.model.responseUpdateZones;
import com.gomotorscompany.gomotors.Zonas.model.responseZonas;
import com.gomotorscompany.gomotors.retrofit.RetrofitEndPointsV2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface zonesService {

    @POST(RetrofitEndPointsV2.GET_ZONAS)
    Call<responseZonas> getZones(@Body requestZonas request);

    @POST(RetrofitEndPointsV2.UPDATE_ZONES)
    Call<responseUpdateZones> updateZones(@Body requestUpdateZones request);


    @POST(RetrofitEndPointsV2.ERASE_ZONES)
    Call<responseEraseZone> eraseZone(@Body requestEraseZone request);



}
