package com.gomotorscompany.gomotors.pedido.util;

import com.gomotorscompany.gomotors.Zonas.model.requestRepartidores;
import com.gomotorscompany.gomotors.Zonas.model.responseRepartidores;
import com.gomotorscompany.gomotors.miscompras.model.get.requestGetOrder;
import com.gomotorscompany.gomotors.miscompras.model.get.responseGetOrder;
import com.gomotorscompany.gomotors.pedido.model.others.responseAsignaciondelaOrden;
import com.gomotorscompany.gomotors.pedido.model.others.setAginaciondeOrdenes;
import com.gomotorscompany.gomotors.pedido.model.repartidores.requestRepartidoresCercanos;
import com.gomotorscompany.gomotors.pedido.model.repartidores.responseRepartidoresCercanos;
import com.gomotorscompany.gomotors.retrofit.RetrofitEndPointsV2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface servicePedido {
    @POST(RetrofitEndPointsV2.GET_ORDERS)
    Call<responseGetOrder> getFullOrders(@Body requestGetOrder request);
    @POST(RetrofitEndPointsV2.GET_REPARTIDORESCERCANOS)
    Call<responseRepartidoresCercanos> getRepartidoresCercanos(@Body requestRepartidoresCercanos request);
    @POST(RetrofitEndPointsV2.REQUEST_REPARTIDORES)
    Call<responseRepartidores> getRepartidores(@Body requestRepartidores request);
    @POST(RetrofitEndPointsV2.ASIGN_REPARTIDOR)
    Call<responseAsignaciondelaOrden> setReapartidorToOrder(@Body  setAginaciondeOrdenes request);


}
