package com.gomotorscompany.gomotors.miscompras.model.set.cocina;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class requestCocina {

    @SerializedName("id_orden")
    @Expose
    private Integer idOrden;

    public requestCocina(Integer idOrden) {
        super();
        this.idOrden = idOrden;
    }

    public Integer getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(Integer idOrden) {
        this.idOrden = idOrden;
    }

}