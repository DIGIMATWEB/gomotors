package com.gomotorscompany.gomotors.miscompras.presenter;

import com.gomotorscompany.gomotors.miscompras.model.get.datagetOrders;
import com.gomotorscompany.gomotors.miscompras.model.set.requeststartOrder;

import java.util.List;

public interface presenterStartOrderInterface {
    void requestStratOrder(requeststartOrder startOrder);
    void setdatatoView(List<datagetOrders> data);
    void requestlasOrders();

    void setordenCreadaSucces(String s);

    void mandarACocina(Integer ordenNum);

    void gotoreparto();

    void hideprogresdialog();

    void showprogresdialog();

    void didnoSucced();
}
