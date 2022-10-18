package com.gomotorscompany.gomotors.pedido.view;

import com.gomotorscompany.gomotors.Zonas.model.dataRapartidores;
import com.gomotorscompany.gomotors.miscompras.model.get.datagetOrders;
import com.gomotorscompany.gomotors.pedido.model.repartidores.dataRepartidores;

import java.util.List;

public interface pedidoView {
    void setDataToView(List<datagetOrders> data);

    void setRepartidores(List<dataRepartidores> data);

    void setFullRepartidores(List<dataRapartidores> repartidoresAll);

    void codeAsignacion(int code);

    void setDialogAsignando();
    void setDialogsinOrdenes();


    void hideprogresdialog();

    void showprogresdialog();
}
