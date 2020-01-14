/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.upsa.ssi.database2.providers;

import es.upsa.ssi.database2.exceptions.AppException;
import es.upsa.ssi.database2.exceptions.ExpedienteRepetidoException;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Administrador
 */
@Provider
@Produces(MediaType.TEXT_PLAIN)
public class AppExceptionMapper implements ExceptionMapper<ExpedienteRepetidoException>
{
    @Override
    public Response toResponse(ExpedienteRepetidoException exception) 
    {
        return Response.serverError()
                       .entity("El expediente está repetido")
                       .build();
    }    
}
