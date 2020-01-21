package com.upsa.backenddakar;

import javax.annotation.sql.DataSourceDefinition;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Configures JAX-RS for the application.
 * @author Juneau
 */

@DataSourceDefinition(name = "java:app/jdbc/database",
                      className = "oracle.jdbc.pool.OracleDataSource",                      
                      url="jdbc:oracle:thin:@localhost:1521:xe",
                      user = "${ENV=DATABASE_USER}",
                      password = "carlos",
                      minPoolSize = 1,
                      maxPoolSize = 3
                     )

/*
@DataSourceDefinition(name = "java:app/jdbc/database",
                      className = "oracle.jdbc.pool.OracleDataSource",                      
                      url="jdbc:oracle:thin:@${ENV=DATABASE_HOST}:${ENV=DATABASE_PORT}:${ENV=DATABASE_SID}",
                      user = "${ENV=DATABASE_USER}",
                      password = "${ENV=DATABASE_PASSWORD}",
                      minPoolSize = 1,
                      maxPoolSize = 3
                     )
*/

@ApplicationPath("/")
public class JAXRSConfiguration extends Application {
    
}
