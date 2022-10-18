package com.gomotorscompany.gomotors.Ordenar.presenter;

import com.gomotorscompany.gomotors.Ordenar.model.ingredientesAlone.dataIngredients;
import com.gomotorscompany.gomotors.Ordenar.model.newmenu.Sucursale;
import com.gomotorscompany.gomotors.Ordenar.model.newmenu.menuDatan;

import java.util.List;

public interface mainpresenterView {
    void requestLocales();
    void requestProductos();

    void showProgressDialog();
    void hideProgressDialog();

    void setComercios(List<menuDatan> data, List<Sucursale> sucursales);

    void setCategorias(List<String> categoriasDisponibles);

    void getComplementosalone();

    void setIngredientesIndividuales(List<dataIngredients> data);
}
