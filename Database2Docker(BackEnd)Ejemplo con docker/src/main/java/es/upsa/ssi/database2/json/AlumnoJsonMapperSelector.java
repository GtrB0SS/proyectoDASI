/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.upsa.ssi.database2.json;

import es.upsa.ssi.database2.annotations.Format;
import es.upsa.ssi.database2.json.impl.AlumnoJsonMapperCorto;
import es.upsa.ssi.database2.json.impl.AlumnoJsonMapperLargo;
import es.upsa.ssi.database2.json.impl.AlumnoJsonMapperMedio;
import javax.inject.Inject;

/**
 *
 * @author Administrador
 */
public class AlumnoJsonMapperSelector 
{
    @Inject
    private AlumnoJsonMapperCorto ajmc;

    @Inject
    private AlumnoJsonMapperMedio ajmm;

    @Inject
    private AlumnoJsonMapperLargo ajml;
    
    
    public AlumnoJsonMapper from( Format.Type type)
    {
        switch ( type )
        {
            case CORTO: return ajmc;
            
            case MEDIO: return ajmm;
            
            default:    return ajml;
        }
    }
            
    
}
