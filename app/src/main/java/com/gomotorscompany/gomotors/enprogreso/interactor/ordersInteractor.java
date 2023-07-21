package com.gomotorscompany.gomotors.enprogreso.interactor;

public interface ordersInteractor {
    void requesAllOrders();

    void setpositionDriver(double latitude, double longitude);

    void liberarRepartidor();

    void checkencola();

    void asignOrderRepartidor(int ordenNum);

    void checkStatus();
}
