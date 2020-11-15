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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/FormStyle.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    </head> 
    <body>
        <%@include  file="MenuNavigator.jsp" %>
        <form method="POST" action="InsertarCuenta">

            <br> <br> <br> <br> <br> <br> <br> 

            <div class="container">
                <h1>Crear Cuenta</h1>

                <div class="row">
                    <div class="col-25">
                        <label for="fnombre">Codigo del cliente</label>
                    </div>
                    <div class="col-77">
                        <input type="text" id="lname" name="codigo" value="${clienteSeleccionado.getCodigo()}" placeholder="${clienteSeleccionado.getCodigo()}" readonly>
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="fnombre">Nombre del cliente</label>
                    </div>
                    <div class="col-77">
                        <input type="text" id="lname" name="nombre" placeholder="${clienteSeleccionado.getNombre()}" readonly>
                    </div>
                </div>

                <div class="row">
                    <div class="col-25">
                        <label for="fDPI">Monto inicial de la cuenta</label>
                    </div>
                    <div class="col-77">
                        <input type="number" step=".01" min="1.0" pattern="^[0-9]+" id="lDPI" name="monto" placeholder="Monto" required>
                    </div>
                </div>

                <div class="row">
                    <br> 
                    <input type="submit" class="button2" value="Registrar cuenta">
                </div>

            </div>

        </form>
        <%-- 
                  Mensajes de error
        --%>
        <c:if test="${successCrearCuenta == 0}">
            <div class="alert2">
                <span class="closebtn"> 
                    <strong>Error</strong> No ingreso datos validos del cajero
            </div>
        </c:if>
        <c:if test="${successCrearCuenta == 1}">
            <div class="alert1">
                <span class="closebtn"> 
                    <strong>Creado</strong> El cajero se creo exitosamente
            </div>
        </c:if>
        <c:if test="${successCrearCuenta == 2}">
            <div class="alert2">
                <span class="closebtn"> 
                    <strong>Creado</strong> No se aceptan datos vacios en los campos
            </div>
        </c:if>
    </body>


</html>
