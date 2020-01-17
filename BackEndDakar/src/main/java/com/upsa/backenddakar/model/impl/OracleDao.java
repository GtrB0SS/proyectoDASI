/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upsa.backenddakar.model.impl;

import com.upsa.backenddakar.model.Dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 *
 * @author Carlos
 */
public class OracleDao implements Dao{
    
    @Resource(lookup = "java:app/jdbc/database")
    private DataSource dataSource;

    
    
    
    
}
