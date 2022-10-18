package com.gomotorscompany.gomotors.Ordenar.presenter;

import com.gomotorscompany.gomotors.Ordenar.model.newmenu.menuDatan;

import java.util.List;

public interface mainpresenterViewn {
    void requestLocales();

    void showProgressDialog();
    void hideProgressDialog();

    void setComercios(List<menuDatan> data);
}
