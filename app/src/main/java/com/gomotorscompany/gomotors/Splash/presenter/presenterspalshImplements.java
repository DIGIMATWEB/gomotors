package com.gomotorscompany.gomotors.Splash.presenter;

import android.content.Context;

import com.gomotorscompany.gomotors.Splash.interactor.splashInteractor;
import com.gomotorscompany.gomotors.Splash.interactor.splashInteractorImpl;
import com.gomotorscompany.gomotors.Splash.view.splashView;

public class presenterspalshImplements  implements  presentersplash{
    private splashView view;
    private Context context;
    private splashInteractor interactor;
    public presenterspalshImplements (splashView view,Context context)
    {
        this.view=view;
        this.context=context;
        interactor = new splashInteractorImpl(this,context);

    }

    @Override
    public void requestsplashConfig() {
        if(view!=null) {
            interactor.requestsplashData();
        }
    }

    @Override
    public void getAvailable() {
        if(view!=null) {
            interactor.getAvailable();
        }
    }

    @Override
    public void setDialog() {
        if(view!=null) {
            view.setDialog();
        }
    }

    @Override
    public void setMaincolor(String colorApp) {
        if(view!=null) {
            view.setColorAPP(colorApp);
        }
    }

    @Override
    public void setImageSplash(String imageapp) {
        if(view!=null) {
            view.setSplashImge(imageapp);
        }
    }

}
