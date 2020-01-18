package com.mycompany.frontenddakar.resources;

import com.mycompany.frontenddakar.model.Dao;
import com.mycompany.frontenddakar.model.Etapa;
import com.mycompany.frontenddakar.model.Resultado;
import com.mycompany.frontenddakar.model.Vehiculo;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.mvc.Controller;
import javax.mvc.Models;
import javax.mvc.View;
import javax.mvc.binding.BindingResult;
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
@Path("/dakar")
public class DakarResource {

    @Inject
    private Dao dao;

    @Inject
    private Models models;

    @Inject
    private BindingResult bindingResult;

    @GET
    @Controller
    @Path("/resultados")
    @View("/jsps/resultados.jsp")
    public void requestResultados() {
        List<Resultado> resultados = dao.requestResultados();

        models.put("resultados", resultados);

    }

    @GET
    @Path("etapas")
    @Controller
    @View("/jsps/etapas.jsp")
    public void requestEtapas() {

        List<Etapa> etapas = dao.requestEtapas();

        models.put("etapas", etapas);

    }

    @GET
    @Path("etapas/{idEtapa}")
    @Controller
    @View("/jsps/etapa.jsp")
    public void requestEtapa(@PathParam("idEtapa") String idEtapa) {
        
        Etapa etapa = dao.requestEtapa(idEtapa);
        
        models.put("etapa", etapa);
    }

    @GET
    @Path("vehiculos")
    @Controller
    @View("/jsps/vehiculos.jsp")
    public void requestVehiculos() {

        List<Vehiculo> vehiculos = dao.requestVehiculos();

        models.put("vehiculos", vehiculos);

    }

    @GET
    @Path("vehiculos/{idVehiculo}")
    @Controller
    @View("/jsps/vehiculo.jsp")
    public void requestVehiculo(@PathParam("idVehiculo") String idVehiculo) {
        
        Vehiculo vehiculo = dao.requestVehiculo(idVehiculo);
        
        models.put("vehiculo", vehiculo);
        
        
    }

    @GET
    @Path("vehiculos/insertarVehiculo")
    @Controller
    @View("/jsps/formInsertarVehiculo.jsp")
    public void formVehiculo() {
        
    }
    
    @POST
    @Path("vehiculos")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response requestInsertVehiculo(
            @FormParam("idVehiculo") String idVehiculo,
            @FormParam("nombreEquipo") String nombreEquipo,
            @FormParam("tipo") String tipo,
            @FormParam("potencia") String potencia,
            @FormParam("piloto") String piloto,
            @DefaultValue("")
            @FormParam("copiloto") String copiloto,
            @FormParam("clasificacion") String clasificacion,
            @FormParam("tiempoTotal") String tiempoTotal
    ) {
        Optional<String> optVehiculo = dao.postVehiculo(idVehiculo, nombreEquipo, tipo, potencia, piloto, copiloto, clasificacion, tiempoTotal);
        if (optVehiculo.isPresent()) {
            return Response.created(URI.create(optVehiculo.get())).build();
        } else {
            return Response.serverError().build();
        }
    }

    @GET
    @Path("vehiculos/del/{idVehiculo}")
    public Response requestDeleteVehiculo(@PathParam("idVehiculo") String idVehiculo) {
        dao.deleteVehiculo(idVehiculo);
        return Response.noContent().build();
    }

}
