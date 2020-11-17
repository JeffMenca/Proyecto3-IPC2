package Controladores;

import Modelos.HistorialCajeroModel;
import Modelos.HistorialClienteModel;
import Modelos.HistorialGerenteModel;
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
@WebServlet(name = "GenerarReporte1", urlPatterns = {"/GenerarReporte1"})
public class GenerarReporte1 extends HttpServlet {

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
            out.println("<title>Servlet GenerarReporte1</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GenerarReporte1 at " + request.getContextPath() + "</h1>");
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
        
        Long codigoEntidad = Long.valueOf((String) request.getParameter("codigo"));
        String tipoEntidad = request.getParameter("tipo");
        if (tipoEntidad.equals("gerente")) {
            HistorialGerenteModel gerenteModel = new HistorialGerenteModel();
            try {
                ArrayList historialGerentes = gerenteModel.obtenerHistorialGerente(codigoEntidad);

                request.setAttribute("listaHistorial", historialGerentes);
                request.setAttribute("tipo", tipoEntidad);
                request.setAttribute("codigoEntidad", codigoEntidad);
                request.getRequestDispatcher("/gerente/VerReporte1.jsp").forward(request, response);
            } catch (Exception e) {
            }
        } else if (tipoEntidad.equals("cliente")) {
            HistorialClienteModel clienteModel = new HistorialClienteModel();
            try {
                ArrayList historialClientes = clienteModel.obtenerHistorialCliente(codigoEntidad);

                request.setAttribute("listaHistorial", historialClientes);
                request.setAttribute("tipo", tipoEntidad);
                request.setAttribute("codigoEntidad", codigoEntidad);
                request.getRequestDispatcher("/gerente/VerReporte1.jsp").forward(request, response);
            } catch (Exception e) {
            }
        } else if (tipoEntidad.equals("cajero")) {
            HistorialCajeroModel clienteModel = new HistorialCajeroModel();
            try {
                ArrayList historialCajeros = clienteModel.obtenerHistorialCajero(codigoEntidad);

                request.setAttribute("listaHistorial", historialCajeros);
                request.setAttribute("tipo", tipoEntidad);
                request.setAttribute("codigoEntidad", codigoEntidad);
                request.getRequestDispatcher("/gerente/VerReporte1.jsp").forward(request, response);
            } catch (Exception e) {
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
