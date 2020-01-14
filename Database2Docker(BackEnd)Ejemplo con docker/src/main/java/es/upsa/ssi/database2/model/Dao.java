/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.upsa.ssi.database2.model;

import es.upsa.ssi.database2.exceptions.AppException;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Administrador
 */
public interface Dao 
{
    public List<Alumno> selectAlumnos() throws AppException;
    public Optional<Alumno> selectAlumno(String expediente) throws AppException;
    public Alumno insertAlumno(String expediente, String nombre, String dni, String email) throws AppException;

    public void update(String expediente, Alumno alumno) throws AppException;
    public void delete(String expediente) throws AppException;
}
