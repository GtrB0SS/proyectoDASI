package com.mycompany.frontenddakar.resources;

import com.mycompany.frontenddakar.model.Dao;
import com.mycompany.frontenddakar.model.Etapa;
import com.mycompany.frontenddakar.model.Resultado;
import com.mycompany.frontenddakar.model.Vehiculo;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
    
    @GET
    @Path("resultados")
    @Produces(MediaType.APPLICATION_JSON)
    public Response requestResultados()
    {
        return Response.ok()
                       .entity( new GenericEntity< List<Resultado> > ( dao.requestResultados() ) {} )                
                       .build();
    }
    
        @GET
    @Path("etapas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response requestEtapas()
    {
        return Response.ok()
                       .entity( new GenericEntity< List<Etapa> > ( dao.requestEtapas() ) {} )                
                       .build();
    }
    
    @GET
    @Path("etapas/{idEtapa}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response requestEtapa(@PathParam("idEtapa") String idEtapa)
    {
        return Response.ok()
                       .entity( dao.requestEtapa(idEtapa) )
                       .build();
    }
    
    
        @GET
    @Path("vehiculos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response requestVehiculos()
    {
        return Response.ok()
                       .entity( new GenericEntity< List<Vehiculo> > ( dao.requestVehiculos() ) {} )                
                       .build();
    }
    
    
      @GET
    @Path("vehiculos/{idVehiculo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response requestVehiculo(@PathParam("idVehiculo") String idVehiculo)
    {
        return Response.ok()
                       .entity( dao.requestVehiculo(idVehiculo) )
                       .build();
    }
    
    
    
    
    @POST
    @Path("vehiculos")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response requestInsertVehiculo(
                                        @FormParam("idVehiculo")    String idVehiculo,
                                        @FormParam("nombreEquipo")  String nombreEquipo,
                                        @FormParam("tipo")          String tipo,
                                        @FormParam("potencia")      String potencia,
                                        @FormParam("piloto")        String piloto,
                                        @DefaultValue("")
                                        @FormParam("copiloto")      String copiloto,
                                        @FormParam("clasificacion") String clasificacion,
                                        @FormParam("tiempoTotal")   String tiempoTotal

                                       )
    {
        Optional<String> optVehiculo = dao.postVehiculo(idVehiculo, nombreEquipo, tipo, potencia, piloto, copiloto, clasificacion, tiempoTotal);
        if ( optVehiculo.isPresent() )
        {
          return Response.created( URI.create( optVehiculo.get() ) ).build();
        }
        else
        {
            return Response.serverError().build();
        }
    }
    
    
    @DELETE
    @Path("vehiculos/{idVehiculo}")
    public Response requestDeleteVehiculo(@PathParam("idVehiculo") String idVehiculo)
    {
        dao.deleteVehiculo(idVehiculo);
        return Response.noContent().build();
    }

    
}

    