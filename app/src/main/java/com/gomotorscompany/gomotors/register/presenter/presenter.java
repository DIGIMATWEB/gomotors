package com.gomotorscompany.gomotors.register.presenter;

public interface presenter {
    void requestRegister(String name, String apellido, String correo, String telefono, String fecha, String contraseña, int mtoogle);
    void responseRegister(int response);


}
