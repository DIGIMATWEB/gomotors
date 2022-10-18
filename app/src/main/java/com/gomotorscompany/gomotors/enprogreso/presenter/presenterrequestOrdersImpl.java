package com.gomotorscompany.gomotors.enprogreso.presenter;

import android.content.Context;

import com.gomotorscompany.gomotors.enprogreso.interactor.ordersInteractor;
import com.gomotorscompany.gomotors.enprogreso.interactor.ordersInteractorImpl;
import com.gomotorscompany.gomotors.enprogreso.model.chekpending.dataorderspending;
import com.gomotorscompany.gomotors.enprogreso.view.enprogresoView;
import com.gomotorscompany.gomotors.miscompras.model.get.datagetOrders;

import java.util.List;

public class presenterrequestOrdersImpl implements presenterrequestOrders {
    private enprogresoView view;
    private Context context;
    private ordersInteractor interactor;
    public presenterrequestOrdersImpl(enprogresoView view,Context context)
    {
        this.view   =view;
        this.context=context;
        this.interactor= new ordersInteractorImpl(this,context);
    }

    @Override
    public void requestOrders() {
        if(view!=null)
        {
            interactor.requesAllOrders();
        }
    }

    @Override
    public void setdatatoView(List<datagetOrders> data) {
        if(view!=null)
        {
            view.setOreders(data);
        }
    }
    @Override
    public void liberarRepartidor() {
        if(view!=null)
        {
            interactor.liberarRepartidor();
        }
    }

    @Override
    public void checkEncola() {
        if(view!=null)
        {
            interactor.checkencola();
        }
    }
    @Override
    public void setOrdertorapartidor( int ordenNum) {
        if(view!=null)
        {
            interactor.asignOrderRepartidor(ordenNum);
        }
    }

    @Override
    public void succesAsignRepartidor(int code) {
        if(view!=null)
        {
            view.succesAsignarRepartidor(code);
        }
    }

    @Override
    public void setPendingsToview(List<dataorderspending> data) {
        if(view!=null)
        {
            view.setOrdenesPendientes(data);
        }
    }


    @Override
    public void setposition(double latitude, double longitude) {
        if(view!=null)
        {
            interactor.setpositionDriver(latitude,longitude);
        }
    }

    @Override
    public void hideprogresdialog() {
        if(view!=null)
        {
            view.hideprogresdialog();
        }
    }

    @Override
    public void showprogresdialog() {
        if(view!=null)
        {
            view.showprogresdialog();
        }
    }
}
