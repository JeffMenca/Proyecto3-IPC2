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
                    <a href="${pageContext.request.contextPath}/cliente/ClienteIndex.jsp">Inicio</a>
                    <a href="${pageContext.request.contextPath}/HorarioAcciones?opcion=1">Crear transaccion</a>
                    <div class="dropdown">
                        <button class="dropbtn" onclick="dropCrear()">Asociacion de cuentas
                            <i class="fa fa-caret-down"></i>
                        </button>
                        <div class="dropdown-content" id="crear">
                            <a href="${pageContext.request.contextPath}/HorarioAcciones?opcion=2">Ver solicitudes </a>
                            <a href="${pageContext.request.contextPath}/HorarioAcciones?opcion=3">Solicitar asociacion</a>
                        </div>
                    </div>
                    <div class="dropdown">
                        <button class="dropbtn" onclick="dropReporte()">Reportes
                            <i class="fa fa-caret-down"></i>
                        </button>
                        <div class="dropdown-content" id="reportes">
                            <a href="${pageContext.request.contextPath}/gerente/SeleccionarEntidad.jsp">Ver historial de cambios realizados en una entidad
                                especifica</a>
                            <a href="${pageContext.request.contextPath}/GenerarReporte2">Ver clientes con transacciones monetarias mayores
                                a un limite establecido</a>
                        </div>
                    </div>
                    <a href="${pageContext.request.contextPath}/Logout">Cerrar sesion</a>
                </div>
            </div>
        </div>
        <script>
            /* When the user clicks on the button, 
             toggle between hiding and showing the dropdown content */

            function dropCrear() {
                document.getElementById("crear").classList.toggle("show");
                var myDropdown1 = document.getElementById("reportes");
                myDropdown1.classList.remove('show');
            }

            function dropReporte() {
                document.getElementById("reportes").classList.toggle("show");
                var myDropdown = document.getElementById("crear");
                myDropdown.classList.remove('show');
            }

            // Close the dropdown if the user clicks outside of it
            window.onclick = function (e) {
                if (!e.target.matches('.dropbtn')) {
                    var myDropdown1 = document.getElementById("crear");
                    var myDropdown3 = document.getElementById("reportes");
                    if (myDropdown1.classList.contains('show')) {
                        myDropdown1.classList.remove('show');
                    }
                    if (myDropdown3.classList.contains('show')) {
                        myDropdown3.classList.remove('show');
                    }
                }
            };
        </script>
        <%
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

            if (session.getAttribute("user") == null) {
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            }
        %>
    </body>
</html>
