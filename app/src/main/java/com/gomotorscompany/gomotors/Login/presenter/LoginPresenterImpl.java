package com.gomotorscompany.gomotors.Login.presenter;

import android.content.Context;

import com.gomotorscompany.gomotors.Login.interactor.LoginInteractor;
import com.gomotorscompany.gomotors.Login.interactor.LoginInteractorImpl;
import com.gomotorscompany.gomotors.Login.view.LoginView;
import com.gomotorscompany.gomotors.Login.view.LoginViewImpl;

public class LoginPresenterImpl implements  LoginPresenter{
    private LoginView view;
    private Context context;
    private LoginInteractor interactor;
    public LoginPresenterImpl(LoginView view,Context context)
    {
        this.view=view;
        this.context=context;
        this. interactor=new LoginInteractorImpl(this,context);
    }

    @Override
    public void setView(LoginViewImpl view) {
        this.view=view;
    }

    @Override
    public void loginRequest(String telephone,String pass ) {
        interactor.requestLogin(telephone ,pass );
    }

    @Override
    public void succes() {
        if(view!=null)
        {
            view.succesLogin();
        }
    }

    @Override
    public void hideDialog() {
        if(view!=null)
        {
            view.hideLoader();
        }
    }

    @Override
    public void showDialog() {
        if(view!=null)
        {
            view.showLoader();
        }
    }
}
