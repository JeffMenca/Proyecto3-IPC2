<%-- 
    Document   : GerenteIndex
    Created on : 12/11/2020, 00:17:34
    Author     : jeffrey
--%>

<%@page import="Objetos.Gerente"%>
<%@page import="javax.swing.JOptionPane"%>
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
        <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br>
        <form method="POST" action="CargaDatos" enctype="multipart/form-data">

            <div class="container">
                <h1>Cargar datos</h1>
                <div class="row">
                    <div class="col-25">
                        <label for="fname">Seleccionar archivos</label>
                    </div>
                    <div class="col-77">
                        <input type="file" id="lfile" name="archivo"  multiple required>
                    </div>
                </div>
                <div class="row">
                    <br> 
                    <input type="submit" class="button2" name="importar" value="Importar datos">
                </div>


        </form>



    </body>
</html>
