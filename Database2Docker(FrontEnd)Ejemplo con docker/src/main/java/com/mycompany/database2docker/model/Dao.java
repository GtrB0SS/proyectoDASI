/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.database2docker.model;

import com.mycompany.database2docker.exceptions.AppException;
import java.util.List;

/**
 *
 * @author Carlos
 */
public interface Dao {
    public List<Alumno> queryAlumnos() throws AppException;
}
