package com.Empresa.model;

public class Venta {
    int id;
    int idUsuario;
    String fechaVenta;
    double totalVenta;
    String metodoPago;

    public Venta(){
    }

    public Venta(int id, int idUsuario, String fechaVenta, double totalVenta, String metodoPago){
        this.id=id;
        this.idUsuario=idUsuario;
        this.fechaVenta=fechaVenta;
        this.totalVenta=totalVenta;
        this.metodoPago=metodoPago;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }

    public int getIdUsuario(){
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario){
        this.idUsuario=idUsuario;
    }

    public String getFechaVenta(){
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta){
        this.fechaVenta=fechaVenta;
    }

    public double getTotalVenta(){
        return totalVenta;
    }

    public void setTotalVenta(double totalVenta){
        this.totalVenta=totalVenta;
    }

    public String getMetodoPago(){
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago){
        this.metodoPago=metodoPago;
    }
}
