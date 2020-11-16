/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.GerenteModel;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jeffrey
 */
@WebServlet(name = "HorarioAcciones", urlPatterns = {"/HorarioAcciones"})
public class HorarioAcciones extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet HorarioCargarDatos</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HorarioCargarDatos at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            GerenteModel gerenteModel = new GerenteModel();
            String codigo = String.valueOf(request.getSession().getAttribute("user"));
            String opcion = request.getParameter("opcion");
            long user = Long.valueOf(codigo);
            try {
                if (gerenteModel.enHora(user) == true) {
                    if (opcion.equals("1")) {
                        request.getRequestDispatcher("/gerente/CargarDatos.jsp").forward(request, response);
                    } else if (opcion.equals("2")) {
                        request.getRequestDispatcher("/gerente/CrearCliente.jsp").forward(request, response);
                    } else if (opcion.equals("3")) {
                        request.getRequestDispatcher("/VerClientes").forward(request, response);
                    } else if (opcion.equals("4")) {
                        request.getRequestDispatcher("/gerente/CrearCajero.jsp").forward(request, response);
                    } else if (opcion.equals("5")) {
                        request.getRequestDispatcher("/gerente/CrearGerente.jsp").forward(request, response);
                    } else if (opcion.equals("6")) {
                        request.getRequestDispatcher("/CargarEditarGerente").forward(request, response);
                    } else if (opcion.equals("7")) {
                        request.getRequestDispatcher("/VerEditarCajeros").forward(request, response);
                    } else if (opcion.equals("8")) {
                        request.getRequestDispatcher("/VerEditarClientes").forward(request, response);
                    } else if (opcion.equals("9")) {
                        request.getRequestDispatcher("/VisualizarClientes").forward(request, response);
                    } else if (opcion.equals("10")) {
                        request.getRequestDispatcher("/VisualizarCajeros").forward(request, response);
                    } else if (opcion.equals("11")) {
                        request.getRequestDispatcher("/VisualizarGerentes").forward(request, response);
                    }

                } else {
                    request.getRequestDispatcher("/gerente/ErrorHorario.jsp").forward(request, response);
                }
            } catch (Exception e) {
            }
        } catch (Exception e) {
            if (request.getSession().getAttribute("user") == null) {
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            }
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
