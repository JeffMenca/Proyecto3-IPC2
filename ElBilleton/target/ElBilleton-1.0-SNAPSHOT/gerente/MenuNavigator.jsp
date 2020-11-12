<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Menu</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="../styles/MenuNavStyle.css?3.0">

    </head>
    <body>
        <div class="header">
            <img class="logo" src="${pageContext.request.contextPath}/img/logo.png" width="60" height="60" />
            <div class="header-right">
                <div class="navbar">
                    <a href="${pageContext.request.contextPath}/gerente/GerenteIndex.jsp">Inicio</a>
                    <div class="dropdown">
                        <button class="dropbtn" onclick="dropCrear()">Crear usuarios
                            <i class="fa fa-caret-down"></i>
                        </button>
                        <div class="dropdown-content" id="crear">
                            <a href="VerCitaConsulta.jsp">Crear cliente </a>
                            <a href="VerCitaIntervalo.jsp">Crear cuenta</a>
                            <a href="VerPacienteNuevo.jsp">Crear cajero</a>
                            <a href="CancelarCita.jsp">Crear gerente</a>
                        </div>
                    </div>
                    <div class="dropdown">
                        <button class="dropbtn" onclick="dropEditar()">Editar usuarios
                            <i class="fa fa-caret-down"></i>
                        </button>
                        <div class="dropdown-content" id="editar">
                            <a href="VerCitaConsulta.jsp">Actualizar informacion</a>
                            <a href="VerPacienteNuevo.jsp">Actualizar cajero</a>
                            <a href="CancelarCita.jsp">Actualizar cliente</a>
                        </div>
                    </div>
                    <div class="dropdown">
                        <button class="dropbtn" onclick="dropReporte()">Reportes
                            <i class="fa fa-caret-down"></i>
                        </button>
                        <div class="dropdown-content" id="reportes">
                            <a href="VerPacienteHistorial.jsp">Ver historial de cambios realizados en una entidad
                                especifica</a>
                            <a href="VerCitaActual.jsp">Ver clientes con transacciones monetarios mayores
                                a un limite establecido</a>
                            <a href="VerPacienteHistorial.jsp">Ver clientes con transacciones monetarias sumadas
                                mayores a un limite establecido</a>
                            <a href="VerPacienteHistorial.jsp">Ver los 10 clientes con mas dinero en sus cuentas</a>
                            <a href="VerPacienteHistorial.jsp">Ver los clientes que no han realizado transacciones
                                dentro de un intervalo de tiempo</a>
                            <a href="VerPacienteHistorial.jsp">Ver el historial de transacciones por cliente</a>
                            <a href="VerPacienteHistorial.jsp">Ver el cajero que mas transacciones ha realizado en
                                un intervalo de tiempo</a>
                        </div>
                    </div>
                    <a href="../index.jsp">Cerrar sesion</a>
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
                myDropdown.classList.remove('show');
                myDropdown1.classList.remove('show');
            }

            function dropEditar() {
                document.getElementById("editar").classList.toggle("show");
                var myDropdown = document.getElementById("crear");
                var myDropdown1 = document.getElementById("reportes");
                myDropdown.classList.remove('show');
                myDropdown1.classList.remove('show');
            }
            function dropReporte() {
                document.getElementById("reportes").classList.toggle("show");
                var myDropdown = document.getElementById("crear");
                var myDropdown1= document.getElementById("editar");
                myDropdown.classList.remove('show');
                myDropdown1.classList.remove('show');
            }

            // Close the dropdown if the user clicks outside of it
            window.onclick = function (e) {
                if (!e.target.matches('.dropbtn')) {
                    var myDropdown1 = document.getElementById("crear");
                    var myDropdown2 = document.getElementById("editar");
                    var myDropdown3 = document.getElementById("reportes");
                    if (myDropdown1.classList.contains('show')) {
                        myDropdown1.classList.remove('show');
                    }
                    if (myDropdown2.classList.contains('show')) {
                        myDropdown2.classList.remove('show');
                    }
                    if (myDropdown3.classList.contains('show')) {
                        myDropdown3.classList.remove('show');
                    }
                }
            };
        </script>
    </body>
</html>