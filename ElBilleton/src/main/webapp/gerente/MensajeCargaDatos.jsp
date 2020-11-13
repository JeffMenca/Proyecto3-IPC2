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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/MenuNavStyle.css?3.0">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    </head> 
    <body>
        <%@include  file="MenuNavigator.jsp" %>
        <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br>
    <center><h1 style="color:white;">Se han cargado datos</h1></h1></center>
    <br> 
    <center><div style="color:green;">
            
            <strong><p></p>
                <p>Se ingresan los datos del archivo correctamente</p></strong>
        </div></center>
    


</body>
</html>
