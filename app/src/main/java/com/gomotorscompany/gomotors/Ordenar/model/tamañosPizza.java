package com.gomotorscompany.gomotors.Ordenar.model;

public class tamañosPizza {
    private String tamaño;
    private Boolean ischecke;


    public tamañosPizza(String tamaño, Boolean ischecke) {
        this.tamaño = tamaño;
        this.ischecke = ischecke;
    }

    public String getTamaño() {
        return tamaño;
    }

    public void setTamaño(String tamaño) {
        this.tamaño = tamaño;
    }

    public Boolean getIschecke() {
        return ischecke;
    }

    public void setIschecke(Boolean ischecke) {
        this.ischecke = ischecke;
    }
}
