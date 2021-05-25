/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import daos.AlumnoSeccionDAO;
import daos.RolUsuarioDAO;
import daos.SeccionCursoDAO;
import daos.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.RolUsuario;
import models.Usuario;
import models_relation.AlumnoSeccion;
import models_relation.SeccionCurso;

/**
 *
 * @author Jhonatan
 */
@WebServlet(name = "Usuario", urlPatterns = {"/Usuario"})
public class UsuarioServlet extends HttpServlet {
    
    UsuarioDAO uDAO = new UsuarioDAO();
    RolUsuarioDAO ruDAO = new RolUsuarioDAO();
    AlumnoSeccionDAO asDAO = new AlumnoSeccionDAO();
    SeccionCursoDAO scDAO = new SeccionCursoDAO();

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
        if ("update".equals(action) || "add".equals(action)) {
            template = "usuario/manage_usuario.jsp";
            if (action.equals("update")) {
                int id = Integer.parseInt(request.getParameter("id"));
                usuario = uDAO.selectById(id);
            }
            List<RolUsuario> roles_usuario = ruDAO.selectAll();
            request.setAttribute("roles_usuario", roles_usuario);
            request.setAttribute("action", action);
            request.setAttribute("usuario", usuario);
        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            usuario = uDAO.selectById(id);
            request.setAttribute("usuario", usuario);
            template = "usuario/delete_usuario.jsp";
        } else {
            template = "usuario/usuarios.jsp";
            List<Usuario> usuarios = uDAO.selectAll();
            request.setAttribute("usuarios", usuarios);
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
            Usuario u = new Usuario();
            int id_ru = Integer.parseInt(request.getParameter("rol"));
            u.setRol(new RolUsuario(id_ru));
            u.setNombre(request.getParameter("nombre"));
            u.setApellido(request.getParameter("apellido"));
            u.setTelefono(request.getParameter("telefono"));
            u.setDocumento(request.getParameter("documento"));
            u.setUser(request.getParameter("user"));
            if (action.equals("add")) {
                uDAO.insert(u);
                request.setAttribute("saved", true);
            } else {
                u.setId(Integer.parseInt(request.getParameter("id")));
                uDAO.update(u);
                request.setAttribute("updated", true);
            }
        } else if (action.equals("delete")) { // update
            int id = Integer.parseInt(request.getParameter("id"));
            ArrayList<AlumnoSeccion> lista_as = asDAO.selectByAlumno(id);
            ArrayList<SeccionCurso> lista_sc = scDAO.selectByDocente(id);
            boolean deleted = false;
            if (lista_as.isEmpty() && lista_sc.isEmpty()) {
                uDAO.delete(id);
                deleted = true;
            } else {
                if (!lista_as.isEmpty()) {
                    request.setAttribute("error_detail", "No se puede eliminar el alumno por que está matriculado en una o más secciones.");
                } else if (!lista_sc.isEmpty()) {
                    request.setAttribute("error_detail", "No se puede eliminar el docente por que está asignado en una o más secciones.");
                }
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
