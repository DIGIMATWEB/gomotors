package com.gomotorscompany.gomotors.Ordenar.presenter;

import android.content.Context;

import com.gomotorscompany.gomotors.Ordenar.interactor.getfullMenuInteractor;
import com.gomotorscompany.gomotors.Ordenar.interactor.getfullMenuInteractorImplements;
import com.gomotorscompany.gomotors.Ordenar.model.ingredientesAlone.dataIngredients;
import com.gomotorscompany.gomotors.Ordenar.model.newmenu.Sucursale;
import com.gomotorscompany.gomotors.Ordenar.model.newmenu.menuDatan;
import com.gomotorscompany.gomotors.Ordenar.view.orderView;

import java.util.List;

public class presenterViewImpl implements  mainpresenterView{
    private Context context;
    private orderView view;
    private getfullMenuInteractor interactor;
    public presenterViewImpl(orderView view ,Context context)
    {
        this.context = context;
        this.view = view;
        interactor = new getfullMenuInteractorImplements(this,context);
    }
    @Override
    public void requestLocales() {
        if(view!=null) {
            interactor.requestFullData();
        }

    }

    @Override
    public void requestProductos() {
        if(view!=null) {
            interactor.requestProductos();
        }
    }

    @Override
    public void getComplementosalone() {
        if(view!=null) {
            interactor.requestaloneComplements();
        }
    }

    @Override
    public void setIngredientesIndividuales(List<dataIngredients> data) {
        if(view!=null) {
            view.igredientesIndividuales(data);
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
    public void setComercios(List<menuDatan> data, List<Sucursale> sucursales) {
        if(view!=null) {
            view.setCompaniastoView(data,sucursales);
        }
    }

    @Override
    public void setCategorias(List<String> categoriasDisponibles) {
        if(view!=null) {
            view.setCategoriasMenu(categoriasDisponibles);
        }
    }

}
