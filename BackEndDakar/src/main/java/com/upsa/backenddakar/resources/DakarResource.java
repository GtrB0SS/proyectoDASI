package com.upsa.backenddakar.resources;

import com.upsa.backenddakar.model.Dao;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 *
 * @author 
 */
@Path("dakar")
public class DakarResource {
    
    @Inject
    private Dao dao;
    
    @GET
    public Response ping(){
        
        
        return Response
                .ok("OK")
                .build();
    }
}
