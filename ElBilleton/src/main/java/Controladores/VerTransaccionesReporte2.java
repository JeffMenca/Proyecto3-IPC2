
package Controladores;

import Modelos.LimitesGerenteModel;
import Modelos.TransaccionModel;
import Objetos.LimitesGerente;
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
@WebServlet(name = "VerTransaccionesReporte2", urlPatterns = {"/VerTransaccionesReporte2"})
public class VerTransaccionesReporte2 extends HttpServlet {

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
            out.println("<title>Servlet VerTransaccionesReporte2</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet VerTransaccionesReporte2 at " + request.getContextPath() + "</h1>");
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
            Long codigoCliente = Long.valueOf((String) request.getParameter("codigo"));
            TransaccionModel modelTransaccion = new TransaccionModel();
            
            LimitesGerenteModel limitesModel = new LimitesGerenteModel();
            LimitesGerente limites = limitesModel.obtenerLimites();
            ArrayList transacciones = modelTransaccion.obtenerTransaccionLimite(limites.getLimite_reporte2(), codigoCliente);
            request.setAttribute("listaTransacciones", transacciones);
            request.setAttribute("cliente", codigoCliente);
            request.getRequestDispatcher("/gerente/VerTransaccionesReporte2.jsp").forward(request, response);
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
