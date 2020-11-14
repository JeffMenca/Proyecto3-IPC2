package Controladores;

import Objetos.Gerente;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

/**
 *
 * @author jeffrey
 */
@WebServlet(name = "Horario", urlPatterns = {"/Horario"})
public class Horario extends HttpServlet {

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
            out.println("<title>Servlet Horario</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Horario at " + request.getContextPath() + "</h1>");
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
            Gerente gerente = (Gerente) request.getSession().getAttribute("gerente");
            LocalTime horaActual = LocalTime.now();
            LocalTime horaInicio, horaFinal;
            if (gerente.getTurno().equals("Vespertino")) {
                horaInicio = LocalTime.of(1, 0);
                horaFinal = LocalTime.of(22, 0);
            } else {
                horaInicio = LocalTime.of(6, 0);
                horaFinal = LocalTime.of(14, 30);
            }

            if (horaActual.isAfter(horaInicio) && (horaActual.isBefore(horaFinal))) {
                request.getSession().setAttribute("enHora", "Si");
                request.getRequestDispatcher("/gerente/GerenteIndex.jsp").forward(request, response);
            } else {
                request.getSession().setAttribute("enHora", "No");
                request.getRequestDispatcher("/gerente/GerenteIndex.jsp").forward(request, response);

            }
        } catch (Exception e) {
            if (request.getSession().getAttribute("user") == null) {
                response.sendRedirect(request.getContextPath()+"/index.jsp");
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
