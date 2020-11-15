<%-- 
    Author     : jeffrey
--%><%@page import="java.time.LocalDate"%>


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
         <form method="POST" action="${pageContext.request.contextPath}/VerReporte1">

            <br> <br> <br> <br> <br> <br> <br> 

            <div class="container">
                <h1>Selecciona la entidad del reporte</h1>

                <div class="row">
                    <div class="col-25">
                        <label for="fsexo">Entidad</label>
                    </div>
                    <div class="col-77">
                        <select id="country2" name="entidad">
                            <option value="gerente" >Gerente</option>  
                            <option value="cliente" >Cliente</option>
                            <option value="cajero" >Cajero</option>
                        </select>
                    </div>
                </div>
                <div class="row">
                    <br> 
                    <input type="submit" class="button2" value="Seleccionar entidad">
                </div>


            </div>

        </form>

    </body>


</html>
