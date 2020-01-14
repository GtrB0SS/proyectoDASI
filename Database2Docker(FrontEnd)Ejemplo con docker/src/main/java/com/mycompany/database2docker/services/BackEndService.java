/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.database2docker.services;

import com.mycompany.database2docker.model.Alumno;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

/**
 *
 * @author Carlos
 */

@RegisterRestClient
public interface BackEndService {
    
    @GET
    @Path("/alumnos")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Alumno> requestAlumnos(@QueryParam("format") Format format);
    
}
