package com.upsa.backenddakar.resources;

import com.upsa.backenddakar.exceptions.AppException;
import com.upsa.backenddakar.model.Dao;
import com.upsa.backenddakar.model.Etapa;
import com.upsa.backenddakar.model.Resultado;
import com.upsa.backenddakar.model.Vehiculo;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.GET;
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
    
    //Parte para ver resultados y resultado individual
    @Path("/resultados")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response selectResultados(){
        
        try {
            
            List<Resultado> resultados = dao.selectResultados();
            
            return Response
                .ok("ok").entity( new GenericEntity< List<Resultado> > (resultados) {})
                .build();
            
        } catch (AppException ex) {
            return Response.status( Response.Status.BAD_REQUEST ).build();
        }
        
        
    }
    
    //Parte para ver vehiculos y vehiculo individual
    @Path("/vehiculos")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response selectVehiculos(){
        
        try {
            List<Vehiculo> vehiculos = dao.selectVehiculos();
            
            return Response
                .ok("ok").entity( new GenericEntity< List<Vehiculo> > (vehiculos) {})
                .build();
            
        } catch (AppException ex) {
            return Response.status( Response.Status.BAD_REQUEST ).build();
        }
        
        
    }
    
    @Path("/vehiculos/{idVehiculo}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response selectVehiculo(@PathParam("idVehiculo") String idVehiculo){
        
        try {
            Vehiculo vehiculo = dao.selectVehiculo(idVehiculo);
            
            return Response
                .ok("ok").entity( new GenericEntity< Vehiculo > (vehiculo) {})
                .build();
            
        } catch (AppException ex) {
            return Response.status( Response.Status.BAD_REQUEST ).build();
        }
        
        
    }
    
    
    //Parte para ver etapas y etapa individual
    @Path("/etapas")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response selectEtapas(){
        
        try {
            List<Etapa> etapas = dao.selectEtapas();
            
            return Response
                .ok("ok").entity( new GenericEntity< List<Etapa> > (etapas) {})
                .build();
            
        } catch (AppException ex) {
            return Response.status( Response.Status.BAD_REQUEST ).build();
        }
        
    }
    
    @Path("/etapas/{idEtapa}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response selectEtapa(@PathParam("idEtapa") String idEtapa){
        
        try {
            Etapa etapa = dao.selectEtapa(idEtapa);
            return Response
                .ok("ok").entity( new GenericEntity< Etapa > (etapa) {})
                .build();
            
        } catch (AppException ex) {
            return Response.status( Response.Status.BAD_REQUEST ).build();
        }
        
        
    }
    
}
