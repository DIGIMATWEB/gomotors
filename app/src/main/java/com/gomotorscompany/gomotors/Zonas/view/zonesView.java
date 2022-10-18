package com.gomotorscompany.gomotors.Zonas.view;

import com.gomotorscompany.gomotors.Zonas.model.dataRapartidores;
import com.gomotorscompany.gomotors.Zonas.model.datarequesZonas;

import java.util.List;

public interface zonesView {
    void setZones( List<datarequesZonas> data);
    void showProgressDialog();
    void hideProgressDialog();

    void reloadScreen();

    void setDrivers(List<dataRapartidores> data);
}
