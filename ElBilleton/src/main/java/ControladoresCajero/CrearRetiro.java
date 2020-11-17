/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresCajero;

import Modelos.CuentaModel;
import Modelos.TransaccionModel;
import Objetos.Cuenta;
import Objetos.Transaccion;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jeffrey
 */
@WebServlet(name = "CrearRetiro", urlPatterns = {"/CrearRetiro"})
public class CrearRetiro extends HttpServlet {

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
            out.println("<title>Servlet RealizarTransaccion</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RealizarTransaccion at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        try {
            CuentaModel cuentamodel = new CuentaModel();
            Long codigoCajero = Long.parseLong((String) request.getSession().getAttribute("user"));
            Long codigoCuenta = Long.parseLong((String) request.getParameter("cuenta"));
            TransaccionModel transaccionModel = new TransaccionModel();
            Date fecha = Date.valueOf(LocalDate.now());
            Time hora = Time.valueOf(LocalTime.now());
            String monto = request.getParameter("monto");
            if (monto != null && !"0.0".equals(monto) && !"".equals(monto)) {
                Double dinero = Double.parseDouble(monto);

                try {
                    Cuenta cuenta = cuentamodel.obtenerCuenta2(codigoCuenta);
                    if (dinero > cuenta.getMonto()) {
                        request.setAttribute("successRetiro", 2);
                        request.getRequestDispatcher("/cajero/RealizarRetiro.jsp").forward(request, response);
                    } else {
                        cuenta.setMonto(cuenta.getMonto() - dinero);
                        Transaccion transaccionDeposito = new Transaccion(0, fecha, hora, "Debito", dinero, cuenta.getCodigo(), codigoCajero);
                        transaccionModel.agregarTransaccion(transaccionDeposito);
                        cuentamodel.modificarMonto(cuenta);
                        request.setAttribute("successRetiro", 1);
                        request.getRequestDispatcher("/cajero/RealizarRetiro.jsp").forward(request, response);
                    }

                } catch (Exception e) {
                }

            } else {
                request.setAttribute("successRetiro", 0);
                request.getRequestDispatcher("/cajero/RealizarRetiro.jsp").forward(request, response);
            }
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
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
