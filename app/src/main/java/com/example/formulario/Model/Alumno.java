package com.example.formulario.Model;

import java.io.Serializable;

public class Alumno implements Serializable {
    private String nombre;
    private String apellido;
    private String fec;
    private String id;
    private String carr;
    private String numCuenta;

    public String getApellido() {
        return apellido;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setCarr(String carr) {
        this.carr = carr;
    }

    public String getCarr() {
        return carr;
    }

    public String getFec() {
        return fec;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setFec(String fec) {
        this.fec = fec;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNumCuenta() {
        return numCuenta;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNumCuenta(String numCuenta) {
        this.numCuenta = numCuenta;
    }

    public Alumno(String nombre,String numCuenta,String apellido,String fec,String carr,String id) {
        this.nombre = nombre;
        this.numCuenta=numCuenta;
        this.apellido=apellido;
        this.fec=fec;
        this.carr=carr;
        this.id=id;
    }

}
