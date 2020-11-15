package Controladores;

import Clases.GeneradorArchivo;
import Clases.PDFHistorial;
import Modelos.ClienteModel;
import Modelos.HistorialClienteModel;
import Objetos.Cliente;
import java.awt.HeadlessException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
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
@WebServlet(name = "ActualizarCliente", urlPatterns = {"/ActualizarCliente"})
@MultipartConfig
public class ActualizarCliente extends HttpServlet {

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
            ClienteModel clienteModel = new ClienteModel();
            HistorialClienteModel historialClienteModel = new HistorialClienteModel();
            GeneradorArchivo generadorArchivo = new GeneradorArchivo();

            Long codigoCliente = Long.parseLong((String) request.getParameter("codigo"));
            String nombre = request.getParameter("nombre").trim();
            Date fecha_nacimiento = Date.valueOf((String) request.getParameter("fecha"));
            String DPI = request.getParameter("DPI");
            String direccion = request.getParameter("direccion").trim();
            String sexo = request.getParameter("sexo");
            String password = request.getParameter("password");
            InputStream archivo = InputStream.nullInputStream();
            Part file = request.getPart("archivo");

            if (nombre.trim().equals("") || direccion.trim().equals("")) {
                request.setAttribute("successEditarCliente", 2);
                request.getRequestDispatcher("/gerente/EditarCliente.jsp").forward(request, response);
            } else {
                if (file != null && file.getSize() > 0) {
                    try {
                        archivo = generadorArchivo.extraerArchivo("archivo", request);
                        PDFHistorial crearPDF = new PDFHistorial(archivo);
                        InputStream pdf1 = new ByteArrayInputStream(crearPDF.obtenerArrayDatos());
                        InputStream pdf2 = new ByteArrayInputStream(crearPDF.obtenerArrayDatos());
                        Cliente nuevoCliente = new Cliente(0, nombre, fecha_nacimiento, DPI, direccion, sexo, password, pdf1);
                        try {
                            clienteModel.actualizarCliente(nuevoCliente, codigoCliente);
                            nuevoCliente.setDPI_copia(pdf2);
                            historialClienteModel.agregarHistorialClienteCodigo(nuevoCliente, codigoCliente);
                            request.setAttribute("successEditarCliente", 1);
                            request.getRequestDispatcher("/gerente/EditarCliente.jsp").forward(request, response);
                        } catch (IOException | SQLException | ServletException e) {
                            request.setAttribute("successEditarCliente", 0);
                            request.getRequestDispatcher("/gerente/EditarCliente.jsp").forward(request, response);
                        }
                    } catch (IOException | ServletException e) {
                    }
                } else {
                    Cliente nuevoCliente = new Cliente(0, nombre, fecha_nacimiento, DPI, direccion, sexo, password, clienteModel.obtenerDPI(codigoCliente));
                    try {
                        clienteModel.actualizarCliente(nuevoCliente, codigoCliente);
                        historialClienteModel.agregarHistorialClienteCodigo(nuevoCliente, codigoCliente);
                        request.setAttribute("successEditarCliente", 1);
                        request.getRequestDispatcher("/gerente/EditarCliente.jsp").forward(request, response);
                    } catch (IOException | SQLException | ServletException e) {
                        request.setAttribute("successEditarCliente", 0);
                        request.getRequestDispatcher("/gerente/EditarCliente.jsp").forward(request, response);
                    }
                }
            }

        } catch (HeadlessException | IOException | NumberFormatException | ServletException e) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningun cliente");
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
