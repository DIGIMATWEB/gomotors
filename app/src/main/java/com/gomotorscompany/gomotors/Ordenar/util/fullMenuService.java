package com.gomotorscompany.gomotors.Ordenar.util;

import com.gomotorscompany.gomotors.Ordenar.model.requesMenu;
import com.gomotorscompany.gomotors.Ordenar.model.responseMenu;
import com.gomotorscompany.gomotors.retrofit.RetrofitEndPointsV2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface fullMenuService {

     @POST(RetrofitEndPointsV2.GET_ALLMENU)
     Call<responseMenu> getFullMenu(@Body requesMenu request);
}
