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
    <center><h1 style="color:white;">Bienvenido gerente</h1></h1></center>
    <br> 
    <center><div style="color:white;">
            <strong><p>Codigo: ${gerente.getCodigo()}</p>
                <p>Nombre: ${gerente.getNombre()}</p>
                <p>Turno: ${gerente.getTurno()}</p>
                <p>Esta en turno?: ${enHora}</p></strong>
                <br>
            <p>Aqui podra administrar los usuarios que accedan al portal .</p>
            <p>Podra crear usuarios como clientes,cajeros y gerentes</p>
            <p>Podra generar reportes y exportarlos de facil manera .</p>
            <p>Tambien podra acceder la informacion de los usuarios y editarla .</p>
            <p>Sus datos son privados y seguros.</p>
            <p>bienvenido</p>
        </div></center>
    <center><img src="${pageContext.request.contextPath}/img/logo.png" width="150" height="150" /></center>
    


</body>
</html>
