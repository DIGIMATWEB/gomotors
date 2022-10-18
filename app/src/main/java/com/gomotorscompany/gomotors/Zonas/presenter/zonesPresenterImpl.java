package com.gomotorscompany.gomotors.Zonas.presenter;

import android.content.Context;

import com.gomotorscompany.gomotors.Zonas.interactor.zonesInteractor;
import com.gomotorscompany.gomotors.Zonas.interactor.zonesInteractorImpl;
import com.gomotorscompany.gomotors.Zonas.model.Zona;
import com.gomotorscompany.gomotors.Zonas.model.datarequesZonas;
import com.gomotorscompany.gomotors.Zonas.view.zonesView;

import java.util.List;

public class zonesPresenterImpl implements zonesPresenter {
    private zonesView view;
    private Context context;
    private zonesInteractor interactor;
    public zonesPresenterImpl(zonesView view,Context context)
    {
        this.view=view;
        this.context=context;
        this.interactor=new zonesInteractorImpl(this,context);

    }
    @Override
    public void getZones() {
        if (view != null) {
        interactor.requesZones();
        }
    }

//    @Override
//    public void getEmployes() {
//        if (view != null) {
//            interactor.requesEmployes();
//        }
//    }
//
//    @Override
//    public void setRepartidores(List<dataRapartidores> data) {
//        if (view != null) {
//            view.setDrivers(data);
//        }
//    }

    @Override
    public void setZones( List<datarequesZonas> data) {
        if (view != null) {
            view.setZones(data);
        }
    }

    @Override
    public void showProgressDialog() {
        if (view != null) {
            view.showProgressDialog();
        }

    }

    @Override
    public void hideProgressDialog() {
        if (view != null) {
            view.hideProgressDialog();
        }
    }

    @Override
    public void saveDotsZone(List<Zona> zonas) {
        if (view != null) {
        interactor.updateZone(zonas);
        }
    }

    @Override
    public void eraseZona(int id) {
        if (view != null) {
            interactor.eraseZone(id);
        }
    }

    @Override
    public void reloadScreen() {
        if (view != null) {
            view.reloadScreen();
        }
    }


}
