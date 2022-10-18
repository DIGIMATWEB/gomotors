package com.gomotorscompany.gomotors.enprodresodetail.util;

import com.gomotorscompany.gomotors.enprodresodetail.model.repartidor.requetsliberar;
import com.gomotorscompany.gomotors.enprodresodetail.model.repartidor.responseLiberar;
import com.gomotorscompany.gomotors.enprodresodetail.model.responseChangeStatus;
import com.gomotorscompany.gomotors.enprodresodetail.model.setStatustoOrder;
import com.gomotorscompany.gomotors.enprogreso.model.responseNewLocationDriver;
import com.gomotorscompany.gomotors.enprogreso.model.setNewlocationDriver;
import com.gomotorscompany.gomotors.retrofit.RetrofitEndPointsV2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface servicedetail {

    @POST(RetrofitEndPointsV2.SET_NEWSTATUS)
    Call<responseChangeStatus> setNewStatustoOrder(@Body setStatustoOrder request);

    @POST(RetrofitEndPointsV2.SET_LOCATION)
    Call<responseNewLocationDriver> setLocationmiscomprasdetail(@Body setNewlocationDriver request);
    @POST(RetrofitEndPointsV2.SET_LIBERARREPARTIDOR)
    Call<responseLiberar> requestLiberar(@Body requetsliberar requets);
}
