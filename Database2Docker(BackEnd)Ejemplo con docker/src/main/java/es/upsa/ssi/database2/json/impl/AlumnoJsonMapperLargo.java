/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.upsa.ssi.database2.json.impl;

import es.upsa.ssi.database2.json.AlumnoJsonMapper;
import es.upsa.ssi.database2.model.Alumno;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Administrador
 */
public class AlumnoJsonMapperLargo implements AlumnoJsonMapper
{
    
    
    @Override
    public JsonObject toJson(Alumno alumno) 
    {        
        return Json.createObjectBuilder()
                   .add("expediente", alumno.getExpediente())
                   .add("nombre", alumno.getNombre())
                   .add("dni", alumno.getDni())
                   .add("email",  alumno.getEmail())
                   .build();
    }   
}
