<%-- 
    Author     : jeffrey
--%><%@page import="java.time.LocalDate"%>

>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>El Billeton</title>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/TableStyle.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/SearchBarStyle.css?3.4">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    </head> 
    <body>
        <%@include  file="MenuNavigator.jsp" %>
        <form method="GET" action="VerSolicitudAsociacion">

            <br> <br> <br> <br> <br> <br> <br> 

            <div class="container">
                <h1 style="color:WHITE">Solicitudes de asociacion</h1>
                <br> <br>
                <div class="wrap">
                    
                </div>
                <table id="customers">
                    <tr>
                        <th >Codigo  </th>
                        <th >Fecha  </th>
                        <th >Codigo de la cuenta emisora  </th>
                        <th >Aceptar solicitud  </th>
                        <th >Rechazar solicitud  </th>
                    </tr>
                    <c:forEach items="${solicitudes}" var="solicitud">
                        <tr>
                            <td>${solicitud.getCodigo()}</td>
                            <td>${solicitud.getFecha()}</td>
                            <td>${solicitud.getCuenta_envia_codigo()}</td>
                            <td>
                                <a class="button" style="background-color: green" href="${pageContext.request.contextPath}/AceptarSolicitud?solicitud=${solicitud.getCodigo()}">Aceptar</a>
                            </td>
                            <td>
                                <a class="button" style="background-color: red" href="${pageContext.request.contextPath}/RechazarSolicitud?solicitud=${solicitud.getCodigo()}">Rechazar</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>

        </form>
        <%-- 
                          Mensajes de error
        --%>
        <c:if test="${successAsociacion == 0}">
            <div class="alert2">
                <span class="closebtn"> 
                    <strong>Error</strong> No ingreso datos validos del la asociacion
            </div>
        </c:if>
     
        <c:if test="${successAsociacion == 2}">
            <div class="alert2">
                <span class="closebtn"> 
                    <strong>Rechazada</strong> La asociacion se rechazo correctamente 
            </div>
        </c:if>
               <c:if test="${successAsociacion == 3}">
            <div class="alert2">
                <span class="closebtn"> 
                    <strong>Aceptada</strong> La asociacion se acepto correctamente 
            </div>
        </c:if>
        /c:if>
               <c:if test="${successAsociacion == 4}">
            <div class="alert2">
                <span class="closebtn"> 
                    <strong>Error</strong> Esas cuentas ya estan asociadas
            </div>
        </c:if>

    </body>


</html>
