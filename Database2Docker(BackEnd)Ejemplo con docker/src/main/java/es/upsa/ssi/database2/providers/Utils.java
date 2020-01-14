/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.upsa.ssi.database2.providers;

import es.upsa.ssi.database2.annotations.Format;
import java.lang.annotation.Annotation;
import java.util.Optional;

/**
 *
 * @author Administrador
 */
public class Utils 
{
    public static <T> Optional<T> findAnnotation(Annotation[] annotations, Class< T > annotationClass)
    {
        for(Annotation annotation: annotations)
        {
            if ( annotationClass.isAssignableFrom( annotation.annotationType() ) )
            {
                return  Optional.of( annotationClass.cast(annotation) );
            }
        }
        return Optional.empty();
    }
    
}
