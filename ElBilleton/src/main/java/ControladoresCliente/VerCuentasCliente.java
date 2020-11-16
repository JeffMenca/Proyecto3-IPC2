package ControladoresCliente;

import Modelos.ClienteModel;
import Modelos.CuentaModel;
import Objetos.Cliente;
import Objetos.Cuenta;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet(name = "VerCuentasCliente", urlPatterns = {"/VerCuentasCliente"})
public class VerCuentasCliente extends HttpServlet {

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
        CuentaModel cuentaModel = new CuentaModel();
        ClienteModel clienteModel=new ClienteModel();
        Long codigoCliente = Long.valueOf((String) request.getSession().getAttribute("user"));
        Long cuentaSeleccionada = Long.valueOf((String) request.getParameter("cuenta"));
        Cuenta cuentaEncontrada = null;
        try {
            cuentaEncontrada = cuentaModel.obtenerCuenta(cuentaSeleccionada,codigoCliente);
            ArrayList cuentas = cuentaModel.obtenerCuentasCliente(codigoCliente);
            request.setAttribute("listaCuentas", cuentas);
            request.setAttribute("cuentaEncontrada", cuentaEncontrada);
            if (cuentaEncontrada != null) {
                Cliente clienteEncontrado=clienteModel.obtenerCliente(cuentaEncontrada.getCliente_codigo());
                request.setAttribute("clienteEncontrado", clienteEncontrado);
                request.getRequestDispatcher("/cliente/EnviarSolicitudAsociacion.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/cliente/ErrorEncontrarCuenta.jsp").forward(request, response);
            }

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
