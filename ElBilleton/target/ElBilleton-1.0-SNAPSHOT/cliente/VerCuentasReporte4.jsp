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
        <form method="GET" action="VerCuentasReporte4">

            <br> <br> <br> <br> <br> <br> <br> 

            <div class="container">
                <h1 style="color:WHITE">Seleccionar la cuenta</h1>
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
                        <th >Seleccionar cuenta  </th>
                    </tr>
                    <c:forEach items="${listaCuentas}" var="cuenta">
                        <tr>
                            <td>${cuenta.getCodigo()}</td>
                            <td>
                                <a class="button" href="${pageContext.request.contextPath}/GenerarReporte4Cliente?cuenta=${cuenta.getCodigo()}&&cliente=${cuenta.getCliente_codigo()}">Seleccionar</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>

        </form>


    </body>


</html>
