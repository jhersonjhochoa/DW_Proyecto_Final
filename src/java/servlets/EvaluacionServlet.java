/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import daos.CursoDAO;
import daos.EvaluacionDAO;
import daos.GradoDAO;
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
import models.Evaluacion;
import models.Grado;
import models.Seccion;
import models_relation.SeccionCurso;

/**
 *
 * @author Jhonatan
 */
@WebServlet(name = "Evaluacion", urlPatterns = {"/Evaluacion"})
public class EvaluacionServlet extends HttpServlet {
    
    RolUsuarioDAO ruDAO = new RolUsuarioDAO();
    UsuarioDAO uDAO = new UsuarioDAO();
    GradoDAO gDAO = new GradoDAO();
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
        int seccion = 0;
        try {seccion = Integer.parseInt(request.getParameter("seccion"));}
        catch (NumberFormatException ex) {}
        int curso = 0;
        try {curso = Integer.parseInt(request.getParameter("curso"));}
        catch (NumberFormatException ex) {}
        int id_sc = 0;
        try {id_sc = Integer.parseInt(request.getParameter("id_sc"));}
        catch (NumberFormatException ex) {}
        SeccionCurso sc = new SeccionCurso();
        if (seccion != 0 && curso != 0) {
            sc = scDAO.selectBySeccionCurso(seccion, curso);
            id_sc = sc.getId();
        } else if (id_sc != 0) {
            sc = scDAO.selectById(id_sc);
        }
        request.setAttribute("seccion", seccion);
        request.setAttribute("curso", curso);
        request.setAttribute("sc", sc);
        if ("update".equals(action) || "add".equals(action)) {
            template = "evaluacion/manage_evaluacion.jsp";
            Evaluacion ev = new Evaluacion();
            if (action.equals("update")) {
                int id = Integer.parseInt(request.getParameter("id"));
                ev = eDAO.selectById(id);
            }
            request.setAttribute("action", action);
            request.setAttribute("evaluacion", ev);
        }  else if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Evaluacion ev = eDAO.selectById(id);
            request.setAttribute("evaluacion", ev);
            template = "evaluacion/delete_evaluacion.jsp";
        } else if ("getFiltro".equals(action)) {
            List<Grado> grados = gDAO.selectAll();
            request.setAttribute("grados", grados);
            System.out.println("GRADO: " + request.getParameter("grado"));
            int grado = Integer.parseInt(request.getParameter("grado"));
            request.setAttribute("grado", grado);
            if (grado != 0) {
                List<Seccion> secciones = sDAO.selectByGrado(grado);
                request.setAttribute("secciones", secciones);
            }
            if (seccion != 0) {
                List<SeccionCurso> s_cursos = scDAO.selectBySeccion(seccion);
                request.setAttribute("s_cursos", s_cursos);
            }
            request.setAttribute("evaluaciones", scDAO.selectBySeccion(seccion));
            template = "evaluacion/filtros_evaluacion.jsp";
        } else if ("getEvaluaciones".equals(action)) {
            List<Evaluacion> evs = eDAO.selectBySc(id_sc);
            int suma_porcentaje = 0;
            int suma_bonus = 0;
            for (Evaluacion e: evs) {
                if (e.isBonus()) suma_bonus += e.getPorcentaje();
                else suma_porcentaje += e.getPorcentaje();
            }
            request.setAttribute("suma_porcentaje", suma_porcentaje);
            request.setAttribute("suma_bonus", suma_bonus);
            request.setAttribute("evaluaciones", evs);
            template = "evaluacion/tabla_evaluaciones.jsp";
        } else {
            template = "evaluacion/evaluaciones.jsp";
            List<Grado> grados = gDAO.selectAll();
            request.setAttribute("grados", grados);
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
            Evaluacion ev = new Evaluacion();
            int id_sc = Integer.parseInt(request.getParameter("id_sc"));
            SeccionCurso sc = new SeccionCurso();
            sc.setId(id_sc);
            String descripcion = request.getParameter("descripcion");
            int porcentaje = Integer.parseInt(request.getParameter("porcentaje"));
            boolean bonus = Boolean.parseBoolean(request.getParameter("bonus"));
            ev.setSeccion_curso(sc);
            ev.setDescripcion(descripcion);
            ev.setPorcentaje(porcentaje);
            ev.setBonus(bonus);
            if (action.equals("add")) {
                eDAO.insert(ev);
                request.setAttribute("saved", true);
            } else {
                ev.setId(Integer.parseInt(request.getParameter("id")));
                eDAO.update(ev);
                request.setAttribute("updated", true);
            }

            /* 
            error = true;
            error_detail = "Error....";
            if (error) {
                request.setAttribute("error_detail", error_detail);
            }*/
        } else if (action.equals("delete")) { // update
            int id = Integer.parseInt(request.getParameter("id"));
            eDAO.delete(id);
            request.setAttribute("deleted", true);
        }
        request.setAttribute("redirect", "getEvaluaciones");
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
