package com.gomotorscompany.gomotors.enprogreso.interactor;

public interface ordersInteractor {
    void requesAllOrders();

    void setpositionDriver(Double latitude, Double longitude);

    void liberarRepartidor();

    void checkencola();

    void asignOrderRepartidor(int ordenNum);

    void checkStatus();
}
