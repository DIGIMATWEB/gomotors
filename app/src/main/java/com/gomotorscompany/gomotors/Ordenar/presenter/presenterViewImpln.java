package com.gomotorscompany.gomotors.Ordenar.presenter;

import android.content.Context;

import com.gomotorscompany.gomotors.Ordenar.interactor.getfullMenuInteractor;
import com.gomotorscompany.gomotors.Ordenar.interactor.getfullMenuInteractorImplementsn;
import com.gomotorscompany.gomotors.Ordenar.model.newmenu.menuDatan;
import com.gomotorscompany.gomotors.Ordenar.view.orderViewn;

import java.util.List;

public class presenterViewImpln implements  mainpresenterViewn{
    private Context context;
    private orderViewn view;
    private getfullMenuInteractor interactor;
    public presenterViewImpln(orderViewn view ,Context context)
    {
        this.context = context;
        this.view = view;
        interactor = new getfullMenuInteractorImplementsn(this,context);
    }
    @Override
    public void requestLocales() {
        if(view!=null) {
            interactor.requestFullData();
        }

    }

    @Override
    public void showProgressDialog() {
        if(view!=null) {
            view.showProgressDialog();
        }
    }

    @Override
    public void hideProgressDialog() {
        if(view!=null) {
            view.hideProgressDialog();
        }
    }

    @Override
    public void setComercios(List<menuDatan> data) {
        if(view!=null) {
            view.setCompaniastoView(data);
        }
    }
}