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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/FormStyle.css?3.0">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    </head> 
    <body>
        <%@include  file="MenuNavigator.jsp" %>
        <form method="GET" action="${pageContext.request.contextPath}/GenerarReporte5">

            <br> <br> <br> <br> <br> <br> <br> 

            <div class="container">
                <h1>Ingrese el intervalo</h1>

                <div class="row">
                    <div class="col-25">
                        <label for="fname">Fecha de inicio</label>
                    </div>
                    <div class="col-77">
                        <input  type="date" name="fechaInicio"
                                value="<%= LocalDate.now()%>"
                                min="1870-01-01" max="<%= LocalDate.now()%>" required>
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="fname">Fecha de final</label>
                    </div>
                    <div class="col-77">
                        <input  type="date" name="fechaFinal"
                                value="<%= LocalDate.now()%>"
                                min="1870-01-01" max="<%= LocalDate.now()%>" required>
                    </div>
                </div>

                <div class="row">
                    <br> 
                    <input type="submit" class="button2" value="Generar reporte">
                </div>

            </div>

        </form>
        <c:if test="${successIntervalo == 2}">
            <div class="alert2">
                <span class="closebtn"> 
                    <strong>Error</strong> La fecha inicial debe ser antes de la fecha final
            </div>
        </c:if>
    </body>


</html>
