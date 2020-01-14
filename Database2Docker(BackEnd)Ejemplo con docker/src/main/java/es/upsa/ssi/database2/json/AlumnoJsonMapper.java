/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.upsa.ssi.database2.json;

import es.upsa.ssi.database2.model.Alumno;
import java.util.Collection;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

/**
 *
 * @author Administrador
 */
public interface AlumnoJsonMapper 
{
    public JsonObject toJson(Alumno alumno);
 
    public default JsonArray toJson(Collection<Alumno> alumnos)
    {
        JsonArrayBuilder jab = Json.createArrayBuilder();
        for(Alumno alumno: alumnos) jab.add( toJson(alumno) );
        return jab.build();
    }
}
