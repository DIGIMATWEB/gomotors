package com.gomotorscompany.gomotors.Zonas.interactor;

import com.gomotorscompany.gomotors.Zonas.model.Zona;

import java.util.List;

public interface zonesInteractor {
     void requesZones();

    void updateZone(List<Zona> zonas);

    void eraseZone(int id);
//
//    void requesEmployes();
    //void updateZones();
    //void requestRepartidores();
}
