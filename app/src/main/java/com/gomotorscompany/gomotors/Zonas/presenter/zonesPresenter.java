package com.gomotorscompany.gomotors.Zonas.presenter;

import com.gomotorscompany.gomotors.Zonas.model.Zona;
import com.gomotorscompany.gomotors.Zonas.model.datarequesZonas;

import java.util.List;

public interface zonesPresenter {

    void getZones();
    void setZones( List<datarequesZonas> data);

    void showProgressDialog();
    void hideProgressDialog();

    void saveDotsZone(List<Zona> zonas);
    void eraseZona(int id);
    void reloadScreen();

  //  void getEmployes();

   // void setRepartidores(List<dataRapartidores> data);
}
