<%-- 
    Document   : resultados
    Created on : 18 ene. 2020, 15:31:56
    Author     : Carlos
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Resultados de los equipos por etapa</h2>

        <table style="border: solid black 1px;">
            <thead>
                <tr>
                    <th style="border: solid black 1px;">Etapa</th>
                    <th style="border: solid black 1px;">Fecha</th>
                    <th style="border: solid black 1px;">Distancia</th>
                    <th style="border: solid black 1px;">Equipo</th>
                    <th style="border: solid black 1px;">Posicion</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="resultado" items="${resultados}">
                    <c:url var="urlViewVehiculo" value="../dakar/vehiculos/${resultado.vehiculo.idVehiculo}"/>
                    <c:url var="urlViewEtapa" value="../dakar/etapas/${resultado.etapa.idEtapa}"/>
                    <tr>
                        <td style="border: solid black 1px;"><a href="${urlViewEtapa}">${resultado.etapa.idEtapa}</a></td>
                        <td style="border: solid black 1px;">${resultado.etapa.fecha}</td>
                        <td style="border: solid black 1px;">${resultado.etapa.recorrido}</td>
                        <td style="border: solid black 1px;"><a href="${urlViewVehiculo}">${resultado.vehiculo.nombreEquipo}</a></td>
                        <td style="border: solid black 1px;">${resultado.vehiculo.clasificacion}</td>
                    </tr>
                </c:forEach> 
            </tbody>
        </table>
    </body>
</html>
