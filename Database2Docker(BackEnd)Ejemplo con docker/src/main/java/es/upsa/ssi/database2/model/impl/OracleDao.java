/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.upsa.ssi.database2.model.impl;

import es.upsa.ssi.database2.exceptions.AlumnoNotFoundException;
import es.upsa.ssi.database2.exceptions.AppException;
import es.upsa.ssi.database2.exceptions.DniRepetidoException;
import es.upsa.ssi.database2.exceptions.EmailRepetidoException;
import es.upsa.ssi.database2.exceptions.ExpedienteRepetidoException;
import es.upsa.ssi.database2.exceptions.SqlAppException;
import es.upsa.ssi.database2.model.Alumno;
import es.upsa.ssi.database2.model.Dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;


@ApplicationScoped
public class OracleDao implements Dao
{
    @Resource(lookup = "java:app/jdbc/database")
    private DataSource dataSource;

    @Override
    public List<Alumno> selectAlumnos() throws AppException 
    {
        List<Alumno> alumnos = new LinkedList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT A.EXPEDIENTE, A.NOMBRE, A.DNI, A.EMAIL " + 
                                                          "  FROM ALUMNOS A                              ")
            )
        {
            if ( resultSet.next() )
            {
                do
                {
                    Alumno alumno = new Alumno();
                    alumno.setExpediente( resultSet.getString(1) );
                    alumno.setNombre( resultSet.getString(2) );
                    alumno.setDni( resultSet.getString(3) );
                    alumno.setEmail( resultSet.getString(4) );
                    alumnos.add(alumno);
                    
                } while ( resultSet.next() );
            }
        } catch (SQLException sqlException)
          {
             throw new SqlAppException(sqlException);
          }
        return alumnos;
    }

    @Override
    public Optional<Alumno> selectAlumno(String expediente) throws AppException 
    {
        final String SQL = "SELECT A.EXPEDIENTE, A.NOMBRE, A.DNI, A.EMAIL " +
                           "  FROM ALUMNOS A                              " +
                           " WHERE A.EXPEDIENTE = ?                       ";
        
        try ( Connection connection = dataSource.getConnection();
              PreparedStatement ps = connection.prepareStatement(SQL)
            )
        {
            ps.setString(1, expediente);
            try ( ResultSet rs = ps.executeQuery() )
            {
                if ( rs.next() )
                {
                    Alumno alumno = new Alumno();
                    alumno.setExpediente( rs.getString(1) );
                    alumno.setNombre( rs.getString(2) );
                    alumno.setDni( rs.getString(3) );
                    alumno.setEmail( rs.getString(4) );
                    return Optional.of(alumno);
                }
                else
                {
                    return Optional.empty();
                }                     
            }
            
        } catch (SQLException sqlException)
          {
             throw new SqlAppException(sqlException);
          }

    }

    @Override  
    public Alumno insertAlumno(String expediente, String nombre, String dni, String email) throws AppException 
    {
        final String SQL_INSERT =   "INSERT INTO ALUMNOS(EXPEDIENTE, NOMBRE, DNI, EMAIL) " 
                                  + "             VALUES(?,          ?,      ?,   ?    ) ";
        
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement( SQL_INSERT )
            )
        {
            ps.setString(1, expediente);
            ps.setString(2, nombre);
            ps.setString(3, dni);
            ps.setString(4, email);
            ps.executeUpdate();
            
            Alumno alumno = new Alumno();
            alumno.setExpediente(expediente);
            alumno.setNombre(nombre);
            alumno.setDni(dni);
            alumno.setEmail(email);
            return alumno;
            
        } catch (SQLException sqlException)
          {
              String message = sqlException.getMessage();
              if      ( message.contains("PK_ALUMNOS") )       throw new ExpedienteRepetidoException();
              else if ( message.contains("UK_ALUMNOS.DNI") )   throw new DniRepetidoException();
              else if ( message.contains("UK_ALUMNOS.EMAIL") ) throw new EmailRepetidoException();

              throw new SqlAppException(sqlException);
          }

    }

    @Override
    public void update(String expediente, Alumno alumno) throws AppException 
    {
       final String SQL_UPDATE = "UPDATE ALUMNOS A       " +
                                 "   SET A.NOMBRE = ?,   " +
                                 "       A.DNI    = ?,   " +
                                 "       A.EMAIL  = ?    " +
                                 " WHERE A.EXPEDIENTE = ?";

       try ( Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_UPDATE)
           )
       {
           ps.setString(1, alumno.getNombre());
           ps.setString(2, alumno.getDni());
           ps.setString(3, alumno.getEmail());
           ps.setString(4, expediente);
           int count = ps.executeUpdate();
           if ( count == 0 ) throw new AlumnoNotFoundException(expediente);
           
       } catch (SQLException sqlException)
         {
             String message = sqlException.getMessage();
             
             if      ( message.contains("UK_ALUMNOS.DNI") )   throw new DniRepetidoException();
             else if ( message.contains("UK_ALUMNOS.EMAIL") ) throw new EmailRepetidoException();
             throw new SqlAppException(sqlException);
         }
       
    }

    @Override
    public void delete(String expediente) throws AppException 
    {
       final String SQL_UPDATE = "DELETE ALUMNOS A       " +
                                 " WHERE A.EXPEDIENTE = ?";

       try ( Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_UPDATE)
           )
       {
           ps.setString(1, expediente);
           int count = ps.executeUpdate();
           if ( count == 0 ) throw new AlumnoNotFoundException(expediente);
           
       } catch (SQLException sqlException)
         {
             throw new SqlAppException(sqlException);
         }
    }


    
}
