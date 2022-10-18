package com.gomotorscompany.gomotors.miscompras.view;

import com.gomotorscompany.gomotors.miscompras.model.get.datagetOrders;

import java.util.List;

public interface miscomprasView {
    void setOreders(List<datagetOrders> data);
    void showprogresDialog();
    void hideProgresDialog();

    void setSuccesOrderCreated(String s);

    void succesGotoReparto();

    void didnotsucced();
}
