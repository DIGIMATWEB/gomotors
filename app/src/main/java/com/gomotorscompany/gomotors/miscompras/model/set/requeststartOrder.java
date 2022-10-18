package com.gomotorscompany.gomotors.miscompras.model.set;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class requeststartOrder {


        @SerializedName("id_order")
        @Expose
        private Integer idOrder;
        @SerializedName("Token")
        @Expose
        private String token;
        @SerializedName("direccion_entrega")
        @Expose
        private String direccionEntrega;
        @SerializedName("latitud")
        @Expose
        private Double latitud;
        @SerializedName("longitud")
        @Expose
        private Double longitud;
        @SerializedName("sucursal")
        @Expose
        private String sucursal;
        @SerializedName("paquetes")
        @Expose
        private List<Paquete_startOrder> paquetes = null;
        @SerializedName("productos")
        @Expose
        private List<Producto_startOrder> productos = null;
        @SerializedName("complementos")
        @Expose
        private List<Complemento_startOrder> complementos = null;

    public requeststartOrder(Integer idOrder, String token, String direccionEntrega, Double latitud, Double longitud, String sucursal,
                             List<Paquete_startOrder> paquetes, List<Producto_startOrder> productos, List<Complemento_startOrder> complementos) {
        super();
        this.idOrder = idOrder;
        this.token = token;
        this.direccionEntrega = direccionEntrega;
        this.latitud = latitud;
        this.longitud = longitud;
        this.sucursal = sucursal;
        this.paquetes = paquetes;
        this.productos = productos;
        this.complementos = complementos;
    }


        public Integer getIdOrder() {
            return idOrder;
        }

        public void setIdOrder(Integer idOrder) {
            this.idOrder = idOrder;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getDireccionEntrega() {
            return direccionEntrega;
        }

        public void setDireccionEntrega(String direccionEntrega) {
            this.direccionEntrega = direccionEntrega;
        }

        public Double getLatitud() {
            return latitud;
        }

        public void setLatitud(Double latitud) {
            this.latitud = latitud;
        }

        public Double getLongitud() {
            return longitud;
        }

        public void setLongitud(Double longitud) {
            this.longitud = longitud;
        }

        public String getSucursal() {
            return sucursal;
        }

        public void setSucursal(String sucursal) {
            this.sucursal = sucursal;
        }

        public List<Paquete_startOrder> getPaquetes() {
            return paquetes;
        }

        public void setPaquetes(List<Paquete_startOrder> paquetes) {
            this.paquetes = paquetes;
        }

        public List<Producto_startOrder> getProductos() {
            return productos;
        }

        public void setProductos(List<Producto_startOrder> productos) {
            this.productos = productos;
        }

        public List<Complemento_startOrder> getComplementos() {
            return complementos;
        }

        public void setComplementos(List<Complemento_startOrder> complementos) {
            this.complementos = complementos;
        }

    }

