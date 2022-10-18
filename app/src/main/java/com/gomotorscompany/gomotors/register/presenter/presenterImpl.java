package com.gomotorscompany.gomotors.register.presenter;

import android.content.Context;

import com.gomotorscompany.gomotors.register.interactor.registerInteractor;
import com.gomotorscompany.gomotors.register.interactor.registerInteractorImpl;
import com.gomotorscompany.gomotors.register.view.viewregister;

public class presenterImpl  implements presenter{
    private Context context;
    private viewregister view;
    private registerInteractor interactor;

    public presenterImpl(viewregister view, Context context) {
        this.view=view;
        this.context=context;
        interactor= new registerInteractorImpl(this,context);
    }

    @Override
    public void requestRegister(String name, String apellido, String correo, String telefono, String fecha, String contraseña, int mtoogle) {
        if(view!=null)
        {
            interactor.requestRegister(name,apellido,correo,telefono,fecha,contraseña,mtoogle);
        }
    }

    @Override
    public void responseRegister(int response) {
        if(view!=null)
        {
            view.setresponseRegister(response);
        }
    }
}
