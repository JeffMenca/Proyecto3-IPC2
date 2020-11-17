package ControladoresCliente;

import Modelos.AsociacionModel;
import Modelos.SolicitudModel;
import Objetos.AsociacionCuentas;
import Objetos.SolicitudAsociacion;
import com.sun.tools.doclint.Entity;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
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
@WebServlet(name = "AceptarSolicitud", urlPatterns = {"/AceptarSolicitud"})
public class AceptarSolicitud extends HttpServlet {

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
            out.println("<title>Servlet AceptarSolicitud</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AceptarSolicitud at " + request.getContextPath() + "</h1>");
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
        int codigoSolicitud = Integer.valueOf((String) request.getParameter("solicitud"));
        AsociacionModel modeloAsociacion = new AsociacionModel();
        SolicitudModel solicitudModel = new SolicitudModel();
        try {
            Boolean comprobacion = false;
            SolicitudAsociacion solicitud = solicitudModel.obtenerSolicitudCodigo(codigoSolicitud);
            comprobacion = modeloAsociacion.comprobarAsociacion(solicitud.getCuenta_envia_codigo(), solicitud.getCuenta2_recibe_codigo1());

            if (comprobacion == false) {
                solicitud.setEstado("Aceptada");
                solicitudModel.actualizarAsociacion(solicitud, codigoSolicitud);
                Date fecha = Date.valueOf(LocalDate.now());
                AsociacionCuentas asociacion = new AsociacionCuentas(0, fecha, codigoSolicitud);
                modeloAsociacion.crearAsociacion(asociacion);
                request.setAttribute("successAsociacion", 3);
                request.getRequestDispatcher("/cliente/VerSolicitudesAsociacion.jsp").forward(request, response);
            } else if (comprobacion == true) {

                request.setAttribute("successAsociacion", 4);
                request.getRequestDispatcher("/cliente/VerSolicitudesAsociacion.jsp").forward(request, response);
                solicitud.setEstado("Aceptada");
                solicitudModel.actualizarAsociacion(solicitud, codigoSolicitud);
            }

        } catch (Exception e) {
            request.setAttribute("successAsociacion", 0);
            request.getRequestDispatcher("/cliente/VerSolicitudesAsociacion.jsp").forward(request, response);
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
