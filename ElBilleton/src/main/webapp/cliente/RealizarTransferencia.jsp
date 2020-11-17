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
        <form method="POST" action="RealizarTransaccion">

            <br> <br> <br> <br> <br> <br> <br> 

            <div class="container">
                <h1>Realizar transaccion</h1>

                <div class="row">
                    <div class="col-25">
                        <label for="fnombre">Cliente</label>
                    </div>
                    <div class="col-77">
                        <input type="text" id="lname" name="nombre" value="${cliente.getNombre()}"   readonly="">
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="fnombre">Codigo de cuenta</label>
                    </div>
                    <div class="col-77">
                        <input type="text" id="lname" name="nombre" value="${Cuenta1.getCodigo()}"  readonly="">
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="fnombre">Monto de la cuenta</label>
                    </div>
                    <div class="col-77">
                        <input type="text" id="lname" name="nombre" value="${Cuenta1.getMonto()}"  readonly="">
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="fnombre">Cliente a transferir</label>
                    </div>
                    <div class="col-77">
                        <input type="text" id="lname" name="nombre" value="${Cliente2.getNombre()}"  readonly="">
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="fnombre">Cuenta a transferir</label>
                    </div>
                    <div class="col-77">
                        <input type="text" id="lname" name="nombre" value="${Cuenta2.getCodigo()}"  readonly="">
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="fDPI">Monto a transferir</label>
                    </div>
                    <div class="col-77">
                        <input type="number" min="0" pattern="^[0-9]+" id="lDPI" name="monto" placeholder="monto" required>
                    </div>
                </div>
                <div class="row">
                    <br> 
                    <input type="submit" class="button2" value="Realizar transaccion">
                </div>

            </div>

        </form>
        <%-- 
                  Mensajes de error
        --%>
        <c:if test="${successTransaccion == 0}">
            <div class="alert2">
                <span class="closebtn"> 
                    <strong>Error</strong> No ingreso datos validos de la transaccion
            </div>
        </c:if>
        <c:if test="${successTransaccion == 1}">
            <div class="alert1">
                <span class="closebtn"> 
                    <strong>Creado</strong> La transaccion se realizo exitosamente. 
            </div>
        </c:if>
        <c:if test="${successTransaccion == 2}">
            <div class="alert2">
                <span class="closebtn"> 
                    <strong>Error</strong> No tiene suficiente dinero en la cuenta
            </div>
        </c:if>
    </body>


</html>
