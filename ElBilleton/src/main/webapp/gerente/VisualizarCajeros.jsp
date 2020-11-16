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
        <form method="GET" action="VisualizarCajeros">

            <br> <br> <br> <br> <br> <br> <br> 

            <div class="container">
                <h1 style="color:WHITE">Visualizar Cajeros</h1>
                <br> <br>
                <div class="wrap">
                    <div class="search">
                        <input type="text" name="filtro" class="searchTerm" placeholder="Busqueda por codigo">
                        <button type="submit" class="searchButton">
                            <i class="fa fa-search"></i>
                        </button>
                    </div>
                </div>
                <table id="customers">
                    <tr>
                        <th >Codigo  </th>
                        <th >Nombre  </th>
                        <th >Turno  </th>
                        <th >DPI  </th>
                        <th >Direccion  </th>
                        <th >Sexo  </th>
                    </tr>
                    <c:forEach items="${listaCajeros}" var="cajero">
                        <tr>
                            <td>${cajero.getCodigo()}</td>
                            <td>${cajero.getNombre()}</td>
                            <td>${cajero.getTurno()}</td>
                            <td>${cajero.getDPI()}</td>
                            <td>${cajero.getDireccion()}</td>
                            <td>${cajero.getSexo()}</td>
                            
                        </tr>
                    </c:forEach>
                </table>
            </div>

        </form>


    </body>


</html>
