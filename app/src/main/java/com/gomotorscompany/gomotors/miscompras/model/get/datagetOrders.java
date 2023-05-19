package com.gomotorscompany.gomotors.miscompras.model.get;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class datagetOrders implements Serializable{// implements Serializable

    // "status_semaforo": 2,
//      "descripcion_semaforo": "En
//      Cola",
    @SerializedName("id_order")
    @Expose
    private Integer ordenNum;
    @SerializedName("status_semaforo")
    @Expose
    private Integer semaforo;
    @SerializedName("descripcion_semaforo")
    @Expose
    private String status;

    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("sucursal")
    @Expose
    private String suc;
    @SerializedName("nombre_suc")
    @Expose
    private String namesuc;
    @SerializedName("lat_suc")
    @Expose
    private Double latsuc;
    @SerializedName("long_suc")
    @Expose
    private Double longsuc;
    @SerializedName("fecha")
    @Expose
    private String fecha;
    @SerializedName("direccion")
    @Expose
    private String direccion;
    @SerializedName("lat_client")
    @Expose
    private Double latitud;
    @SerializedName("long_client")
    @Expose
    private Double longuitud;


    @SerializedName("repartidorAsignado")
    @Expose
    private Integer idRepartidor;
    @SerializedName("NombreRepa")
    @Expose
    private String repartidor;
    @SerializedName("paquetes")
    @Expose
    private List<Paquete> paquete = null;
    @SerializedName("productos_u")
    @Expose
    private List<ProductosU> productosU = null;
    @SerializedName("complementos")
    @Expose
    private List<Complemeto> complemeto = null;
    @SerializedName("telefono")
    @Expose
    private String telefono;

    public datagetOrders(Integer semaforo,String status,String token, Integer ordenNum, String fecha, String direccion, String suc, String namesuc,
                         Double latsuc,Double longsuc, Double latitud, Double longuitud,Integer idRepartidor,String repartidor, List<Paquete> paquete,
                         List<ProductosU> productosU, List<Complemeto> complemeto,String telefono) {
        super();
        this.semaforo=semaforo;
        this.status=status;
        this.token = token;
        this.ordenNum = ordenNum;
        this.fecha = fecha;
        this.direccion = direccion;
        this.suc = suc;
        this.namesuc=namesuc;
        this.latsuc=latsuc;
        this.longsuc=longsuc;
        this.latitud = latitud;
        this.longuitud = longuitud;
        this.idRepartidor=idRepartidor;
        this.repartidor=repartidor;
        this.paquete = paquete;
        this.productosU = productosU;
        this.complemeto = complemeto;
        this.telefono=telefono;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getOrdenNum() {
        return ordenNum;
    }

    public void setOrdenNum(Integer ordenNum) {
        this.ordenNum = ordenNum;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getSuc() {
        return suc;
    }

    public void setSuc(String suc) {
        this.suc = suc;
    }

    public String getNamesuc() {
        return namesuc;
    }

    public void setNamesuc(String namesuc) {
        this.namesuc = namesuc;
    }


    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLonguitud() {
        return longuitud;
    }

    public void setLonguitud(Double longuitud) {
        this.longuitud = longuitud;
    }

    public List<Paquete> getPaquete() {
        return paquete;
    }

    public void setPaquete(List<Paquete> paquete) {
        this.paquete = paquete;
    }

    public List<ProductosU> getProductosU() {
        return productosU;
    }

    public void setProductosU(List<ProductosU> productosU) {
        this.productosU = productosU;
    }

    public List<Complemeto> getComplemeto() {
        return complemeto;
    }

    public void setComplemeto(List<Complemeto> complemeto) {
        this.complemeto = complemeto;
    }
    public Integer getSemaforo() {
        return semaforo;
    }

    public void setSemaforo(Integer semaforo) {
        this.semaforo = semaforo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getIdRepartidor() {
        return idRepartidor;
    }

    public void setIdRepartidor(Integer idRepartidor) {
        this.idRepartidor = idRepartidor;
    }

    public String getRepartidor() {
        return repartidor;
    }

    public void setRepartidor(String repartidor) {
        this.repartidor = repartidor;
    }
    public Double getLatsuc() {
        return latsuc;
    }

    public void setLatsuc(Double latsuc) {
        this.latsuc = latsuc;
    }

    public Double getLongsuc() {
        return longsuc;
    }

    public void setLongsuc(Double longsuc) {
        this.longsuc = longsuc;
    }


}