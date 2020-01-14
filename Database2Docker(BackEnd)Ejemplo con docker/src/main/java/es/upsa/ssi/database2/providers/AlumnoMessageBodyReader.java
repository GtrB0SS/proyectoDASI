/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.upsa.ssi.database2.providers;

import es.upsa.ssi.database2.model.Alumno;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Administrador
 */
@Provider
@Consumes(MediaType.APPLICATION_JSON)
public class AlumnoMessageBodyReader implements MessageBodyReader<Alumno>
{

    @Override
    public boolean isReadable(Class<?> arg0, Type arg1, Annotation[] arg2, MediaType arg3) {
        return false;
    }

    @Override
    public Alumno readFrom(Class<Alumno> arg0, Type arg1, Annotation[] arg2, MediaType arg3, MultivaluedMap<String, String> arg4, InputStream arg5) throws IOException, WebApplicationException 
    {
        try ( JsonReader jsonReader = Json.createReader(arg5) )
        {
            JsonObject jsonObject = jsonReader.readObject();
            Alumno alumno = new Alumno();
            alumno.setExpediente( jsonObject.getString("expediente") );
            
            return alumno;
        }
        
        
    }
    
}
