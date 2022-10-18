package com.gomotorscompany.gomotors.enprogreso.util;

import com.gomotorscompany.gomotors.enprodresodetail.model.repartidor.requetsliberar;
import com.gomotorscompany.gomotors.enprodresodetail.model.repartidor.responseLiberar;
import com.gomotorscompany.gomotors.enprogreso.model.chekpending.requestpending;
import com.gomotorscompany.gomotors.enprogreso.model.chekpending.responsependientes;
import com.gomotorscompany.gomotors.enprogreso.model.responseNewLocationDriver;
import com.gomotorscompany.gomotors.enprogreso.model.setNewlocationDriver;
import com.gomotorscompany.gomotors.miscompras.model.get.requestGetOrder;
import com.gomotorscompany.gomotors.miscompras.model.get.responseGetOrder;
import com.gomotorscompany.gomotors.pedido.model.others.responseAsignaciondelaOrden;
import com.gomotorscompany.gomotors.pedido.model.others.setAginaciondeOrdenes;
import com.gomotorscompany.gomotors.retrofit.RetrofitEndPointsV2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface servicegetOrders {
    @POST(RetrofitEndPointsV2.GET_ORDERS)
    Call<responseGetOrder> getFullOrders(@Body requestGetOrder request);

    @POST(RetrofitEndPointsV2.SET_LOCATION)
    Call<responseNewLocationDriver> setLocationNew(@Body setNewlocationDriver request);
    @POST(RetrofitEndPointsV2.SET_LIBERARREPARTIDOR)
    Call<responseLiberar> requestLiberar(@Body requetsliberar requets);

    @POST(RetrofitEndPointsV2.GET_REPARTIDORESCERCANOS)
    Call<responsependientes> checkpendings(@Body requestpending request);
    @POST(RetrofitEndPointsV2.ASIGN_REPARTIDOR)
    Call<responseAsignaciondelaOrden> setReapartidorToOrder(@Body setAginaciondeOrdenes request);
}
