/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import daos.AlumnoSeccionDAO;
import daos.GradoDAO;
import daos.SeccionDAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Grado;
import models.Seccion;
import models_relation.AlumnoSeccion;

/**
 *
 * @author Jhonatan
 */
@WebServlet(name = "Seccion", urlPatterns = {"/Seccion"})
public class SeccionServlet extends HttpServlet {
    
    SeccionDAO sDAO = new SeccionDAO();
    GradoDAO gDAO = new GradoDAO();
    AlumnoSeccionDAO asDAO = new AlumnoSeccionDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        String redirect = (String) request.getAttribute("redirect");
        if (redirect != null) {
            action = redirect;
        }
        String anio = Calendar.getInstance().get(Calendar.YEAR) + "";
        int year_from = Integer.parseInt(request.getParameter("year_from") == null ? anio : request.getParameter("year_from"));
        int year_to = Integer.parseInt(request.getParameter("year_to") == null ? anio : request.getParameter("year_to"));
        request.setAttribute("year_from", year_from);
        request.setAttribute("year_to", year_to);
        String template;
        Seccion seccion = new Seccion();
        if ("update".equals(action) || "add".equals(action)) {
            template = "seccion/manage_seccion.jsp";
            if (action.equals("update")) {
                int id = Integer.parseInt(request.getParameter("id"));
                seccion = sDAO.selectById(id);
            }
            List<Grado> grados = gDAO.selectAll();
            request.setAttribute("grados", grados);
            request.setAttribute("anio", anio);
            request.setAttribute("action", action);
            request.setAttribute("seccion", seccion);
        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            seccion = sDAO.selectById(id);
            request.setAttribute("seccion", seccion);
            template = "seccion/delete_seccion.jsp";
        } else {
            template = "seccion/secciones.jsp";
            List<Seccion> secciones = sDAO.selectByYears(year_from, year_to);
            request.setAttribute("secciones", secciones);
        }
        
        RequestDispatcher rd = request.getRequestDispatcher(template);
        rd.forward(request, response);
        
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
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action.equals("add") || action.equals("update")) {
            Seccion s = new Seccion();
            int id_grado = Integer.parseInt(request.getParameter("grado"));
            s.setGrado(new Grado(id_grado));
            s.setDescripcion(request.getParameter("descripcion"));
            s.setAnio(Integer.parseInt(request.getParameter("anio")));
            if (action.equals("add")) {
                sDAO.insert(s);
                request.setAttribute("saved", true);
            } else {
                s.setId(Integer.parseInt(request.getParameter("id")));
                sDAO.update(s);
                request.setAttribute("updated", true);
            }
        } else if (action.equals("delete")) { // update
            int id = Integer.parseInt(request.getParameter("id"));
            ArrayList<AlumnoSeccion> lista_as = asDAO.selectBySeccion(id);
            boolean deleted = false;
            if (lista_as.isEmpty()) {
                sDAO.delete(id);
                deleted = true;
            } else {
                request.setAttribute("error_detail", "No se puede eliminar la sección por que tiene alumnos matriculados.");
            }
            request.setAttribute("deleted", deleted);
        }
        request.setAttribute("redirect", "listar");
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
