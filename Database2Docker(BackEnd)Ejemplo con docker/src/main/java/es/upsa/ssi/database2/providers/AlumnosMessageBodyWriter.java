/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.upsa.ssi.database2.providers;

import es.upsa.ssi.database2.annotations.Format;
import es.upsa.ssi.database2.annotations.FormatLiteral;
import es.upsa.ssi.database2.json.AlumnoJsonMapper;
import es.upsa.ssi.database2.json.AlumnoJsonMapperSelector;
import es.upsa.ssi.database2.model.Alumno;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.json.Json;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Administrador
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class AlumnosMessageBodyWriter implements MessageBodyWriter< List<Alumno> >
{
    @Context
    private UriInfo UriInfo;
    
    @Inject
    private AlumnoJsonMapperSelector mapperSelector;

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) 
    {
        if ( List.class.isAssignableFrom( type ) )
        {
            if ( ParameterizedType.class.isAssignableFrom( genericType.getClass() ) )
            {
                ParameterizedType pt = (ParameterizedType) genericType;
                Class generic = (Class) pt.getActualTypeArguments()[0];
                if ( Alumno.class.isAssignableFrom( generic ) )
                {
                    return true;
                }
            }
        }
        return false;

    }

    @Override
    public void writeTo(List<Alumno> alumnos, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException             
    {
        
        
        
        Optional<Format> optFormat = Utils.findAnnotation(annotations, Format.class);

//        Format.Type formatType2 = optFormat.orElse( FormatLiteral.of( Format.Type.MEDIO ) ).value();
        Format.Type formatType = ( optFormat.isPresent() )? optFormat.get().value() : Format.Type.MEDIO;
        
        AlumnoJsonMapper mapper = mapperSelector.from( formatType );
        Json.createWriter(entityStream).write( mapper.toJson(alumnos) );
    }
    
}
