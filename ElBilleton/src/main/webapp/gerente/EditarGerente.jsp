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
        <form method="POST" action="ActualizarGerente" enctype="multipart/form-data">

            <br> <br> <br> <br> <br> <br> <br> 

            <div class="container">
                <h1>Actualizar informacion propia</h1>

                <div class="row">
                    <div class="col-25">
                        <label for="fnombre">Codigo</label>
                    </div>
                    <div class="col-77">
                        <input type="text" id="lname" name="codigo" value="${gerenteSeleccionado.getCodigo()}" "placeholder="${gerenteSeleccionado.getCodigo()}" readonly>
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="fnombre">Nombre</label>
                    </div>
                    <div class="col-77">
                        <input type="text" id="lname" name="nombre" value="${gerenteSeleccionado.getNombre()}" required>
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="fsexo">Turno</label>
                    </div>
                    <div class="col-77">
                        <select id="country2" name="turno">
                            <option  value="${gerenteSeleccionado.getTurno()}"> ${gerenteSeleccionado.getTurno()}</option>
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
                        <input type="number" id="lDPI" min="0" pattern="^[0-9]+" name="DPI" value="${gerenteSeleccionado.getDPI()}" required>
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="fDPI">Direccion</label>
                    </div>
                    <div class="col-77">
                        <input type="text" id="lDPI" name="direccion" value="${gerenteSeleccionado.getDireccion()}" required>
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="fsexo">Sexo</label>
                    </div>
                    <div class="col-77">
                        <select id="country2" name="sexo">
                            <option  value="${gerenteSeleccionado.getSexo()}"> ${gerenteSeleccionado.getSexo()}</option>
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
                        <input type="password" id="lpassword" name="password" value="${gerenteSeleccionado.getPassword()}" required>
                    </div>
                </div>
                <div class="row">
                    <br> 
                    <input type="submit" class="button2" value="Actualizar informacion">
                </div>


            </div>

        </form>
        <%-- 
                  Mensajes de error
        --%>
        <c:if test="${successEditarGerente == 0}">
            <div class="alert2">
                <span class="closebtn"> 
                    <strong>Error</strong> No ingreso datos validos
            </div>
        </c:if>
        <c:if test="${successEditarGerente == 1}">
            <div class="alert1">
                <span class="closebtn"> 
                    <strong>Actualizado</strong> La informacion se actualizo exitosamente
            </div>
        </c:if>
          <c:if test="${successEditarGerente == 2}">
            <div class="alert2">
                <span class="closebtn"> 
                    <strong>Error</strong> No se aceptan campos vacios en los campos
            </div>
        </c:if>
    </body>


</html>
