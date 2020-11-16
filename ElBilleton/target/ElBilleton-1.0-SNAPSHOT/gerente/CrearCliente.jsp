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
        <form method="POST" action="InsertarCliente" enctype="multipart/form-data">

            <br> <br> <br> <br> <br> <br> <br> 

            <div class="container">
                <h1>Crear Cliente</h1>

                <div class="row">
                    <div class="col-25">
                        <label for="fnombre">Nombre</label>
                    </div>
                    <div class="col-77">
                        <input type="text" id="lname" name="nombre" placeholder="Nombre del cliente" required>
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="fname">Fecha de nacimiento</label>
                    </div>
                    <div class="col-77">
                        <input  type="date" name="fecha"
                                value="<%= LocalDate.now()%>"
                                min="1870-01-01" max="<%= LocalDate.now()%>" required>
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="fDPI">DPI</label>
                    </div>
                    <div class="col-77">
                        <input type="number" id="lDPI" min="0" pattern="^[0-9]+" name="DPI" placeholder="DPI" required>
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
                            <option value="otro" >Otro</option>
                        </select>
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="fname">Seleccionar archivos</label>
                    </div>
                    <div class="col-77">
                        <input type="file" id="lfile" accept="application/pdf" name="archivo"  multiple required>
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
                    <div class="col-25">
                        <label for="fDPI">Monto para abrir su cuenta</label>
                    </div>
                    <div class="col-77">
                        <input type="number" step=".01" min="1.0" id="lDPI" name="monto" placeholder="Monto" required>
                    </div>
                </div>
                <div class="row">
                    <br> 
                    <input type="submit" class="button2" value="Registrar cliente">
                </div>


            </div>

        </form>
        <%-- 
                  Mensajes de error
        --%>
        <c:if test="${successCrearCliente == 0}">
            <div class="alert2">
                <span class="closebtn"> 
                    <strong>Error</strong> No ingreso datos validos del cliente
            </div>
        </c:if>
        <c:if test="${successCrearCliente == 1}">
            <div class="alert1">
                <span class="closebtn"> 
                    <strong>Creado</strong> El cliente y su primera cuenta se registraron exitosamente.
                    (El codigo del cliente es <strong>${codigoCreado}</strong> y su codigo de cuenta es <strong>${cuentaCreado}</strong>)
            </div>
        </c:if>
              <c:if test="${successCrearCliente == 2}">
            <div class="alert2">
                <span class="closebtn"> 
                    <strong>Error</strong> No puede ingresar datos vacios en los campos
            </div>
        </c:if>
    </body>


</html>
