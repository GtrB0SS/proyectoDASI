/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.database2docker.model;

/**
 *
 * @author Carlos
 */
public class Alumno {
    private String expediente;
    private String nombre;
    private String dni;
    private String email;

    public Alumno(String expediente, String nombre, String dni, String email) {
        this.expediente = expediente;
        this.nombre = nombre;
        this.dni = dni;
        this.email = email;
    }

    public Alumno() {
    }

    public String getExpediente() {
        return expediente;
    }

    public void setExpediente(String expediente) {
        this.expediente = expediente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
