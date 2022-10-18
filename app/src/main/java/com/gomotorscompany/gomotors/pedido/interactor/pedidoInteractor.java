package com.gomotorscompany.gomotors.pedido.interactor;

public interface pedidoInteractor {
    void requestAllpedidos();

    void requestRepartidoresMasCercanos();

    void requestAllRepartidores();

    void asignOrderRepartidor(String token, Integer ordenNum);

    void domatrix();
}
