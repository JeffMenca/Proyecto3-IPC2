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
        <form method="GET" action="VerCuentasCliente2">

            <br> <br> <br> <br> <br> <br> <br> 

            <div class="container">
                <h1 style="color:WHITE">Estado de cuenta</h1>
                <br> <br>
                <table id="customers">
                    <tr>
                        <th class="text-center">Cliente</th>
                        <th class="text-center">Codigo de Transaccion</th>
                        <th class="text-center">Fecha</th>
                        <th class="text-center">Hora</th>
                        <th class="text-center">Tipo</th>
                        <th class="text-center">Monto</th>
                    </tr>
                    <c:forEach items="${listaTransacciones}" var="transaccion">
                        <tr>
                            <td class="text-center">${nombre}</td>
                            <td class="text-center">${transaccion.getCodigo()}</td>
                            <td class="text-center">${transaccion.getFecha()}</td>
                            <td class="text-center">${transaccion.getHora()}</td>
                            <td class="text-center">${transaccion.getTipo()}</td>
                            <td class="text-center">${transaccion.getMonto()}</td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td class="text-center"></td>
                        <td class="text-center"></td>
                        <td class="text-center"></td>
                        <td class="text-center"></td>
                        <td class="text-center">Total: </td>
                        <td class="text-center">${cuenta.getMonto()}</td>
                    </tr>
                </table>
            </div>

        </form>


    </body>


</html>
