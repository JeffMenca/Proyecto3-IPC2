/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.ClienteModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jeffrey
 */
@WebServlet(name = "VerClientesReporte6", urlPatterns = {"/VerClientesReporte6"})
public class VerClientesReporte6 extends HttpServlet {

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
            out.println("<title>Servlet VerClientes</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet VerClientes at " + request.getContextPath() + "</h1>");
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
        ClienteModel clienteModel = new ClienteModel();
        try {
            ArrayList clientes = clienteModel.obtenerClientes();
            clientes = clienteModel.obtenerClientes();
            request.setAttribute("listaClientes", clientes);
            request.getRequestDispatcher("/gerente/VerClientesReporte6.jsp").forward(request, response);
        } catch (Exception e) {
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
        ClienteModel clienteModel = new ClienteModel();
        try {
            ArrayList clientes = clienteModel.obtenerClientes();
            String nombre = request.getParameter("filtro");
            String limite = request.getParameter("filtro2");
            if (nombre == null) {
                nombre = "";
            }
            if (limite == null) {
                limite = "0";
            } else if (limite == "") {
                limite = "0";
            }
            Double limite2 = Double.parseDouble(limite);
            try {
                request.setAttribute("listaClientes", clienteModel.obtenerReporte6(limite2, nombre));
                request.getRequestDispatcher("/gerente/VerClientesReporte6.jsp").forward(request, response);
            } catch (Exception e) {
            }

        } catch (Exception e) {
        }
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
