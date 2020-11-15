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
        <form method="POST" action="EstablecerLimites" enctype="multipart/form-data">

            <br> <br> <br> <br> <br> <br> <br> 

            <div class="container">
                <h1>Establecer limites del reporte 2 y 3</h1>

                <div class="row">
                    <div class="col-25">
                        <label for="fDPI">Limite del reporte 2</label>
                    </div>
                    <div class="col-77">
                        <input type="number" step=".01" min="0.0" pattern="^[0-9]+" id="lDPI" value="${limitesSeleccionados.getLimite_reporte2()}" name="limite2" placeholder="${limitesSeleccionados.getLimite_reporte2()}" required>
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="fDPI">Limite del reporte 3</label>
                    </div>
                    <div class="col-77">
                        <input type="number" step=".01" min="0.0" pattern="^[0-9]+" id="lDPI" value="${limitesSeleccionados.getLimite_reporte3()}" name="limite3" placeholder="${limitesSeleccionados.getLimite_reporte3()}" required>
                    </div>
                </div>

                <div class="row">
                    <br> 
                    <input type="submit" class="button2" value="Establecer limites">
                </div>


            </div>

        </form>
        <%-- 
                  Mensajes de error
        --%>
        <c:if test="${successEditarLimites == 0}">
            <div class="alert2">
                <span class="closebtn"> 
                    <strong>Error</strong> No ingreso datos validos
            </div>
        </c:if>
        <c:if test="${successEditarLimites == 1}">
            <div class="alert1">
                <span class="closebtn"> 
                    <strong>Establecidos</strong> Los limites se establecieron correctamente
            </div>
        </c:if>
             <c:if test="${successEditarLimites == 2}">
            <div class="alert2">
                <span class="closebtn"> 
                    <strong>Error</strong> El limite del reporte 2 debe ser menor al limite del reporte 3
            </div>
        </c:if>
    </body>


</html>
