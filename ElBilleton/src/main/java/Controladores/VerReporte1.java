package Controladores;

import Modelos.CajeroModel;
import Modelos.ClienteModel;
import Modelos.GerenteModel;
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
@WebServlet(name = "VerReporte1", urlPatterns = {"/VerReporte1"})
public class VerReporte1 extends HttpServlet {

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
            out.println("<title>Servlet VerReporte1</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet VerReporte1 at " + request.getContextPath() + "</h1>");
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
        String entidadSeleccionada = request.getParameter("entidad");
        if (entidadSeleccionada.equals("gerente")) {
            GerenteModel gerenteModel = new GerenteModel();
            try {
                ArrayList gerentes = gerenteModel.obtenerGerentes();
                String codigo = request.getParameter("filtro");

                if (codigo == null || (codigo != null && codigo.isEmpty())) {
                    gerentes = gerenteModel.obtenerGerentes();
                } else {
                    gerentes = gerenteModel.obtenerGerentesLike(codigo);
                }
                request.setAttribute("listaGerentes", gerentes);
                request.setAttribute("entidad", entidadSeleccionada);
                request.getRequestDispatcher("/gerente/VerGerentesReporte1.jsp").forward(request, response);
            } catch (Exception e) {
            }
        } else if (entidadSeleccionada.equals("cliente")) {
            ClienteModel clienteModel = new ClienteModel();
            try {
                ArrayList clientes = clienteModel.obtenerClientes();
                String codigo = request.getParameter("filtro");

                if (codigo == null || (codigo != null && codigo.isEmpty())) {
                    clientes = clienteModel.obtenerClientes();
                } else {
                    clientes = clienteModel.obtenerClientesLike(codigo);
                }
                request.setAttribute("listaClientes", clientes);
                request.setAttribute("entidad", entidadSeleccionada);
                request.getRequestDispatcher("/gerente/VerClientesReporte1.jsp").forward(request, response);
            } catch (Exception e) {
            }
        } else if (entidadSeleccionada.equals("cajero")) {
            CajeroModel cajeroModel = new CajeroModel();
            try {
                ArrayList cajeros = cajeroModel.obtenerCajeros();
                String codigo = request.getParameter("filtro");

                if (codigo == null || (codigo != null && codigo.isEmpty())) {
                    cajeros = cajeroModel.obtenerCajeros();
                } else {
                    cajeros = cajeroModel.obtenerCajerosLike(codigo);
                }
                request.setAttribute("listaCajeros", cajeros);
                request.setAttribute("entidad", entidadSeleccionada);
                request.getRequestDispatcher("/gerente/VerCajerosReporte1.jsp").forward(request, response);
            } catch (Exception e) {
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
