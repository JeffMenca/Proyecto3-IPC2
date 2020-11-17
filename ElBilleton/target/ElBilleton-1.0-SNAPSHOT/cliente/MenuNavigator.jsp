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
                    <a href="${pageContext.request.contextPath}/VerCuentasCliente2">Crear transaccion</a>
                    <a href="${pageContext.request.contextPath}/VerCuentasCliente3">Estado de cuenta</a>
                    <div class="dropdown">
                        <button class="dropbtn" onclick="dropCrear()">Asociacion de cuentas
                            <i class="fa fa-caret-down"></i>
                        </button>
                        <div class="dropdown-content" id="crear">   
                            <a href="${pageContext.request.contextPath}/VerCuentasCliente">Ver solicitudes </a>
                            <a href="${pageContext.request.contextPath}/cliente/IngresarCuenta.jsp">Solicitar asociacion</a>
                        </div>
                    </div>
                    <div class="dropdown">
                        <button class="dropbtn" onclick="dropReporte()">Reportes
                            <i class="fa fa-caret-down"></i>
                        </button>
                        <div class="dropdown-content" id="reportes">
                            <a href="${pageContext.request.contextPath}/VerCuentasReporte1">Ver las últimas 15 transacciones más grandes realizadas
                                en el último año, por cuenta</a>
                            <a href="${pageContext.request.contextPath}/GenerarReporte2">Ver clientes con transacciones monetarias mayores
                                a un limite establecido</a>
                            <a href="${pageContext.request.contextPath}/cliente/IntervaloReporte3Cliente.jsp">Ver la cuenta con más dinero y todas sus
                                transacciones con fecha mínima a la fecha actual</a>
                            <a href="${pageContext.request.contextPath}/GenerarReporte4Cliente">Ver historial con listado de solicitud de asociación
                                de cuentas recibidas</a>
                            <a href="${pageContext.request.contextPath}/GenerarReporte5Cliente">Ver historial con listado de solicitud de asociación
                                de cuentas realizadas</a>
                        </div>
                    </div>
                    <a href="${pageContext.request.contextPath}/Logout">Cerrar sesion</a>
                </div>
            </div>
        </div>
        <script>
            /* Cuando se presiona el boton en el menu
             se cierran o abren sus submenus */

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

            // Se cierran los menus cuando se presiona fuera de ellos
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
