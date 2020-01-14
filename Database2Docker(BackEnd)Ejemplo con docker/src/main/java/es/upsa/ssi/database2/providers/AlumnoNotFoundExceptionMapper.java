/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.upsa.ssi.database2.providers;

import es.upsa.ssi.database2.exceptions.AlumnoNotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Administrador
 */
@Provider

public class AlumnoNotFoundExceptionMapper implements ExceptionMapper< AlumnoNotFoundException >
{

    @Override
    public Response toResponse(AlumnoNotFoundException exception) 
    {
        return Response.status(Response.Status.NOT_FOUND)
                       .build();
    }
    
}
