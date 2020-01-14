package es.upsa.ssi.database2.resources;

import es.upsa.ssi.database2.annotations.Format;
import es.upsa.ssi.database2.annotations.FormatLiteral;
import es.upsa.ssi.database2.exceptions.AppException;
import es.upsa.ssi.database2.link.AlumnoLinkProducer;
import es.upsa.ssi.database2.model.Alumno;
import es.upsa.ssi.database2.model.Dao;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author 
 */
@Path("/alumnos")
public class AlumnosResource
{
    @Inject
    private Dao dao;
    
    @Inject
    private AlumnoLinkProducer alumnoLinkProducer;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response selectAlumnos(@QueryParam("format") @DefaultValue("MEDIO") String format) throws AppException
    {
        Format.Type formatType;        
        try
        {
          formatType = Format.Type.valueOf( format );
          
        } catch (IllegalArgumentException iae)
          {
              return Response.status( Response.Status.BAD_REQUEST ).build();
          }
        
        
        List<Alumno> alumnos = dao.selectAlumnos();
        
        return Response.ok()
                       .entity( new GenericEntity< List<Alumno> > (alumnos) {}, new Annotation[] { FormatLiteral.of(formatType) } )
                       .build();
    }
    
    
    @Path("/{expediente}")
    @GET
    @Produces(MediaType.APPLICATION_JSON) 
//    @Format(Format.Type.CORTO)
    public Response selectAlumno(@PathParam("expediente") String expediente, 
                                 @QueryParam("format") @DefaultValue("MEDIO") String format) throws AppException
    {
        Format.Type formatType;
        
        try
        {
          formatType = Format.Type.valueOf( format );
          
        } catch (IllegalArgumentException iae)
          {
              return Response.status( Response.Status.BAD_REQUEST ).build();
          }
        
        Optional<Alumno> optAlumno = dao.selectAlumno(expediente);
        if ( optAlumno.isPresent() )
        {
            Alumno alumno = optAlumno.get();
            return Response.status(Response.Status.OK)
                           .entity(alumno, new Annotation[] { FormatLiteral.of( formatType )  })                                                      
                           .build();            
        }
        return Response.status( Response.Status.NOT_FOUND )
                       .build();
    }
    
    

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)    
    public Response insertAlumno(@FormParam("expediente") String expediente,
                                 @FormParam("nombre") String nombre,
                                 @FormParam("dni") String dni,
                                 @FormParam("email") String email
                                ) throws AppException
    {
        Alumno alumno = dao.insertAlumno(expediente, nombre, dni, email);
        
        
        return Response.created(  alumnoLinkProducer.fromAlumno(alumno).getUri()  )
                       .build();
        
    }
    
    
    
    @PUT
    @Path("{expediente}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update( @PathParam("expediente") String expediente, 
                            Alumno alumno) throws AppException
    {
        dao.update(expediente, alumno);
        
        return Response.noContent().build();
    }
    
    @DELETE
    @Path("{expediente}")
    public Response delete(@PathParam("expediente") String expediente) throws AppException
    {
        dao.delete(expediente);
        
        return Response.noContent().build();
    }
    
}
