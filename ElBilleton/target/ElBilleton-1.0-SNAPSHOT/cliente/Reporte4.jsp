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
        <form method="GET" action="${pageContext.request.contextPath}/exportarReporte4Clientes">

            <br> <br> <br> <br> <br> <br> <br> 

            <div class="container">
                <h1 style="color:WHITE">Ver historial con listado de solicitud de asociación
                    de cuentas recibidas</h1>
                <br> <br>
                <input type="submit" class="button3" value="Exportar Reporte">
                <input  name="cuenta" type="hidden" value="${cuenta}">
                <table id="customers">
                    <tr>
                        <th class="text-center">Codigo de solicitud</th>
                        <th class="text-center">Fecha</th>
                        <th class="text-center">Estado</th>
                        <th class="text-center">Cuenta que la emitio</th>
                        <th class="text-center">Cuenta que la recibio</th>
                    </tr>
                    <c:forEach items="${listadoSolicitudes}" var="solicitud">
                        <tr>
                            <td class="text-center">${solicitud.getCodigo()}</td>
                            <td class="text-center">${solicitud.getFecha()}</td>
                            <td class="text-center">${solicitud.getEstado()}</td>
                            <td class="text-center">${solicitud.getCuenta_envia_codigo()}</td>
                            <td class="text-center">${solicitud.getCuenta2_recibe_codigo1()}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>

        </form>


    </body>


</html>
