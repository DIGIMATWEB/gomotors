package com.gomotorscompany.gomotors.miscompras.presenter;

import android.content.Context;

import com.gomotorscompany.gomotors.miscompras.interactor.misComprasInteractor;
import com.gomotorscompany.gomotors.miscompras.interactor.misComprasInteractorImpl;
import com.gomotorscompany.gomotors.miscompras.model.get.datagetOrders;
import com.gomotorscompany.gomotors.miscompras.model.set.requeststartOrder;
import com.gomotorscompany.gomotors.miscompras.view.miscomprasView;

import java.util.List;

public class presenterStartOrderImpl implements presenterStartOrderInterface{
    private miscomprasView view;
    private Context context;
    private misComprasInteractor interactor;

    public presenterStartOrderImpl( miscomprasView view,Context context)
    {
        this.view=view;
        this.context=context;
        this.interactor=new misComprasInteractorImpl(this,context);
    }

    @Override
    public void requestStratOrder(requeststartOrder startOrder) {
        if(view!=null)
        {
            interactor.requestCompras(startOrder);
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
    public void requestlasOrders() {
        if(view!=null)
        {
            interactor.requestOrders();
        }
    }

    @Override
    public void setordenCreadaSucces(String s) {
        if(view!=null)
        {
            view.setSuccesOrderCreated(s);
        }
    }

    @Override
    public void mandarACocina(Integer ordenNum) {
        if(view!=null)
        {
            interactor.mandarACocina(ordenNum);
        }
    }

    @Override
    public void gotoreparto() {
        if(view!=null)
        {
            view.succesGotoReparto();
        }
    }

    @Override
    public void hideprogresdialog() {
        if(view!=null) {
            view.hideProgresDialog();
        }
    }

    @Override
    public void showprogresdialog() {
        if(view!=null) {
            view.showprogresDialog();
        }
    }

    @Override
    public void didnoSucced() {
        if(view!=null) {
            view.didnotsucced();
        }
    }
}
