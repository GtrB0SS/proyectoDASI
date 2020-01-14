/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.upsa.ssi.database2.json.impl;

import es.upsa.ssi.database2.json.AlumnoJsonMapper;
import es.upsa.ssi.database2.link.AlumnoLinkProducer;
import es.upsa.ssi.database2.model.Alumno;
import java.math.BigDecimal;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.Link;

/**
 *
 * @author Administrador
 */
public class AlumnoJsonMapperCorto implements AlumnoJsonMapper
{
    @Inject
    private AlumnoLinkProducer alumnoLinkProducer;
    
    @Override
    public JsonObject toJson(Alumno alumno) 
    {
        Link link = alumnoLinkProducer.fromAlumno(alumno);
        
        return Json.createObjectBuilder()
                   .add("nombre", alumno.getNombre())
                   .add("link"  , Json.createObjectBuilder()
                                      .add("rel", link.getRel())
                                      .add("type", link.getType())
                                      .add("href", link.getUri().toString())
                                      .build()
                       )
                   .build();
    }   
}
