/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import daos.GradoDAO;
import daos.CursoDAO;
import daos.SeccionCursoDAO;
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
import models.Curso;
import models_relation.SeccionCurso;

/**
 *
 * @author Jhonatan
 */
@WebServlet(name = "Curso", urlPatterns = {"/Curso"})
public class CursoServlet extends HttpServlet {
    
    CursoDAO cDAO = new CursoDAO();
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
        Curso curso = new Curso();
        if ("update".equals(action) || "add".equals(action)) {
            template = "curso/manage_curso.jsp";
            if (action.equals("update")) {
                int id = Integer.parseInt(request.getParameter("id"));
                curso = cDAO.selectById(id);
            }
            request.setAttribute("action", action);
            request.setAttribute("curso", curso);
        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            curso = cDAO.selectById(id);
            request.setAttribute("curso", curso);
            template = "curso/delete_curso.jsp";
        } else {
            template = "curso/cursos.jsp";
            List<Curso> cursos = cDAO.selectAll();
            request.setAttribute("cursos", cursos);
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
            Curso c = new Curso();
            String nombre = request.getParameter("nombre");
            c.setNombre(nombre);
            if (action.equals("add")) {
                cDAO.insert(c);
                request.setAttribute("saved", true);
            } else {
                c.setId(Integer.parseInt(request.getParameter("id")));
                cDAO.update(c);
                request.setAttribute("updated", true);
            }
        } else if (action.equals("delete")) { // update
            int id = Integer.parseInt(request.getParameter("id"));
            ArrayList<SeccionCurso> lista_sc = scDAO.selectByCurso(id);
            boolean deleted = false;
            if (lista_sc.isEmpty()) {
                cDAO.delete(id);
                deleted = true;
            } else {
                request.setAttribute("error_detail", "No se puede eliminar el curso por que ya ha sido asignado a una o más secciones.");
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
