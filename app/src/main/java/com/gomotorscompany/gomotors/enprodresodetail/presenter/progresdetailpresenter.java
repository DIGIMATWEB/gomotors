package com.gomotorscompany.gomotors.enprodresodetail.presenter;

public interface progresdetailpresenter {
    void changeStatus(String clientid,int idorder,int status);
    void liberarRepartidor();

    void setposition(double latitude, double longitude);
    void succesChangeStatus(int code);

    void showprogresdialog();
    void hideprogresdialog();
}
