package Controladores;

import Modelos.CuentaModel;
import Modelos.TransaccionModel;
import Objetos.Cuenta;
import Objetos.Transaccion;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jeffrey
 */
@WebServlet(name = "InsertarCuenta", urlPatterns = {"/InsertarCuenta"})
@MultipartConfig
public class InsertarCuenta extends HttpServlet {

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
            CuentaModel cuentaModel = new CuentaModel();
            TransaccionModel transaccionModel = new TransaccionModel();
            Long codigoCliente = Long.valueOf((String) request.getParameter("codigo"));
            Double monto = Double.valueOf((String) request.getParameter("monto"));
            Date fecha = Date.valueOf(LocalDate.now());
            Time hora = Time.valueOf(LocalTime.now());
            Cuenta nuevaCuenta = new Cuenta(0, fecha, monto, codigoCliente);
            try {
                Long codigoCuenta = cuentaModel.agregarCuenta(nuevaCuenta);
                Transaccion transaccion = new Transaccion(0, fecha, hora, "Credito", monto, codigoCuenta, 101);
                transaccionModel.agregarTransaccion(transaccion);
                request.setAttribute("codigoCreado", codigoCuenta);
                request.setAttribute("successCrearCuenta", 1);
                request.getRequestDispatcher("/gerente/CrearCuenta.jsp").forward(request, response);
            } catch (Exception e) {
                request.setAttribute("successCrearCuenta", 0);
                request.getRequestDispatcher("/gerente/CrearCuenta.jsp").forward(request, response);
            }
        } catch (Exception e) {
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
