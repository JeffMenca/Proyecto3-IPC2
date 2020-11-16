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
        <form method="GET" action="${pageContext.request.contextPath}/exportarReporte4">

            <br> <br> <br> <br> <br> <br> <br> 

            <div class="container">
                <h1 style="color:WHITE">Ver los 10 clientes con mas dinero en sus cuentas</h1>
                <br> <br>


                <input type="submit" class="button3" value="Exportar Reporte">
                <table id="customers">
                    <tr>
                        <th >Codigo </th>
                        <th >Nombre  </th>
                        <th >DPI  </th>
                        <th >Direccion  </th>
                        <th >Sexo  </th>
                        <th >Dinero en cuenta </th>
                    </tr>
                    <c:forEach items="${listaClientes}" var="clientes">
                        <tr>
                            <td>${clientes.getCodigo()}</td>
                            <td>${clientes.getNombre()}</td>
                            <td>${clientes.getDPI()}</td>
                            <td>${clientes.getDireccion()}</td>
                            <td>${clientes.getSexo()}</td>
                            <td>${clientes.getTotalTransaccion()}</td>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>

        </form>


    </body>


</html>
