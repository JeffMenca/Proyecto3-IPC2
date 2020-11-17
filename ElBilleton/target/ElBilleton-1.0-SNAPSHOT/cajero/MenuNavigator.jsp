<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Menu</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/MenuNavStyle.css?6.2">
    </head>
    <body>
        <div class="header">
            <img class="logo" src="${pageContext.request.contextPath}/img/logo.png" width="60" height="60" />
            <div class="header-right">
                <div class="navbar">
                    <a href="${pageContext.request.contextPath}/cajero/CajeroIndex.jsp">Inicio</a>
                    <a href="${pageContext.request.contextPath}/HorarioAccionesCajero?opcion=1">Realizar deposito</a>
                    <a href="${pageContext.request.contextPath}/HorarioAccionesCajero?opcion=2">Realizar retiro</a>
                   
                    <a href="${pageContext.request.contextPath}/Logout">Cerrar sesion</a>
                </div>
            </div>
        </div>
       
        <%
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

            if (session.getAttribute("user") == null) {
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            }
        %>
    </body>
</html>
