package com.upsa.backenddakar.resources;

import com.upsa.backenddakar.exceptions.AppException;
import com.upsa.backenddakar.model.Dao;
import com.upsa.backenddakar.model.Resultado;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author 
 */
@Path("dakar")
public class DakarResource {
    
    @Inject
    private Dao dao;
    
    @Path("/resultados")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response ping(){
        
        try {
            List<Resultado> resultados = dao.selectResultados();
            return Response
                .ok("ok").entity( new GenericEntity< List<Resultado> > (resultados) {})
                .build();
            
        } catch (AppException ex) {
            return Response.status( Response.Status.BAD_REQUEST ).build();
        }
        
        
    }
}
