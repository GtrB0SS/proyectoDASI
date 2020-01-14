/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.upsa.ssi.database2.exceptions;

/**
 *
 * @author Administrador
 */
public class AlumnoNotFoundException extends AppException
{
    private String expediente;

    public AlumnoNotFoundException(String expediente)
    {
        super( String.format("No existe el alumno con expediente %s", expediente) );
        this.expediente = expediente;
    }

    public String getExpediente() {
        return expediente;
    }
    
    
    
    
}
