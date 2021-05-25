/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import daos.CursoDAO;
import daos.EvaluacionDAO;
import daos.RolUsuarioDAO;
import daos.SeccionCursoDAO;
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
import models.Curso;
import models.Evaluacion;
import models.RolUsuario;
import models.Seccion;
import models.Usuario;
import models_relation.SeccionCurso;

/**
 *
 * @author Jhonatan
 */
@WebServlet(name = "SeccionCurso", urlPatterns = {"/SeccionCurso"})
public class SeccionCursoServlet extends HttpServlet {
    
    RolUsuarioDAO ruDAO = new RolUsuarioDAO();
    UsuarioDAO uDAO = new UsuarioDAO();
    SeccionDAO sDAO = new SeccionDAO();
    CursoDAO cDAO = new CursoDAO();
    SeccionCursoDAO scDAO = new SeccionCursoDAO();
    EvaluacionDAO eDAO = new EvaluacionDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        String redirect = (String) request.getAttribute("redirect");
        if (redirect != null) {
            action = redirect;
        }
        String template;
        if ("update".equals(action) || "add".equals(action)) {
            template = "seccion_curso/manage_seccion_curso.jsp";
            int seccion = Integer.parseInt(request.getParameter("seccion"));
            SeccionCurso sc = new SeccionCurso();
            if (action.equals("update")) {
                int id = Integer.parseInt(request.getParameter("id"));
                sc = scDAO.selectById(id);
            }
            List<Curso> cursos = cDAO.selectAll();
            RolUsuario ruDocente = ruDAO.selectByCod("DOC");
            List<Usuario> docentes = uDAO.selectAllByRol(ruDocente);
            request.setAttribute("cursos", cursos);
            request.setAttribute("docentes", docentes);
            request.setAttribute("action", action);
            request.setAttribute("seccion", seccion);
            request.setAttribute("sc", sc);
        }  else if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            SeccionCurso sc = scDAO.selectById(id);
            request.setAttribute("sc", sc);
            template = "seccion_curso/delete_seccion_curso.jsp";
        } else if ("getCursos".equals(action)) {
            int seccion = Integer.parseInt(request.getParameter("seccion"));
            request.setAttribute("seccion_cursos", scDAO.selectBySeccion(seccion));
            template = "seccion_curso/tabla_seccion_cursos.jsp";
        } else {
            template = "seccion_curso/seccion_cursos.jsp";
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
        if (action.equals("add") || action.equals("update")) {
            int id_sec = Integer.parseInt(request.getParameter("seccion"));
            Seccion seccion = new Seccion();
            seccion.setId(id_sec);
            String error_detail = "";
            boolean error = false;
            int id_curso = Integer.parseInt(request.getParameter("curso"));
            int id_docente = Integer.parseInt(request.getParameter("docente"));
            Curso curso = new Curso();
            curso.setId(id_curso);
            Usuario docente = new Usuario();
            docente.setId(id_docente);
            SeccionCurso sc_v = scDAO.selectBySeccionCurso(id_sec, id_curso);
            SeccionCurso sc = new SeccionCurso();
            sc.setSeccion(seccion);
            sc.setCurso(curso);
            sc.setDocente(docente);
            if (sc_v == null || sc_v.getId() == 0) {
                if (action.equals("add")) {
                    scDAO.insert(sc);
                    request.setAttribute("saved", true);
                } else {
                    sc.setId(Integer.parseInt(request.getParameter("id")));
                    scDAO.update(sc);
                    request.setAttribute("updated", true);
                }
            } else {
                error = true;
                error_detail = "Este curso ya se ha agregado.";
            }
            if (error) {
                request.setAttribute("error_detail", error_detail);
            }
        } else if (action.equals("delete")) { // update
            int id = Integer.parseInt(request.getParameter("id"));
            List<Evaluacion> lista_ev = eDAO.selectBySc(id);
            if (lista_ev.isEmpty()) {
                request.setAttribute("deleted", true);
                scDAO.delete(id);
            } else {
                request.setAttribute("error_detail", "No se puede desvincular el curso de esta sección por que ya tiene evaluaciones asignadas.");
            }
        }
        request.setAttribute("redirect", "getCursos");
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
