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
        <form method="POST" action="${pageContext.request.contextPath}/CrearDeposito">

            <br> <br> <br> <br> <br> <br> <br> 

            <div class="container">
                <h1>Realizar deposito</h1>

                <div class="row">
                    <div class="col-25">
                        <label for="fnombre">Cuenta que recibira la solicitud</label>
                    </div>
                    <div class="col-77">
                        <input type="text" id="lname" name="cuenta" value="${cuentaEncontrada.getCodigo()}" placeholder="${cuentaEncontrada.getCodigo()}" readonly>
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="fnombre">Nombre del propietario</label>
                    </div>
                    <div class="col-77">
                        <input type="text" id="lname" name="nombre" value="${clienteEncontrado.getNombre()}" placeholder="${clienteEncontrado.getNombre()}" readonly>
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="fDPI">Monto a depositar</label>
                    </div>
                    <div class="col-77">
                        <input type="number" step=".05" min="1.0" pattern="^[0-9]+" id="lDPI" name="monto" placeholder="Monto" required>
                    </div>
                </div>

                <div class="row">
                    <br> 
                    <input type="submit" class="button2" value="Realizar deposito">
                </div>

            </div>

        </form>
        <%-- 
                  Mensajes de error
        --%>
        <c:if test="${successDeposito == 0}">
            <div class="alert2">
                <span class="closebtn"> 
                    <strong>Error</strong> No ingreso datos validos la solicitud.
            </div>
        </c:if>
        <c:if test="${successDeposito == 1}">
            <div class="alert1">
                <span class="closebtn"> 
                    <strong>Creado</strong> El deposito se realizo correctamente  
            </div>
        </c:if>
        <c:if test="${successDeposito == 2}">
            <div class="alert2">
                <span class="closebtn"> 
                    <strong>Error</strong> No se logro hacer el deposito
            </div>
        </c:if>
    </body>


</html>
