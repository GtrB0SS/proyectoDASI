/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upsa.backenddakar.model;

/**
 *
 * @author Carlos
 */
public class Resultado {
    private String idResultado;
    private Etapa etapa;
    private Vehiculo vehiculo;
    private int recorrido;

    public Resultado() {
    }

    public Resultado(String idResultado, Etapa etapa, Vehiculo vehiculo, int recorrido) {
        this.idResultado = idResultado;
        this.etapa = etapa;
        this.vehiculo = vehiculo;
        this.recorrido = recorrido;
    }

    public String getIdResultado() {
        return idResultado;
    }

    public void setIdResultado(String idResultado) {
        this.idResultado = idResultado;
    }

    public Etapa getEtapa() {
        return etapa;
    }

    public void setEtapa(Etapa etapa) {
        this.etapa = etapa;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public int getRecorrido() {
        return recorrido;
    }

    public void setRecorrido(int recorrido) {
        this.recorrido = recorrido;
    }

    @Override
    public String toString() {
        return "Resultado{" + "idResultado=" + idResultado + ", etapa=" + etapa + ", vehiculo=" + vehiculo + ", recorrido=" + recorrido + '}';
    }
    
    
}
