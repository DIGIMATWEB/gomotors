package com.gomotorscompany.gomotors.Ordenar.model.ingredientesAlone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class dataIngredients {

    @SerializedName("ingrediente_id")
    @Expose
    private String ingredienteId;
    @SerializedName("name_ingrediente")
    @Expose
    private String nameIngrediente;

    private Boolean checked;
    public dataIngredients(String ingredienteId, String nameIngrediente,Boolean checked) {
        super();
        this.ingredienteId = ingredienteId;
        this.nameIngrediente = nameIngrediente;
        this.checked=checked;
    }

    public String getIngredienteId() {
        return ingredienteId;
    }

    public void setIngredienteId(String ingredienteId) {
        this.ingredienteId = ingredienteId;
    }

    public String getNameIngrediente() {
        return nameIngrediente;
    }

    public void setNameIngrediente(String nameIngrediente) {
        this.nameIngrediente = nameIngrediente;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}
