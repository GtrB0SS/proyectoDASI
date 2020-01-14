/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.upsa.ssi.database2.annotations;

import javax.enterprise.util.AnnotationLiteral;

/**
 *
 * @author Administrador
 */
public class FormatLiteral extends AnnotationLiteral<Format> implements Format
{
    public static Format of(Type type) 
    {
        return new FormatLiteral(type);
    }    
    
    
    private Type type;

    public FormatLiteral(Type type) {
        this.type = type;
    }
    
    

    @Override
    public Type value() 
    {
        return type;
    }
    
}
