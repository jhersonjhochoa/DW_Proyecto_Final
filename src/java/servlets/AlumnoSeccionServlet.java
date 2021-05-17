/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import daos.AlumnoSeccionDAO;
import daos.SeccionDAO;
import daos.UsuarioDAO;
import java.io.IOException; 
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Seccion;
import models.Usuario;
import models_relation.AlumnoSeccion;

/**
 *
 * @author Jhonatan
 */
@WebServlet(name = "AlumnoSeccion", urlPatterns = {"/AlumnoSeccion"})
public class AlumnoSeccionServlet extends HttpServlet {
    
    UsuarioDAO uDAO = new UsuarioDAO();
    SeccionDAO sDAO = new SeccionDAO();
    AlumnoSeccionDAO asDAO = new AlumnoSeccionDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        String redirect = (String) request.getAttribute("redirect");
        if (redirect != null) {
            action = redirect;
        }
        String template;
        Usuario usuario = new Usuario();
        if ("add".equals(action)) {
            int seccion = Integer.parseInt(request.getParameter("seccion"));
            template = "alumno_seccion/manage_alumno_seccion.jsp";
            request.setAttribute("seccion", seccion);
            request.setAttribute("usuario", usuario);
        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            AlumnoSeccion as = asDAO.selectById(id);
            request.setAttribute("as", as);
            template = "alumno_seccion/delete_alumno_seccion.jsp";
        } else if ("getAlumnos".equals(action)) {
            int seccion = Integer.parseInt(request.getParameter("seccion"));
            request.setAttribute("alumnos_sec", asDAO.selectBySeccion(seccion));
            template = "alumno_seccion/tabla_alumnos_seccion.jsp";
        } else {
            template = "alumno_seccion/alumnos_seccion.jsp";
            List<Seccion> secciones = sDAO.selectAll();
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
        String action = request.getParameter("action");
        if (action.equals("add")) {
            int id_sec = Integer.parseInt(request.getParameter("seccion"));
            Seccion seccion = sDAO.selectById(id_sec);
            String error_detail = "";
            boolean error = false;
            String documento = request.getParameter("documento");
            Usuario alumno = uDAO.selectByDocumento(documento);
            if (alumno == null || alumno.getId() == 0 || (alumno.getId() != 0 && !"ALUMN".equals(alumno.getRol().getCod()))) {
                error = true;
                error_detail = "Alumno no existe.";
            } else {
                AlumnoSeccion as = new AlumnoSeccion();
                as.setAlumno(alumno);
                as.setSeccion(seccion);
                try {
                    asDAO.insert(as);
                } catch (Exception e) {
                    error = true;
                    error_detail = "Error interno.";
                }
            }
            if (error) {
                request.setAttribute("error_detail", error_detail);
            } else {
                request.setAttribute("saved", true);
            }
            
        } else if (action.equals("delete")) { // update
            int id = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("deleted", true);
            asDAO.delete(id);
        }
        request.setAttribute("redirect", "getAlumnos");
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
