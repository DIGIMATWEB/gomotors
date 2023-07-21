package com.gomotorscompany.gomotors.Login.interactor;

public interface LoginInteractor {
    void requestLogin(String telephone,String pass );

    void checkStatus(String token);
}
