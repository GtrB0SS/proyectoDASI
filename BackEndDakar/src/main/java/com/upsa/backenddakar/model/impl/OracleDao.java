/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upsa.backenddakar.model.impl;

import com.upsa.backenddakar.exceptions.AppException;
import com.upsa.backenddakar.exceptions.SqlAppException;
import com.upsa.backenddakar.model.Dao;
import com.upsa.backenddakar.model.Etapa;
import com.upsa.backenddakar.model.Resultado;
import com.upsa.backenddakar.model.Vehiculo;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 *
 * @author Carlos
 */
public class OracleDao implements Dao{
    
    @Resource(lookup = "java:app/jdbc/database")
    private DataSource dataSource;

    @Override
    public List<Resultado> selectResultados() throws AppException{
        
        List<Resultado> resultados = new LinkedList<>();
        
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT R.ID_RESULTADO, R.RECORRIDO, V.ID_VEHICULO, V.NOMBRE_EQUIPO, V.TIPO, V.POTENCIA, V.PILOTO, V.COPILOTO, V.CLASIFICACION, E.ID_ETAPA, E.FECHA, E.RECORRIDO " + 
                                                          "  FROM RESULTADO R, VEHICULO V, ETAPA E "
                     + "                                     WHERE R.ID_ETAPA = E.ID_ETAPA "
                     + "                                     AND   R.ID_VEHICULO = V.ID_VEHICULO                              ")
            )
        {
            if ( resultSet.next() )
            {
                do
                {
                    Vehiculo v = new Vehiculo();
                    v.setIdVehiculo(resultSet.getString(3));
                    v.setNombreEquipo(resultSet.getString(4));
                    v.setTipo(resultSet.getString(5));
                    v.setPotencia(resultSet.getInt(6));
                    v.setPiloto(resultSet.getString(7));
                    v.setCopiloto(resultSet.getString(8));
                    v.setClasificacion(resultSet.getString(9));
                    
                    Etapa e = new Etapa();
                    e.setIdEtapa(resultSet.getString(10));
                    e.setFecha(resultSet.getString(11));
                    e.setRecorrido(resultSet.getInt(12));
                    
                    Resultado resultado = new Resultado();
                    resultado.setIdResultado(resultSet.getString(1));
                    resultado.setRecorrido(resultSet.getInt(2));
                    resultado.setEtapa(e);
                    resultado.setVehiculo(v);
                    
                    resultados.add(resultado);
                } while ( resultSet.next() );
            }
        } catch (SQLException sqlException)
          {
             throw new SqlAppException(sqlException);
          }
        return resultados;
    }

    @Override
    public List<Vehiculo> selectVehiculos() throws AppException {
        
        List<Vehiculo> vehiculos = new LinkedList<>();
        
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT V.ID_VEHICULO, V.NOMBRE_EQUIPO, V.TIPO, V.POTENCIA, V.PILOTO, V.COPILOTO, V.CLASIFICACION " + 
                                                          "  FROM VEHICULO V")
            )
        {
            if ( resultSet.next() )
            {
                do
                {
                    Vehiculo v = new Vehiculo();
                    v.setIdVehiculo(resultSet.getString(1));
                    v.setNombreEquipo(resultSet.getString(2));
                    v.setTipo(resultSet.getString(3));
                    v.setPotencia(resultSet.getInt(4));
                    v.setPiloto(resultSet.getString(5));
                    v.setCopiloto(resultSet.getString(6));
                    v.setClasificacion(resultSet.getString(7));
                    
                    vehiculos.add(v);
                } while ( resultSet.next() );
            }
        } catch (SQLException sqlException)
          {
             throw new SqlAppException(sqlException);
          }
        return vehiculos;
    }

    @Override
    public List<Etapa> selectEtapas() throws AppException {
        List<Etapa> etapas = new LinkedList<>();
        
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT E.ID_ETAPA, E.FECHA, E.RECORRIDO " + 
                                                          "  FROM ETAPA E  ")
            )
        {
            if ( resultSet.next() )
            {
                do
                {
                    
                    Etapa e = new Etapa();
                    e.setIdEtapa(resultSet.getString(1));
                    e.setFecha(resultSet.getString(2));
                    e.setRecorrido(resultSet.getInt(3));
                    
                    etapas.add(e);
                } while ( resultSet.next() );
            }
        } catch (SQLException sqlException)
          {
             throw new SqlAppException(sqlException);
          }
        return etapas;
    }
    
    
    
    
    
    
}
