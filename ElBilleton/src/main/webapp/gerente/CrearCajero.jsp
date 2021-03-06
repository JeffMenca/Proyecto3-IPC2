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
        <form method="POST" action="InsertarCajero">

            <br> <br> <br> <br> <br> <br> <br> 

            <div class="container">
                <h1>Crear Cajero</h1>

                <div class="row">
                    <div class="col-25">
                        <label for="fnombre">Nombre</label>
                    </div>
                    <div class="col-77">
                        <input type="text" id="lname" name="nombre" placeholder="Nombre del cajero" required>
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="fsexo">Turno</label>
                    </div>
                    <div class="col-77">
                        <select id="country2" name="turno">
                            <option value="Vespertino" >Vespertino</option>  
                            <option value="Matutino" >Matutino</option>
                        </select>
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="fDPI">DPI</label>
                    </div>
                    <div class="col-77">
                        <input type="number" min="0" pattern="^[0-9]+" id="lDPI" name="DPI" placeholder="DPI" required>
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="fDPI">Direccion</label>
                    </div>
                    <div class="col-77">
                        <input type="text" id="lDPI" name="direccion" placeholder="Direccion" required>
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="fsexo">Sexo</label>
                    </div>
                    <div class="col-77">
                        <select id="country2" name="sexo">
                            <option value="Masculino" >Masculino</option>  
                            <option value="Femenino" >Femenino</option>
                            <option value="Otro" >Otro</option>
                        </select>
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="fpassword">Password</label>
                    </div>
                    <div class="col-77">
                        <input type="password" id="lpassword" name="password" placeholder="Password" required>
                    </div>
                </div>

                <div class="row">
                    <br> 
                    <input type="submit" class="button2" value="Registrar cajero">
                </div>

            </div>

        </form>
        <%-- 
                  Mensajes de error
        --%>
        <c:if test="${successCrearCajero == 0}">
            <div class="alert2">
                <span class="closebtn"> 
                    <strong>Error</strong> No ingreso datos validos del cajero
            </div>
        </c:if>
        <c:if test="${successCrearCajero == 1}">
            <div class="alert1">
                <span class="closebtn"> 
                    <strong>Creado</strong> El cajero se creo exitosamente. 
                    (El codigo del cajero creado es <strong>${codigoCreado}</strong>)
            </div>
        </c:if>
        <c:if test="${successCrearCajero == 2}">
            <div class="alert2">
                <span class="closebtn"> 
                    <strong>Error</strong> No se permiten espacios vacios en campos necesarios
            </div>
        </c:if>
    </body>


</html>
