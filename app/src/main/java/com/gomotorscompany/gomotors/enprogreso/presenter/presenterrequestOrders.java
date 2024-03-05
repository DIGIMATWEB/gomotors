package com.gomotorscompany.gomotors.enprogreso.presenter;

import com.gomotorscompany.gomotors.enprogreso.model.chekpending.dataorderspending;
import com.gomotorscompany.gomotors.miscompras.model.get.datagetOrders;

import java.util.List;

public interface presenterrequestOrders {
    void requestOrders();
    void setdatatoView(List<datagetOrders> data);


    void setposition(Double latitude, Double longitude);
    void hideprogresdialog();

    void showprogresdialog();

    void liberarRepartidor();

    void checkEncola();

    void setPendingsToview(List<dataorderspending> data);

    void setOrdertorapartidor(int idorder);

    void succesAsignRepartidor(int code);

    void checkStatus();

    void endSession();
}
