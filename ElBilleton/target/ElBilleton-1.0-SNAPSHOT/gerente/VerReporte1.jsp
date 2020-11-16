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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/SearchBarStyle.css?3.5">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    </head> 
    <body>
        <%@include  file="MenuNavigator.jsp" %>
        <form method="GET" action="${pageContext.request.contextPath}/exportarReporte1">

            <br> <br> <br> <br> <br> <br> <br> 

            <div class="container">
                <h1 style="color:WHITE">Ver historial de cambios realizados en una entidad
                    especifica</h1>
                <br> <br>


                
                <input  name="tipo" type="hidden" value="${tipo}">
                <input name="codigo" type="hidden" value="${codigoEntidad}">
                <input type="submit" class="button3" value="Exportar Reporte">
                <table id="customers">
                    <tr>
                        <th >Codigo de cambio  </th>
                        <th >Codigo </th>
                        <th >Nombre  </th>
                        <th >DPI  </th>
                        <th >Direccion  </th>
                        <th >Sexo  </th>
                        <th >Ver DPI en pdf  </th>
                    </tr>
                    <c:forEach items="${listaHistorial}" var="historial">
                        <tr>
                            <td>${historial.getCodigo()}</td>
                            <td>${codigoEntidad}</td>
                            <td>${historial.getNombre()}</td>
                            <td>${historial.getDPI()}</td>
                            <td>${historial.getDireccion()}</td>
                            <td>${historial.getSexo()}</td>
                            <td><a href="${pageContext.request.contextPath}/CargarDPIHistorial?codigo=${historial.getCodigo()}" target="_blank">Ver el DPI en PDF</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>

        </form>


    </body>


</html>
