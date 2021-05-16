/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import daos.RolUsuarioDAO;
import daos.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.RolUsuario;
import models.Usuario;

/**
 *
 * @author Jhonatan
 */
@WebServlet(name = "Usuario", urlPatterns = {"/Usuario"})
public class UsuarioServlet extends HttpServlet {
    
    UsuarioDAO uDAO = new UsuarioDAO();
    RolUsuarioDAO ruDAO = new RolUsuarioDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
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
            } else {
                u.setId(Integer.parseInt(request.getParameter("id")));
                uDAO.update(u);
            }
        } else if (action.equals("delete")) { // update
            int id = Integer.parseInt(request.getParameter("id"));
            uDAO.delete(id);
        }
        response.sendRedirect(request.getContextPath() + "/Usuario?action=listar");
        
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
