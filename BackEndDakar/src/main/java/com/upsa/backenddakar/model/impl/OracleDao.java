/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upsa.backenddakar.model.impl;

import com.upsa.backenddakar.exceptions.AppException;
import com.upsa.backenddakar.exceptions.EtapaNotFoundException;
import com.upsa.backenddakar.exceptions.SqlAppException;
import com.upsa.backenddakar.exceptions.VehiculoNotFoundException;
import com.upsa.backenddakar.model.Dao;
import com.upsa.backenddakar.model.Etapa;
import com.upsa.backenddakar.model.Resultado;
import com.upsa.backenddakar.model.Vehiculo;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
public class OracleDao implements Dao {

    @Resource(lookup = "java:app/jdbc/database")
    private DataSource dataSource;

    @Override
    public List<Resultado> selectResultados() throws AppException {

        List<Resultado> resultados = new LinkedList<>();

        try (Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT R.ID_RESULTADO, R.TIEMPO, V.ID_VEHICULO, V.NOMBRE_EQUIPO, V.TIPO, V.POTENCIA, V.PILOTO, V.COPILOTO, V.CLASIFICACION, V.TIEMPO_TOTAL, E.ID_ETAPA, E.FECHA, E.RECORRIDO "
                        + "  FROM RESULTADO R, VEHICULO V, ETAPA E "
                        + "                                     WHERE R.ID_ETAPA = E.ID_ETAPA "
                        + "                                     AND   R.ID_VEHICULO = V.ID_VEHICULO                              ")) {
            
            if (resultSet.next()) {
                do {
                    
                    Vehiculo v = new Vehiculo();
                    v.setIdVehiculo(resultSet.getString(3));
                    v.setNombreEquipo(resultSet.getString(4));
                    v.setTipo(resultSet.getString(5));
                    v.setPotencia(resultSet.getInt(6));
                    v.setPiloto(resultSet.getString(7));
                    v.setCopiloto(resultSet.getString(8));
                    v.setClasificacion(resultSet.getString(9));
                    v.setTiempoTotal(resultSet.getString(10));

                    Etapa e = new Etapa();
                    e.setIdEtapa(resultSet.getString(11));
                    e.setFecha(resultSet.getString(12));
                    e.setRecorrido(resultSet.getInt(13));

                    Resultado resultado = new Resultado();
                    resultado.setIdResultado(resultSet.getString(1));
                    resultado.setTiempo(resultSet.getString(2));
                    resultado.setEtapa(e);
                    resultado.setVehiculo(v);

                    resultados.add(resultado);
                } while (resultSet.next());
            }
        } catch (SQLException sqlException) {
            throw new SqlAppException(sqlException);
        }
        return resultados;
    }

    @Override
    public List<Vehiculo> selectVehiculos() throws AppException {

        List<Vehiculo> vehiculos = new LinkedList<>();

        try (Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT V.ID_VEHICULO, V.NOMBRE_EQUIPO, V.TIPO, V.POTENCIA, V.PILOTO, V.COPILOTO, V.CLASIFICACION, V.TIEMPO_TOTAL "
                        + "  FROM VEHICULO V")) {
            if (resultSet.next()) {
                do {
                    Vehiculo v = new Vehiculo();
                    v.setIdVehiculo(resultSet.getString(1));
                    v.setNombreEquipo(resultSet.getString(2));
                    v.setTipo(resultSet.getString(3));
                    v.setPotencia(resultSet.getInt(4));
                    v.setPiloto(resultSet.getString(5));
                    v.setCopiloto(resultSet.getString(6));
                    v.setClasificacion(resultSet.getString(7));
                    v.setTiempoTotal(resultSet.getString(8));

                    vehiculos.add(v);
                } while (resultSet.next());
            }
        } catch (SQLException sqlException) {
            throw new SqlAppException(sqlException);
        }
        return vehiculos;
    }

    @Override
    public List<Etapa> selectEtapas() throws AppException {
        List<Etapa> etapas = new LinkedList<>();

        try (Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT E.ID_ETAPA, E.FECHA, E.RECORRIDO "
                        + "  FROM ETAPA E  ")) {
            if (resultSet.next()) {
                do {

                    Etapa e = new Etapa();
                    e.setIdEtapa(resultSet.getString(1));
                    e.setFecha(resultSet.getString(2));
                    e.setRecorrido(resultSet.getInt(3));

                    etapas.add(e);
                } while (resultSet.next());
            }
        } catch (SQLException sqlException) {
            throw new SqlAppException(sqlException);
        }
        return etapas;
    }

    @Override
    public Etapa selectEtapa(String idEtapa) throws AppException {
        Etapa etapa = new Etapa();
        String SQL_SELECT = "SELECT E.ID_ETAPA, E.FECHA, E.RECORRIDO "
                + "  FROM ETAPA E  "
                + "                                     WHERE E.ID_ETAPA = ?   ";
        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT);) {
            statement.setString(1, idEtapa);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                etapa.setIdEtapa(resultSet.getString(1));
                etapa.setFecha(resultSet.getString(2));
                etapa.setRecorrido(resultSet.getInt(3));

            } else {
                throw new EtapaNotFoundException(idEtapa);
            }
        } catch (SQLException sqlException) {
            throw new SqlAppException(sqlException);
        }
        return etapa;
    }

    @Override
    public Vehiculo selectVehiculo(String idVehiculo) throws AppException {
        Vehiculo vehiculo = new Vehiculo();

        String SQL_SELECT = "SELECT V.ID_VEHICULO, V.NOMBRE_EQUIPO, V.TIPO, V.POTENCIA, V.PILOTO, V.COPILOTO, V.CLASIFICACION, V.TIEMPO_TOTAL "
                + "  FROM VEHICULO V"
                + "                                     WHERE V.ID_VEHICULO = ?   ";

        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT)) {
            statement.setString(1, idVehiculo);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {

                vehiculo.setIdVehiculo(resultSet.getString(1));
                vehiculo.setNombreEquipo(resultSet.getString(2));
                vehiculo.setTipo(resultSet.getString(3));
                vehiculo.setPotencia(resultSet.getInt(4));
                vehiculo.setPiloto(resultSet.getString(5));
                vehiculo.setCopiloto(resultSet.getString(6));
                vehiculo.setClasificacion(resultSet.getString(7));
                vehiculo.setTiempoTotal(resultSet.getString(8));

            }else{
                throw new VehiculoNotFoundException(idVehiculo);
            }
        } catch (SQLException sqlException) {
            throw new SqlAppException(sqlException);
        }
        return vehiculo;
    }

}
