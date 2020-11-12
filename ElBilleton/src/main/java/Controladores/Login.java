package Controladores;

import Modelos.CajeroModel;
import Modelos.ClienteModel;
import Modelos.GerenteModel;
import Objetos.Cajero;
import Objetos.Cliente;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Objetos.Gerente;
import java.sql.SQLException;

/**
 *
 * @author jeffrey
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    GerenteModel gerenteModel = new GerenteModel();
    CajeroModel cajeroModel = new CajeroModel();
    ClienteModel clienteModel = new ClienteModel();

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
            out.println("<title>Servlet Login</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Login at " + request.getContextPath() + "</h1>");
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
        /**
     * Abre el formulario especifico de una entidad si valida las credenciales
     *
     */
        try {
            String username = request.getParameter("usuario");
            String password = request.getParameter("password");
            String tipo = request.getParameter("tipo");
            Gerente gerente = gerenteModel.loginValidation(Integer.parseInt(username), password);
            Cajero cajero = cajeroModel.loginValidation(Integer.parseInt(username), password);
            Cliente cliente = clienteModel.loginValidation(Integer.parseInt(username), password);
            if (gerente != null && tipo.equals("Gerente")) {
                request.getSession().setAttribute("id", username);
                request.getSession().setAttribute("gerente", gerente);
                response.sendRedirect(request.getContextPath()+"/Horario");
            } else if (cajero != null && tipo.equals("Cajero")) {
                request.getSession().setAttribute("id", username);
                request.getSession().setAttribute("cajero", cajero);
                request.getRequestDispatcher("/cajero/CajeroIndex").forward(request, response);
            } else if (cliente != null && tipo.equals("Cliente")) {
                request.getSession().setAttribute("id", username);
                request.getSession().setAttribute("cliente", cliente);
                request.getRequestDispatcher("/cliente/ClienteIndex.jsp").forward(request, response);
            } else {
                request.setAttribute("success", 0);
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        } catch (SQLException | IOException | NumberFormatException e) {
            
            request.setAttribute("success", 2);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
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
