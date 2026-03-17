package com.Empresa.model;

public class Producto {
    int id;
    String codigoBarras;
    String nombre;
    String descripcion;
    double precioCompra;
    double precioVenta;
    int stockActual;
    int stockMinimo;
    Integer idCategoria;
    String fechaRegistro;

    public Producto(){
    }

    public Producto(int id, String codigoBarras, String nombre, String descripcion, double precioCompra, double precioVenta, int stockActual, int stockMinimo, Integer idCategoria, String fechaRegistro){
        this.id=id;
        this.codigoBarras=codigoBarras;
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.precioCompra=precioCompra;
        this.precioVenta=precioVenta;
        this.stockActual=stockActual;
        this.stockMinimo=stockMinimo;
        this.idCategoria=idCategoria;
        this.fechaRegistro=fechaRegistro;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }

    public String getCodigoBarras(){
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras){
        this.codigoBarras=codigoBarras;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre=nombre;
    }

    public String getDescripcion(){
        return descripcion;
    }

    public void setDescripcion(String descripcion){
        this.descripcion=descripcion;
    }

    public double getPrecioCompra(){
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra){
        this.precioCompra=precioCompra;
    }

    public double getPrecioVenta(){
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta){
        this.precioVenta=precioVenta;
    }

    public int getStockActual(){
        return stockActual;
    }

    public void setStockActual(int stockActual){
        this.stockActual=stockActual;
    }

    public int getStockMinimo(){
        return stockMinimo;
    }

    public void setStockMinimo(int stockMinimo){
        this.stockMinimo=stockMinimo;
    }

    public Integer getIdCategoria(){
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria){
        this.idCategoria=idCategoria;
    }

    public String getFechaRegistro(){
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro){
        this.fechaRegistro=fechaRegistro;
    }
}
