package com.gomotorscompany.gomotors.miscompras.util;

import com.gomotorscompany.gomotors.miscompras.model.get.requestGetOrder;
import com.gomotorscompany.gomotors.miscompras.model.get.responseGetOrder;
import com.gomotorscompany.gomotors.miscompras.model.set.cocina.requestCocina;
import com.gomotorscompany.gomotors.miscompras.model.set.cocina.responseCocina;
import com.gomotorscompany.gomotors.miscompras.model.set.requeststartOrder;
import com.gomotorscompany.gomotors.miscompras.model.set.response.responseStartOrder;
import com.gomotorscompany.gomotors.retrofit.RetrofitEndPointsV2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface serviceOrdersClient {
    @POST(RetrofitEndPointsV2.GET_ORDERS)
    Call<responseGetOrder> getFullOrders1(@Body requestGetOrder request);

    @Headers("Content-Type: application/json")
     @POST(RetrofitEndPointsV2.PUT_START)
        Call<responseStartOrder> putStartOrder(@Body requeststartOrder request);

    @POST(RetrofitEndPointsV2.PUT_ORDERIDONKITCHEN)
    Call<responseCocina> putIdOrder(@Body requestCocina ordenNum);
}
