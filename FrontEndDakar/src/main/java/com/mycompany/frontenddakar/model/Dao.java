/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.frontenddakar.model;

import java.util.List;

/**
 *
 * @author albertogarciacampo
 */
public interface Dao {
    
    public List<Resultado> selectResultados();
    public List<Vehiculo> selectVehiculos();
    public List<Etapa> selectEtapas();
    
    
    public Vehiculo selectVehiculo(String idVehiculo);
    public Etapa selectEtapa(String idEtapa);
}
