package com.Empresa.model;

public class Usuario {
    int id;
    String username;
    String password;
    String rol;
    String fechaCreacion;

    public Usuario(){
    }

    public Usuario(int id, String username, String password, String rol, String fechaCreacion){
        this.id=id;
        this.username=username;
        this.password=password;
        this.rol=rol;
        this.fechaCreacion=fechaCreacion;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username=username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password=password;
    }

    public String getRol(){
        return rol;
    }

    public void setRol(String rol){
        this.rol=rol;
    }

    public String getFechaCreacion(){
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion){
        this.fechaCreacion=fechaCreacion;
    }
}
