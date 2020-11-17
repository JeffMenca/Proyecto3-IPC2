/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresCliente;

import Modelos.AsociacionModel;
import Modelos.CuentaModel;
import Objetos.Cuenta;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

/**
 *
 * @author jeffrey
 */
@WebServlet(name = "BuscarCuentaAsociada", urlPatterns = {"/BuscarCuentaAsociada"})
public class BuscarCuentaAsociada extends HttpServlet {

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
            out.println("<title>Servlet BuscarCuentaAsociada</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BuscarCuentaAsociada at " + request.getContextPath() + "</h1>");
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
        Long cliente = Long.parseLong(request.getParameter("cliente"));
        Long cuenta = Long.parseLong(request.getParameter("cuenta"));
        CuentaModel cuentaModel = new CuentaModel();
        AsociacionModel asociacionModel = new AsociacionModel();
        ArrayList unirListas = new ArrayList();

        try {
            ArrayList asociacionesEnvia = asociacionModel.obtenerAsociacionesEnvia(cuenta);
            ArrayList asociacionesRecibe = asociacionModel.obtenerAsociacionesRecibe(cuenta);
            unirListas.addAll(asociacionesEnvia);
            unirListas.addAll(asociacionesRecibe);
            ArrayList<Cuenta> cuentasAsociadas = new ArrayList<>();

            for (int i = 0; i < unirListas.size(); i++) {
                cuentasAsociadas.add(cuentaModel.obtenerCuenta2(Long.parseLong(unirListas.get(i).toString())));
            }
            
            request.getSession().setAttribute("Cuenta1", cuentaModel.buscarCuenta2(cuenta, cliente));
            request.getSession().setAttribute("CuentasAso", cuentasAsociadas);
            request.getSession().setAttribute("Cuentas", cuentaModel.obtenerCuentasClientesin(cliente, cuenta));
            request.getRequestDispatcher("/cliente/VerCuentasAsociadas.jsp").forward(request, response);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
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
