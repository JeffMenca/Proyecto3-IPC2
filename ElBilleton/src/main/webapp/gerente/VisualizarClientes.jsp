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
        <form method="GET" action="VisualizarClientes">

            <br> <br> <br> <br> <br> <br> <br> 

            <div class="container">
                <h1 style="color:WHITE">Visualizar Clientes</h1>
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
                        <th >DPI  </th>
                        <th >Direccion  </th>
                        <th >Sexo  </th>
                        <th >Fecha de nacimiento  </th>
                        <th >Ver DPI en PDF  </th>
                    </tr>
                    <c:forEach items="${listaClientes}" var="cliente">
                        <tr>
                            <td>${cliente.getCodigo()}</td>
                            <td>${cliente.getNombre()}</td>
                            <td>${cliente.getDPI()}</td>
                            <td>${cliente.getDireccion()}</td>
                            <td>${cliente.getSexo()}</td>
                            <td>${cliente.getFechaNacimiento()}</td>
                            <td><a href="${pageContext.request.contextPath}/CargarDPI?codigo=${cliente.getCodigo()}" target="_blank">Ver el DPI en PDF</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>

        </form>


    </body>


</html>
