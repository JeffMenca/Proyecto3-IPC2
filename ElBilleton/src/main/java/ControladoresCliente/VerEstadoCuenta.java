/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresCliente;

import Modelos.ClienteModel;
import Modelos.CuentaModel;
import Modelos.TransaccionModel;
import Objetos.Cliente;
import Objetos.Cuenta;
import Objetos.Transaccion;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jeffrey
 */
@WebServlet(name = "VerEstadoCuenta", urlPatterns = {"/VerEstadoCuenta"})
public class VerEstadoCuenta extends HttpServlet {

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
            out.println("<title>Servlet VerEstadoCuenta</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet VerEstadoCuenta at " + request.getContextPath() + "</h1>");
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
        CuentaModel cuentaModel = new CuentaModel();
        ClienteModel clienteModel = new ClienteModel();
        String nombreCliente="";
        try {
            Cliente clienteNuevo = clienteModel.obtenerCliente(Long.valueOf((String) request.getSession().getAttribute("user")));
            nombreCliente=clienteNuevo.getNombre();
        } catch (SQLException ex) {
            Logger.getLogger(VerEstadoCuenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        TransaccionModel transaccionModel = new TransaccionModel();
        Long cuenta_codigo = Long.parseLong(request.getParameter("cuenta"));
        ArrayList<Transaccion> listaTransacciones = transaccionModel.obtenerTransaccionesCuenta(cuenta_codigo);
        try {
            Cuenta cuenta = cuentaModel.obtenerCuenta2(cuenta_codigo);
            request.setAttribute("listaTransacciones", listaTransacciones);
            request.setAttribute("cuenta", cuenta);
            request.setAttribute("nombre", nombreCliente);
            request.getRequestDispatcher("/cliente/EstadoDeCuenta.jsp").forward(request, response);
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
