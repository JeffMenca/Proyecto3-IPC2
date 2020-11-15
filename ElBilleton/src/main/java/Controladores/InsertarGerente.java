package Controladores;

import Modelos.GerenteModel;
import Modelos.HistorialGerenteModel;
import Objetos.Gerente;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jeffrey
 */
@WebServlet(name = "InsertarGerente", urlPatterns = {"/InsertarGerente"})
public class InsertarGerente extends HttpServlet {

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

        GerenteModel gerenteModel = new GerenteModel();
        HistorialGerenteModel historialGerente = new HistorialGerenteModel();
        String nombre = request.getParameter("nombre").trim();
        String turno = request.getParameter("turno");
        String DPI = request.getParameter("DPI");
        String direccion = request.getParameter("direccion").trim();
        String sexo = request.getParameter("sexo");
        String password = request.getParameter("password");

        if (nombre.trim().equals("") || direccion.trim().equals("")) {
            request.setAttribute("successCrearGerente", 2);
            request.getRequestDispatcher("/gerente/CrearGerente.jsp").forward(request, response);
        } else {
            Gerente nuevoGerente = new Gerente(0, nombre, turno, DPI, direccion, sexo, password);
            try {
                Long codigoGerente = gerenteModel.agregarGerente(nuevoGerente);
                historialGerente.agregarHistorialGerenteCodigo(nuevoGerente, codigoGerente);
                request.setAttribute("successCrearGerente", 1);
                request.getRequestDispatcher("/gerente/CrearGerente.jsp").forward(request, response);
            } catch (Exception e) {
                request.setAttribute("successCrearGerente", 0);
                request.getRequestDispatcher("/gerente/CrearGerente.jsp").forward(request, response);
            }
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
