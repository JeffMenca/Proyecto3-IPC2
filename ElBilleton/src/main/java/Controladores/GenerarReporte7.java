package Controladores;

import Modelos.CajeroModel;
import Modelos.ClienteModel;
import Modelos.LimitesGerenteModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
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
@WebServlet(name = "GenerarReporte7", urlPatterns = {"/GenerarReporte7"})
public class GenerarReporte7 extends HttpServlet {

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
            out.println("<title>Servlet GenerarReporte2</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GenerarReporte2 at " + request.getContextPath() + "</h1>");
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
        CajeroModel cajeroModel = new CajeroModel();
        Date fechaInicio = Date.valueOf((String) request.getParameter("fechaInicio"));
        Date fechaFinal = Date.valueOf((String) request.getParameter("fechaFinal"));

        if (fechaInicio.after(fechaFinal)) {
            request.setAttribute("successIntervalo", 2);
            request.setAttribute("fecha1", fechaInicio);
            request.setAttribute("fecha2", fechaFinal);
            request.getRequestDispatcher("/gerente/IntervaloReporte7.jsp").forward(request, response);
        } else {
            try {
                ArrayList cajeros = cajeroModel.obtenerReporte7(fechaInicio, fechaFinal);
                request.setAttribute("listaCajeros", cajeros);
                request.setAttribute("fecha1", fechaInicio);
                request.setAttribute("fecha2", fechaFinal);
                request.getRequestDispatcher("/gerente/VerReporte7.jsp").forward(request, response);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
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
