/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upsa.backenddakar.model;

import java.sql.Date;

/**
 *
 * @author Carlos
 */
public class Etapa {
    private String idEtapa;
    private Date fecha;
    private int recorrido;

    public Etapa() {
    }

    public Etapa(String idEtapa, Date fecha, int recorrido) {
        this.idEtapa = idEtapa;
        this.fecha = fecha;
        this.recorrido = recorrido;
    }

    public String getIdEtapa() {
        return idEtapa;
    }

    public void setIdEtapa(String idEtapa) {
        this.idEtapa = idEtapa;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getRecorrido() {
        return recorrido;
    }

    public void setRecorrido(int recorrido) {
        this.recorrido = recorrido;
    }
    
    
}
