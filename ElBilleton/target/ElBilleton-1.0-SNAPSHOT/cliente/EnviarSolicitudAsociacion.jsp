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
        <form method="POST" action="${pageContext.request.contextPath}/CrearSolicitud">

            <br> <br> <br> <br> <br> <br> <br> 

            <div class="container">
                <h1>Enviar solicitud de asociacion</h1>

                <div class="row">
                    <div class="col-25">
                        <label for="fsexo">Seleccione su cuenta</label>
                    </div>
                    <div class="col-77">
                        <select id="country2" name="cuenta1">
                            <c:forEach items="${listaCuentas}" var="cuentas">
                                <option value="${cuentas.getCodigo()}" >${cuentas.getCodigo()}</option> 
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="row">
                    <div class="col-25">
                        <label for="fnombre">Cuenta que recibira la solicitud</label>
                    </div>
                    <div class="col-77">
                        <input type="text" id="lname" name="cuenta2" value="${cuentaEncontrada.getCodigo()}" placeholder="${cuentaEncontrada.getCodigo()}" readonly>
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
                    <br> 
                    <input type="submit" class="button2" value="Enviar solicitud">
                </div>

            </div>

        </form>
        <%-- 
                  Mensajes de error
        --%>
        <c:if test="${successCrearSolicitud == 0}">
            <div class="alert2">
                <span class="closebtn"> 
                    <strong>Error</strong> No ingreso datos validos la solicitud.
            </div>
        </c:if>
        <c:if test="${successCrearSolicitud == 1}">
            <div class="alert1">
                <span class="closebtn"> 
                    <strong>Creado</strong> La solicitud se genero exitosamente.  
            </div>
        </c:if>
        <c:if test="${successCrearSolicitud == 2}">
            <div class="alert2">
                <span class="closebtn"> 
                    <strong>Error</strong> Ya realizo 3 solicitudes a esa cuenta, no puede enviar mas
            </div>
        </c:if>
    </body>


</html>
