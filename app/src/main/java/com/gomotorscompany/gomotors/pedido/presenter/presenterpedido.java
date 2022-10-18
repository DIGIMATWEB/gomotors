package com.gomotorscompany.gomotors.pedido.presenter;

import com.gomotorscompany.gomotors.Zonas.model.dataRapartidores;
import com.gomotorscompany.gomotors.miscompras.model.get.datagetOrders;
import com.gomotorscompany.gomotors.pedido.model.repartidores.dataRepartidores;

import java.util.List;

public interface presenterpedido {
    void requestOrder();
    void setdatatoView(List<datagetOrders> data);

    void repartidoresmasCercanos();

    void setRepartidoresCercanos(List<dataRepartidores> data);

    void requestFullRepartidores();

    void setRepartidores(List<dataRapartidores> repartidoresAll);

    void setOrdertorapartidor(String token, Integer ordenNum);

    void succesAsignRepartidor(int code);

    void setDialog();

    void showprogresdialog();
    void hideprogresdialog();

    void matrix();
}
