package com.gomotorscompany.gomotors.Zonas.presenter;

import android.content.Context;

import com.gomotorscompany.gomotors.Zonas.interactor.interactorEmpleados;
import com.gomotorscompany.gomotors.Zonas.interactor.interactorEmpleadosInterface;
import com.gomotorscompany.gomotors.Zonas.model.dataRapartidores;
import com.gomotorscompany.gomotors.Zonas.view.zonesView;

import java.util.List;

public class empleadosPresenterImpl implements empleadorsPresenter{
      private zonesView view;
        private Context context;
        private interactorEmpleadosInterface interactor;

        public empleadosPresenterImpl(zonesView view,Context context)
    {
        this.view=view;
        this.context=context;
        this.interactor=new interactorEmpleados(this,context);

    }

    @Override
    public void getEmployes() {
        if (view != null) {
            interactor.requesEmployes();
        }
    }

    @Override
    public void setRepartidores(List<dataRapartidores> data) {
        if (view != null) {
            view.setDrivers(data);
        }
    }
}
