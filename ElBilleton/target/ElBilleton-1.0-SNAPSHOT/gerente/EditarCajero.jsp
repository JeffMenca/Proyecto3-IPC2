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
        <form method="POST" action="ActualizarCajero" enctype="multipart/form-data">

            <br> <br> <br> <br> <br> <br> <br> 

            <div class="container">
                <h1>Actualizar informacion de Cajero</h1>

                <div class="row">
                    <div class="col-25">
                        <label for="fnombre">Codigo</label>
                    </div>
                    <div class="col-77">
                        <input type="text" id="lname" name="codigo" value="${cajeroSeleccionado.getCodigo()}" "placeholder="${cajeroSeleccionado.getCodigo()}" readonly>
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="fnombre">Nombre</label>
                    </div>
                    <div class="col-77">
                        <input type="text" id="lname" name="nombre" value="${cajeroSeleccionado.getNombre()}" required>
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="fsexo">Turno</label>
                    </div>
                    <div class="col-77">
                        <select id="country2" name="turno">
                            <option  value="${cajeroSeleccionado.getTurno()}"> ${cajeroSeleccionado.getTurno()}</option>
                            <option value="Vespertino" >Vespertino</option>  
                            <option value="Matutino" >Matutino</option>
                            <option value="Otro" >Otro</option>
                        </select>
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="fDPI">DPI</label>
                    </div>
                    <div class="col-77">
                        <input type="number" id="lDPI" min="0" pattern="^[0-9]+" name="DPI" value="${cajeroSeleccionado.getDPI()}" required>
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="fDPI">Direccion</label>
                    </div>
                    <div class="col-77">
                        <input type="text" id="lDPI" name="direccion" value="${cajeroSeleccionado.getDireccion()}" required>
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="fsexo">Sexo</label>
                    </div>
                    <div class="col-77">
                        <select id="country2" name="sexo">
                            <option  value="${cajeroSeleccionado.getSexo()}"> ${cajeroSeleccionado.getSexo()}</option>
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
                        <input type="password" id="lpassword" name="password" value="${cajeroSeleccionado.getPassword()}" required>
                    </div>
                </div>
                <div class="row">
                    <br> 
                    <input type="submit" class="button2" value="Actualizar cajero">
                </div>


            </div>

        </form>
        <%-- 
                  Mensajes de error
        --%>
        <c:if test="${successEditarCajero == 0}">
            <div class="alert2">
                <span class="closebtn"> 
                    <strong>Error</strong> No ingreso datos validos del cajero
            </div>
        </c:if>
        <c:if test="${successEditarCajero == 1}">
            <div class="alert1">
                <span class="closebtn"> 
                    <strong>Actualizado</strong> El cajero se edito exitosamente
            </div>
        </c:if>
           <c:if test="${successEditarCajero == 2}">
            <div class="alert2">
                <span class="closebtn"> 
                    <strong>Error</strong> No se aceptan espacios vacios en los campos
            </div>
        </c:if>
    </body>


</html>
