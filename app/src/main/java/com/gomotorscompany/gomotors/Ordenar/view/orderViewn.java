package com.gomotorscompany.gomotors.Ordenar.view;

import com.gomotorscompany.gomotors.Ordenar.model.newmenu.menuDatan;

import java.util.List;

public interface orderViewn {
    void setCompaniastoView(List<menuDatan> data);
    void showProgressDialog();
    void hideProgressDialog();
}
