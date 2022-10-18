package com.gomotorscompany.gomotors.miscompras.interactor;

import com.gomotorscompany.gomotors.miscompras.model.set.requeststartOrder;

public interface misComprasInteractor {
    void requestCompras(requeststartOrder startOrder);

    void requestOrders();

    void mandarACocina(Integer ordenNum);
}
