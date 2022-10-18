package com.gomotorscompany.gomotors.pedido.presenter;

import android.content.Context;

import com.gomotorscompany.gomotors.Zonas.model.dataRapartidores;
import com.gomotorscompany.gomotors.miscompras.model.get.datagetOrders;
import com.gomotorscompany.gomotors.pedido.interactor.pedidoInteractor;
import com.gomotorscompany.gomotors.pedido.interactor.pedidoInteractorImpl;
import com.gomotorscompany.gomotors.pedido.model.repartidores.dataRepartidores;
import com.gomotorscompany.gomotors.pedido.view.pedidoView;

import java.util.List;

public class presenterPedidoImpl  implements presenterpedido{
    private pedidoView view;
    private Context contex;
    private pedidoInteractor interactor;
    public  presenterPedidoImpl(pedidoView view,Context contex)
    {
        this.view=view;
        this.contex=contex;
        this.interactor= new pedidoInteractorImpl(this,contex);
    }
    @Override
    public void requestOrder() {
        if(view!=null)
        {
            interactor.requestAllpedidos();
        }
    }

    @Override
    public void repartidoresmasCercanos() {
        if(view!=null)
        {
            interactor.requestRepartidoresMasCercanos();
        }
    }

    @Override
    public void setRepartidoresCercanos(List<dataRepartidores> data) {
        if(view!=null)
        {
            view.setRepartidores(data);
        }
    }

    @Override
    public void requestFullRepartidores() {
        if(view!=null)
        {
            interactor.requestAllRepartidores();
        }
    }

    @Override
    public void setRepartidores(List<dataRapartidores> repartidoresAll) {
        if(view!=null)
        {
            view.setFullRepartidores(repartidoresAll);
        }
    }

    @Override
    public void setOrdertorapartidor(String token, Integer ordenNum) {
        if(view!=null)
        {
            interactor.asignOrderRepartidor(token,ordenNum);
        }
    }

    @Override
    public void succesAsignRepartidor(int code) {
        if(view!=null)
        {
            view.codeAsignacion(code);
        }
    }

    @Override
    public void setDialog() {
        if(view!=null)
        {
            view.setDialogsinOrdenes();
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

    @Override
    public void matrix() {
        if(view!=null)
        {
            interactor.domatrix();
        }
    }

    @Override
    public void setdatatoView(List<datagetOrders> data) {
        if(view!=null)
        {
            view.setDataToView(data);
        }
    }

}
