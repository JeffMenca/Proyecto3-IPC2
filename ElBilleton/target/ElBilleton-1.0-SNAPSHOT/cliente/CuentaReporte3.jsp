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
        <form method="GET" action="exportarReporte3ClienteCue">

            <br> <br> <br> <br> <br> <br> <br> 

            <div class="container">
                <h1 style="color:WHITE">Cuenta con mas dinero</h1>
                <br> <br>

                <input type="submit" class="button3" value="Exportar Reporte">
                <input  name="fecha1" type="hidden" value="${fecha1}">
                <input  name="cliente" type="hidden" value="${cliente}">
                <input  name="cuenta" type="hidden" value="${cuenta.getCodigo()}">
                <table id="customers">
                    <tr>
                        <th >Codigo  </th>
                        <th >Fecha de creacion  </th>
                        <th >Monto  </th>
                        <th >Ver transacciones  </th>
                    </tr>
                    <c:forEach items="${cuentas}" var="cuenta">
                        <tr>
                            <td>${cuenta.getCodigo()}</td>
                            <td>${cuenta.getFecha_creacion()}</td>
                            <td>${cuenta.getMonto()}</td>
                            <td>
                                <a class="button" href="${pageContext.request.contextPath}/GenerarReporte3Transacciones?cuenta=${cuenta.getCodigo()}&&fecha=${fecha1}">Ver transacciones</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>

        </form>


    </body>


</html>
