package com.mycompany.database2docker.resources;

import com.mycompany.database2docker.exceptions.AppException;
import com.mycompany.database2docker.model.Alumno;
import com.mycompany.database2docker.model.Dao;
import java.util.List;
import javax.inject.Inject;
import javax.mvc.Controller;
import javax.mvc.Models;
import javax.mvc.View;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 *
 * @author 
 */
@Path("alumnos")
public class AlumnosResource {
    @Inject
    private Dao dao;
    
    @Inject
    private Models models;
    
    @GET
    @Controller
    @View("/jsps/alumnos.jsp")
    public void requestAlumnos() throws AppException{
        List<Alumno> alumnos = dao.queryAlumnos();
        models.put("alumnos", alumnos);
        
    }
}
