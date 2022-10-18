package com.gomotorscompany.gomotors.Ordenar.util;

import com.gomotorscompany.gomotors.Ordenar.model.ingredientesAlone.requestIngredients;
import com.gomotorscompany.gomotors.Ordenar.model.ingredientesAlone.responseIngredients;
import com.gomotorscompany.gomotors.Ordenar.model.newmenu.requesMenun;
import com.gomotorscompany.gomotors.Ordenar.model.newmenu.responseMenun;
import com.gomotorscompany.gomotors.Ordenar.model.productslist.requestlistproducts;
import com.gomotorscompany.gomotors.Ordenar.model.productslist.responselistaproducts;
import com.gomotorscompany.gomotors.retrofit.RetrofitEndPointsV2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface fullMenuServicen {
    @POST(RetrofitEndPointsV2.GET_ALLMENUV2)
    Call<responseMenun> getFullMenu(@Body requesMenun request);

    @POST(RetrofitEndPointsV2.GET_CATEGORY)
    Call<responselistaproducts> listaProducts(@Body requestlistproducts req);

    @POST(RetrofitEndPointsV2.GET_SINGLE_INGREDINETS)
    Call<responseIngredients> getIngredients(@Body requestIngredients request);
}
