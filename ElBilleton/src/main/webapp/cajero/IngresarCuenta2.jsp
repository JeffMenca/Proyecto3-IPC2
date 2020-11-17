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
        <form method="GET" action="${pageContext.request.contextPath}/SeleccionarCuentasRetiro">

            <br> <br> <br> <br> <br> <br> <br> 

            <div class="container">
                <h1>Ingrese la cuenta para realizar el retiro</h1>

                <div class="row">
                    <div class="col-25">
                        <label for="fnombre">Cuenta</label>
                    </div>
                    <div class="col-77">
                        <input type="text" id="lname" name="cuenta" placeholder="Codigo de la cuenta" required>
                    </div>
                </div>
                <div class="row">
                    <br> 
                    <input type="submit" class="button2" value="Buscar cuenta">
                </div>

            </div>

        </form>
       
             
       
    </body>


</html>
