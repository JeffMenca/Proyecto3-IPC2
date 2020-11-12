<%-- 
    Document   : index
    Author     : JeffMenca
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>El Billeton</title>
        <link rel="stylesheet" href="styles/MenuBarStyle.css">
        <link rel="stylesheet" href="styles/LoginStyle.css">
    </head>
    
    <body>
        <div class="header">
            <img class="logo" src="img/logo.png" width="60" height="60" />
        </div>
        <form method="POST" action="Login">

            <div class="login-box">
                <h1>Ingreso</h1>
                <%-- 
                     Combobox de tipo
                --%>
                <div class="box">
                    <select name="tipo">
                        <option value="ninguno">Tipo de usuario:</option>
                        <option value="Gerente">Gerente</option>
                        <option value="Cajero">Cajero</option>
                        <option value="Cliente">Cliente</option>
                    </select>
                </div>
                <%-- 
                    Textbox de usuario
                --%>
                <div class="textbox">
                    <i class="fas fa-user"></i>
                    <input type="text" placeholder="Usuario" name="usuario">
                </div>
                <%-- 
                    Textbox de password
                --%>
                <div class="textbox">
                    <i class="fas fa-lock"></i>
                    <input type="password" placeholder="Contraseña" name="password">
                </div>
                <%-- 
                    Button de ingresar
                --%>
                <input type="submit" class="btn" value="Ingresar">
            </div>
        </form>
        <%-- 
          Mensajes de error
        --%>
        <c:if test="${success == 0}">
            <h4 class="error">Su usuario, tipo o contraseña son incorrectos</h4>
        </c:if>
        <c:if test="${success == 2}">
            <h4 class="error">Existen campos que no ha llenado</h4>
        </c:if>
    </body>
</html>
