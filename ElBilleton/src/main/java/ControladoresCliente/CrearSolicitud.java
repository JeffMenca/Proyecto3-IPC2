package ControladoresCliente;

import Modelos.CuentaModel;
import Modelos.SolicitudModel;
import Objetos.Cuenta;
import Objetos.SolicitudAsociacion;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

/**
 *
 * @author jeffrey
 */
@WebServlet(name = "CrearSolicitud", urlPatterns = {"/CrearSolicitud"})
@MultipartConfig
public class CrearSolicitud extends HttpServlet {

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
            out.println("<title>Servlet InsertarCliente</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InsertarCliente at " + request.getContextPath() + "</h1>");
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
            SolicitudModel solicitudModel = new SolicitudModel();
            Date fecha = Date.valueOf(LocalDate.now());
            Long codigoCuentaEnvia = Long.valueOf((String) request.getParameter("cuenta1"));
            Long codigoCuentaRecibe = Long.valueOf((String) request.getParameter("cuenta2"));
            String estado = "Pendiente";
            SolicitudAsociacion nuevaSolicitud = new SolicitudAsociacion(0, fecha, estado, codigoCuentaEnvia, codigoCuentaRecibe);
            int veces = solicitudModel.comprobarSolicitud(codigoCuentaEnvia, codigoCuentaRecibe);
            if (veces <= 3) {
                try {
                    solicitudModel.crearSolicitud(nuevaSolicitud);
                    request.setAttribute("successCrearSolicitud", 1);
                    request.getRequestDispatcher("/cliente/EnviarSolicitudAsociacion.jsp").forward(request, response);
                } catch (Exception e) {
                    request.setAttribute("successCrearSolicitud", 0);
                    request.getRequestDispatcher("/cliente/EnviarSolicitudAsociacion.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("successCrearSolicitud", 2);
                request.getRequestDispatcher("/cliente/EnviarSolicitudAsociacion.jsp").forward(request, response);
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
