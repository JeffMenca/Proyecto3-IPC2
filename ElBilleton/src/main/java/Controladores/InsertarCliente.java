package Controladores;

import Clases.GeneradorArchivo;
import Modelos.ClienteModel;
import Modelos.CuentaModel;
import Modelos.HistorialClienteModel;
import Objetos.Cliente;
import Objetos.Cuenta;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
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
@WebServlet(name = "InsertarCliente", urlPatterns = {"/InsertarCliente"})
@MultipartConfig
public class InsertarCliente extends HttpServlet {

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
        ClienteModel clienteModel = new ClienteModel();
        CuentaModel cuentaModel = new CuentaModel();
        HistorialClienteModel historialClienteModel = new HistorialClienteModel();
        GeneradorArchivo generadorArchivo = new GeneradorArchivo();
        String nombre = request.getParameter("nombre").trim();
        Date fecha_nacimiento = Date.valueOf((String) request.getParameter("fecha"));
        String DPI = request.getParameter("DPI");
        String direccion = request.getParameter("direccion").trim();
        String sexo = request.getParameter("sexo");
        String password = request.getParameter("password");
        Double monto = Double.valueOf((String) request.getParameter("monto"));
        Date fecha = Date.valueOf(LocalDate.now());
        InputStream archivo = InputStream.nullInputStream();
        if (nombre.trim().equals("") || direccion.trim().equals("")) {
            request.setAttribute("successCrearCliente", 2);
            request.getRequestDispatcher("/gerente/CrearCliente.jsp").forward(request, response);
        } else {
            try {
                archivo = generadorArchivo.extraerArchivo("archivo", request);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

            Cliente nuevoCliente = new Cliente(0, nombre, fecha_nacimiento, DPI, direccion, sexo, password, archivo);

            try {
                Long codigoCliente = clienteModel.agregarCliente(nuevoCliente);
                historialClienteModel.agregarHistorialClienteCodigo(nuevoCliente, codigoCliente);
                Cuenta nuevaCuenta = new Cuenta(0, fecha, monto, codigoCliente);
                cuentaModel.agregarCuenta(nuevaCuenta);
                request.setAttribute("successCrearCliente", 1);
                request.getRequestDispatcher("/gerente/CrearCliente.jsp").forward(request, response);
            } catch (Exception e) {
                request.setAttribute("successCrearCliente", 0);
                request.getRequestDispatcher("/gerente/CrearCliente.jsp").forward(request, response);
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
