package com.gomotorscompany.gomotors.enprodresodetail.interactor;

public interface progresdetailInteractor {
    void changeStatus(String clientid,int idorder,int status);
    void setnewposition(double latitude, double longitude);

    void liberarRepartidor();
}
