package com.gomotorscompany.gomotors.enprogreso.view;

import com.gomotorscompany.gomotors.enprogreso.model.chekpending.dataorderspending;
import com.gomotorscompany.gomotors.miscompras.model.get.datagetOrders;

import java.util.List;

public interface enprogresoView {
    void setOreders(List<datagetOrders> data);

    void hideprogresdialog();

    void showprogresdialog();

    void setOrdenesPendientes(List<dataorderspending> data);

    void succesAsignarRepartidor(int code);

    void endSession();
}
