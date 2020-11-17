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
        <form method="GET" action="VerCuentasCliente">

            <br> <br> <br> <br> <br> <br> <br> 

            <div class="container">
                <h1 style="color:WHITE">Cuentas asociadas</h1>
                <br> <br>

                <table id="customers">
                    <tr>
                        <th class="text-center">Codigo</th>
                        <th class="text-center">Fecha Creacion</th>
                        <th class="text-center">Monto</th>
                        <th class="text-center">Codigo Cliente</th>
                        <th class="text-center">Seleccionar cuenta</th>
                    </tr>
                    <c:forEach items="${CuentasAso}" var="asociadas">
                        <tr>
                            <td class="text-center">${asociadas.getCodigo()}</td>
                            <td class="text-center">${asociadas.getFecha_creacion()}</td>
                            <td class="text-center">${asociadas.getMonto()}</td>
                            <td class="text-center">${asociadas.getCliente_codigo()}</td>
                            <td>
                                <a class="btn solid" href="${pageContext.request.contextPath}/SeleccionarCuentaAsociada?cuenta=${asociadas.getCodigo()}&&cliente=${asociadas.getCliente_codigo()}">Transferir</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                <br><br>
                <h1 style="color:WHITE">Cuentas personales</h1>
                <table id="customers">
                    <tr>
                        <th class="text-center">Codigo</th>
                        <th class="text-center">Fecha Creacion</th>
                        <th class="text-center">Monto</th>
                        <th class="text-center">Codigo Cliente</th>
                        <th class="text-center">Seleccionar cuenta</th>
                    </tr>
                    <c:forEach items="${Cuentas}" var="cuenta">
                        <tr>
                            <td class="text-center">${cuenta.getCodigo()}</td>
                            <td class="text-center">${cuenta.getFecha_creacion()}</td>
                            <td class="text-center">${cuenta.getMonto()}</td>
                            <td class="text-center">${cuenta.getCliente_codigo()}</td>
                            <td>
                                <a class="btn solid" href="${pageContext.request.contextPath}/SeleccionarCuentaAsociada?cuenta=${cuenta.getCodigo()}&&cliente=${cuenta.getCliente_codigo()}">Transferir</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>

        </form>


    </body>


</html>
