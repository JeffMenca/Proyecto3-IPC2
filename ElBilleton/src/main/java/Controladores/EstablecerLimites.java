package Controladores;

import Modelos.GerenteModel;
import Modelos.HistorialGerenteModel;
import Modelos.LimitesGerenteModel;
import Objetos.Gerente;
import Objetos.LimitesGerente;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
@WebServlet(name = "EstablecerLimites", urlPatterns = {"/EstablecerLimites"})
@MultipartConfig
public class EstablecerLimites extends HttpServlet {

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
            LimitesGerenteModel limitesModel = new LimitesGerenteModel();

            Double limite2 = Double.parseDouble((String) request.getParameter("limite2"));
            Double limite3 = Double.parseDouble((String) request.getParameter("limite3"));
            if (limite2 < limite3) {
                LimitesGerente limites = new LimitesGerente(limite2, limite3);
                try {
                    limitesModel.agregarLimites(limites);
                    request.setAttribute("successEditarLimites", 1);
                    request.getRequestDispatcher("/gerente/EditarLimites.jsp").forward(request, response);
                } catch (IOException | SQLException | ServletException e) {
                    request.setAttribute("successEditarLimites", 0);
                    request.getRequestDispatcher("/gerente/EditarLimites.jsp").forward(request, response);
                }
            }
            else{
                request.setAttribute("successEditarLimites", 2);
                    request.getRequestDispatcher("/gerente/EditarLimites.jsp").forward(request, response);
            }

        } catch (IOException | NumberFormatException | ServletException e) {
            JOptionPane.showMessageDialog(null, "No ha cargado los limites ya establecidos");
            request.getRequestDispatcher("/gerente/GerenteIndex.jsp").forward(request, response);
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
