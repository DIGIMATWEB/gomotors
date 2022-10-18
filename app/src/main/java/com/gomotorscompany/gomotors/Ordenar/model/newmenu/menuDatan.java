package com.gomotorscompany.gomotors.Ordenar.model.newmenu;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class menuDatan {

    @SerializedName("nombre_empresa")
    @Expose
    private String nombreEmpresa;
    @SerializedName("Logo")
    @Expose
    private List<Logo> logo = null;

    public menuDatan(String nombreEmpresa, List<Logo> logo) {
        super();
        this.nombreEmpresa = nombreEmpresa;
        this.logo = logo;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public List<Logo> getLogo() {
        return logo;
    }

    public void setLogo(List<Logo> logo) {
        this.logo = logo;
    }
}