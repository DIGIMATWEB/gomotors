package com.gomotorscompany.gomotors.Login.presenter;

import com.gomotorscompany.gomotors.Login.view.LoginViewImpl;

public interface LoginPresenter {
    void setView(LoginViewImpl view);
    void loginRequest(String telephone,String pass );

    void succes();

    void hideDialog();

    void showDialog();

    void checkStatus(String token);

    void succesLogin();
}
