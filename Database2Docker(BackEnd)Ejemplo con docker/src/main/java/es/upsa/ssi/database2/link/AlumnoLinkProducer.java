/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.upsa.ssi.database2.link;

import es.upsa.ssi.database2.model.Alumno;
import es.upsa.ssi.database2.resources.AlumnosResource;
import java.net.URI;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Administrador
 */
public class AlumnoLinkProducer 
{
    @Context
    private UriInfo uriInfo;
    
    public Link fromAlumno(Alumno alumno)
    {
              
        
        UriBuilder uriBuilder = uriInfo.getBaseUriBuilder();
        URI alumnoURI = uriBuilder.path("/alumnos/{expediente}")
                                  .resolveTemplate("expediente", alumno.getExpediente())
                                  .build();                
                                  //.path(AlumnosResource.class)
                                  //.path( AlumnosResource.class.getDeclaredMethod("selectAlumno", String.class, String.class) )
                
        return Link.fromUri(alumnoURI)
                   .rel("self")
                   .type( MediaType.APPLICATION_JSON )
                   .build( alumno.getExpediente() );
    }
    
}
