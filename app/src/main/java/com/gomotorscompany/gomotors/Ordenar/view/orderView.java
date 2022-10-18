package com.gomotorscompany.gomotors.Ordenar.view;

import com.gomotorscompany.gomotors.Ordenar.model.ingredientesAlone.dataIngredients;
import com.gomotorscompany.gomotors.Ordenar.model.newmenu.Sucursale;
import com.gomotorscompany.gomotors.Ordenar.model.newmenu.menuDatan;

import java.util.List;

public interface orderView {
    void setCompaniastoView(List<menuDatan> data, List<Sucursale> sucursales);
    void setCategoriasMenu(List<String> categoriasDisponibles);
    void showProgressDialog();
    void hideProgressDialog();


    void igredientesIndividuales(List<dataIngredients> data);
}
