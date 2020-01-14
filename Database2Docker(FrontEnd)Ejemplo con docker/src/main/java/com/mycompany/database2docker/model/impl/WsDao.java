/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.database2docker.model.impl;

import com.mycompany.database2docker.exceptions.AppException;
import com.mycompany.database2docker.model.Alumno;
import com.mycompany.database2docker.model.Dao;
import com.mycompany.database2docker.services.BackEndService;
import com.mycompany.database2docker.services.Format;
import java.util.List;
import javax.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

/**
 *
 * @author Carlos
 */
public class WsDao implements Dao{

    @Inject
    @RestClient
    private BackEndService backend;
    
    @Override
    public List<Alumno> queryAlumnos() throws AppException {
        
        
        return backend.requestAlumnos(Format.LARGO);
    }
    
    
    
}
