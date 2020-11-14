package Controladores;

import Clases.GeneradorArchivo;
import Modelos.CajeroModel;
import Modelos.ClienteModel;
import Modelos.HistorialCajeroModel;
import Modelos.HistorialClienteModel;
import Objetos.Cajero;
import Objetos.Cliente;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.swing.JOptionPane;

/**
 *
 * @author jeffrey
 */
@WebServlet(name = "ActualizarCajero", urlPatterns = {"/ActualizarCajero"})
@MultipartConfig
public class ActualizarCajero extends HttpServlet {

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
            CajeroModel cajeroModel = new CajeroModel();
            HistorialCajeroModel historialCajeroModel = new HistorialCajeroModel();

            Long codigoCajero = Long.parseLong((String) request.getParameter("codigo"));
            String nombre = request.getParameter("nombre");
            String turno = request.getParameter("turno");
            String DPI = request.getParameter("DPI");
            String direccion = request.getParameter("direccion");
            String sexo = request.getParameter("sexo");
            String password = request.getParameter("password");

            Cajero nuevoCajero = new Cajero(0, nombre, turno, DPI, direccion, sexo, password);
            try {
                cajeroModel.actualizarCajero(nuevoCajero, codigoCajero);
                historialCajeroModel.agregarHistorialCajeroCodigo(nuevoCajero, codigoCajero);
                request.setAttribute("successEditarCajero", 1);
                request.getRequestDispatcher("/gerente/EditarCajero.jsp").forward(request, response);
            } catch (Exception e) {
                request.setAttribute("successEditarCajero", 0);
                request.getRequestDispatcher("/gerente/EditarCajero.jsp").forward(request, response);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
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
