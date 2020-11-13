/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Clases.GeneradorArchivo;
import Clases.LeerXML;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author jeffrey
 */
@WebServlet(name = "CargaDatos", urlPatterns = {"/CargaDatos"})
@MultipartConfig
public class CargaDatos extends HttpServlet {

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
            out.println("<title>Servlet CargarDatos</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CargarDatos at " + request.getContextPath() + "</h1>");
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
        GeneradorArchivo generadorArchivo = new GeneradorArchivo();
        LeerXML leerXML=new LeerXML();
        try {
            String archivoXML = "";

            ArrayList<Part> parts = new ArrayList<>();
            ArrayList<Part> partsMessy = new ArrayList<>(request.getParts());
            for (int i = 0; i < partsMessy.size(); i++) {
                if (partsMessy.get(i).getSubmittedFileName() != null) {
                    if (partsMessy.get(i).getSubmittedFileName().endsWith(".xml")) {
                        archivoXML = partsMessy.get(i).getSubmittedFileName();
                    }
                    parts.add(partsMessy.get(i));
                }
            }

            for (int i = 0; i < parts.size(); i++) {
                generadorArchivo.escribirArchivo(parts.get(i));
            }
            File file = new File(generadorArchivo.getPath() + archivoXML);
            leerXML.dividirEtiquetas(generadorArchivo.getPath(), file);
            
            
        } catch (Exception e) {
        }finally{
            request.getRequestDispatcher("/gerente/MensajeCargaDatos.jsp").forward(request, response);
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
