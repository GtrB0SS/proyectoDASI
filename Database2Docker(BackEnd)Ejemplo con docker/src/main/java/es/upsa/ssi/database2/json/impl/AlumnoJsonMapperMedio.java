/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.upsa.ssi.database2.json.impl;

import es.upsa.ssi.database2.json.AlumnoJsonMapper;
import es.upsa.ssi.database2.model.Alumno;
import java.math.BigDecimal;
import javax.json.Json;
import javax.json.JsonObject;

/**
 *
 * @author Administrador
 */
public class AlumnoJsonMapperMedio implements AlumnoJsonMapper
{
    @Override
    public JsonObject toJson(Alumno alumno) 
    {
        return Json.createObjectBuilder()
                   .add("nombre", alumno.getNombre())
                   .add("email", alumno.getEmail())
                   .build();
    }   
}
