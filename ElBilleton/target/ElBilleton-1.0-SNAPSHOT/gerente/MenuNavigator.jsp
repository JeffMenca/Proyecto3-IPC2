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
                    <a href="${pageContext.request.contextPath}/gerente/GerenteIndex.jsp">Inicio</a>
                    <a href="${pageContext.request.contextPath}/HorarioAcciones?opcion=1">Cargar datos</a>
                    <div class="dropdown">
                        <button class="dropbtn" onclick="dropCrear()">Crear usuarios
                            <i class="fa fa-caret-down"></i>
                        </button>
                        <div class="dropdown-content" id="crear">
                            <a href="${pageContext.request.contextPath}/HorarioAcciones?opcion=2">Crear cliente </a>
                            <a href="${pageContext.request.contextPath}/HorarioAcciones?opcion=3">Crear cuenta</a>
                            <a href="${pageContext.request.contextPath}/HorarioAcciones?opcion=4">Crear cajero</a>
                            <a href="${pageContext.request.contextPath}/HorarioAcciones?opcion=5">Crear gerente</a>
                        </div>
                    </div>
                    <div class="dropdown">
                        <button class="dropbtn" onclick="dropVer()">Ver usuarios
                            <i class="fa fa-caret-down"></i>
                        </button>
                        <div class="dropdown-content" id="ver">
                            <a href="${pageContext.request.contextPath}/HorarioAcciones?opcion=9">Ver clientes </a>
                            <a href="${pageContext.request.contextPath}/HorarioAcciones?opcion=10">Ver cajeros</a>
                            <a href="${pageContext.request.contextPath}/HorarioAcciones?opcion=11">Ver gerentes</a>
                        </div>
                    </div>
                    <div class="dropdown">
                        <button class="dropbtn" onclick="dropEditar()">Editar usuarios
                            <i class="fa fa-caret-down"></i>
                        </button>
                        <div class="dropdown-content" id="editar">
                            <a href="${pageContext.request.contextPath}/HorarioAcciones?opcion=6">Actualizar informacion</a>
                            <a href="${pageContext.request.contextPath}/HorarioAcciones?opcion=7">Actualizar cajero</a>
                            <a href="${pageContext.request.contextPath}/HorarioAcciones?opcion=8">Actualizar cliente</a>
                        </div>
                    </div>
                    <div class="dropdown">
                        <button class="dropbtn" onclick="dropReporte()">Reportes
                            <i class="fa fa-caret-down"></i>
                        </button>
                        <div class="dropdown-content" id="reportes">
                            <a href="${pageContext.request.contextPath}/CargarLimitesGerente">Establecer limites</a>
                            <a href="${pageContext.request.contextPath}/gerente/SeleccionarEntidad.jsp">Ver historial de cambios realizados en una entidad
                                especifica</a>
                            <a href="${pageContext.request.contextPath}/GenerarReporte2">Ver clientes con transacciones monetarias mayores
                                a un limite establecido</a>
                            <a href="${pageContext.request.contextPath}/GenerarReporte3">Ver clientes con transacciones monetarias sumadas
                                mayores a un limite establecido</a>
                            <a href="${pageContext.request.contextPath}/GenerarReporte4">Ver los 10 clientes con mas dinero en sus cuentas</a>
                            <a href="${pageContext.request.contextPath}/gerente/IntervaloReporte5.jsp">Ver los clientes que no han realizado transacciones
                                dentro de un intervalo de tiempo</a>
                            <a href="${pageContext.request.contextPath}/VerClientesReporte6">Ver el historial de transacciones por cliente</a>
                            <a href="${pageContext.request.contextPath}/gerente/IntervaloReporte7.jsp">Ver el cajero que mas transacciones ha realizado en
                                un intervalo de tiempo</a>
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
                var myDropdown = document.getElementById("editar");
                var myDropdown1 = document.getElementById("reportes");
                var myDropdown2 = document.getElementById("ver");
                myDropdown.classList.remove('show');
                myDropdown1.classList.remove('show');
                myDropdown2.classList.remove('show');
            }

            function dropEditar() {
                document.getElementById("editar").classList.toggle("show");
                var myDropdown = document.getElementById("crear");
                var myDropdown1 = document.getElementById("reportes");
                var myDropdown2 = document.getElementById("ver");
                myDropdown.classList.remove('show');
                myDropdown1.classList.remove('show');
                myDropdown2.classList.remove('show');
            }
            function dropReporte() {
                document.getElementById("reportes").classList.toggle("show");
                var myDropdown = document.getElementById("crear");
                var myDropdown1 = document.getElementById("editar");
                var myDropdown2 = document.getElementById("ver");
                myDropdown.classList.remove('show');
                myDropdown1.classList.remove('show');
                myDropdown2.classList.remove('show');
            }
            function dropVer() {
                document.getElementById("ver").classList.toggle("show");
                var myDropdown = document.getElementById("editar");
                var myDropdown1 = document.getElementById("reportes");
                var myDropdown2 = document.getElementById("crear");
                myDropdown.classList.remove('show');
                myDropdown1.classList.remove('show');
                myDropdown2.classList.remove('show');
            }

            // Close the dropdown if the user clicks outside of it
            window.onclick = function (e) {
                if (!e.target.matches('.dropbtn')) {
                    var myDropdown1 = document.getElementById("crear");
                    var myDropdown2 = document.getElementById("editar");
                    var myDropdown3 = document.getElementById("reportes");
                    var myDropdown4 = document.getElementById("ver");
                    if (myDropdown1.classList.contains('show')) {
                        myDropdown1.classList.remove('show');
                    }
                    if (myDropdown2.classList.contains('show')) {
                        myDropdown2.classList.remove('show');
                    }
                    if (myDropdown3.classList.contains('show')) {
                        myDropdown3.classList.remove('show');
                    }
                    if (myDropdown4.classList.contains('show')) {
                        myDropdown4.classList.remove('show');
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
