package com.Empresa.model;

public class DetalleVenta {
    int id;
    int idVenta;
    int idProducto;
    int cantidad;
    double precioUnitario;
    double subtotal;

    public DetalleVenta(){
    }

    public DetalleVenta(int id, int idVenta, int idProducto, int cantidad, double precioUnitario, double subtotal){
        this.id=id;
        this.idVenta=idVenta;
        this.idProducto=idProducto;
        this.cantidad=cantidad;
        this.precioUnitario=precioUnitario;
        this.subtotal=subtotal;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }

    public int getIdVenta(){
        return idVenta;
    }

    public void setIdVenta(int idVenta){
        this.idVenta=idVenta;
    }

    public int getIdProducto(){
        return idProducto;
    }

    public void setIdProducto(int idProducto){
        this.idProducto=idProducto;
    }

    public int getCantidad(){
        return cantidad;
    }

    public void setCantidad(int cantidad){
        this.cantidad=cantidad;
    }

    public double getPrecioUnitario(){
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario){
        this.precioUnitario=precioUnitario;
    }

    public double getSubtotal(){
        return subtotal;
    }

    public void setSubtotal(double subtotal){
        this.subtotal=subtotal;
    }
}
