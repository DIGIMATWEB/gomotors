package com.gomotorscompany.gomotors.enprodresodetail.presenter;

import android.content.Context;

import com.gomotorscompany.gomotors.enprodresodetail.interactor.progresdetailInteractor;
import com.gomotorscompany.gomotors.enprodresodetail.interactor.progresdetailInteractorImpl;
import com.gomotorscompany.gomotors.enprodresodetail.view.progresdetailView;

public class progresdetailpresenterImpl implements progresdetailpresenter {
    private Context context;
    private progresdetailView view;
    private progresdetailInteractor interactor;

    public progresdetailpresenterImpl(progresdetailView view,Context context)
    {
        this.view=view;
        this.context=context;
        this.interactor=new progresdetailInteractorImpl(this,context);
    }

    @Override
    public void changeStatus(String clientid, int idorder, int status) {
        if(view!=null)
        {
            interactor.changeStatus(clientid,idorder,status);
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
    public void setposition(double latitude, double longitude) {
        if(view!=null)
        {
           // Toast.makeText(context, "actualizando ubicacion", Toast.LENGTH_SHORT).show();
            interactor.setnewposition(latitude,longitude);
        }
    }

    @Override
    public void succesChangeStatus(int code,int status) {
        if(view!=null)
        {
            view.setChangeStatus(code,status);
        }
    }

    @Override
    public void showprogresdialog() {
        if(view!=null)
        {
            view.showprogresdialog();
        }
    }

    @Override
    public void hideprogresdialog() {
            if(view!=null)
            {
                view.hideprogresdialog();
            }
    }
}
