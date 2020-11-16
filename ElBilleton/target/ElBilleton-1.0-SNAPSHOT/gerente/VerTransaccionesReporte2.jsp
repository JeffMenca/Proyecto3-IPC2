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
        <form method="GET" action="${pageContext.request.contextPath}/exportarReporte6">

            <br> <br> <br> <br> <br> <br> <br> 

            <div class="container">
                <h1 style="color:WHITE">Transacciones del cliente</h1>
                <br> <br>


                <input type="submit" class="button3" value="Exportar Reporte">
                <input  name="cliente" type="hidden" value="${cliente}">
                <table id="customers">
                    <tr>
                        <th >Codigo </th>
                        <th >Fecha  </th>
                        <th >Hora  </th>
                        <th >Tipo  </th>
                        <th >Monto  </th>

                    </tr>
                    <c:forEach items="${listaTransacciones}" var="transacciones">
                        <tr>
                            <td>${transacciones.getCodigo()}</td>
                            <td>${transacciones.getFecha()}</td>
                            <td>${transacciones.getHora()}</td>
                            <td>${transacciones.getTipo()}</td>
                            <td>${transacciones.getMonto()}</td>

                        </tr>
                    </c:forEach>
                </table>
            </div>

        </form>


    </body>


</html>
