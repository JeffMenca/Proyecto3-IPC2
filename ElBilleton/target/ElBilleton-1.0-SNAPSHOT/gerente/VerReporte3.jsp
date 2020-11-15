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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/SearchBarStyle.css?3.7">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    </head> 
    <body>
        <%@include  file="MenuNavigator.jsp" %>
        <form method="GET" action="VerReporte2">

            <br> <br> <br> <br> <br> <br> <br> 

            <div class="container">
                <h1 style="color:WHITE">Ver clientes con transacciones monetarias sumadas mayores
                    a un limite establecido</h1>
                <br> <br>

                <input type="submit" class="button3" value="Exportar Reporte">

                <table id="customers">
                    <tr>
                        <th >Codigo </th>
                        <th >Nombre  </th>
                        <th >DPI  </th>
                        <th >Direccion  </th>
                        <th >Sexo  </th>
                        <th >Transacciones sumadas  </th>
                        <th >Ver transacciones  </th>
                    </tr>
                    <c:forEach items="${listaCliente}" var="cliente">
                        <tr>
                            <td>${cliente.getCodigo()}</td>
                            <td>${cliente.getNombre()}</td>
                            <td>${cliente.getDPI()}</td>
                            <td>${cliente.getDireccion()}</td>
                            <td>${cliente.getSexo()}</td>
                            <td>${cliente.getTotalTransaccion()}</td>
                            <td>
                                <a class="button" href="${pageContext.request.contextPath}/VerTransaccionesReporte3?codigo=${cliente.getCodigo()}">Ver transacciones</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>

        </form>


    </body>


</html>
