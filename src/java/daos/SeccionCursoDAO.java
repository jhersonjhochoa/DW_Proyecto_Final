/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import conexion.ConnectionDB;
import interfaces.ISeccionCurso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Curso;
import models.Seccion;
import models.Usuario;
import models_relation.SeccionCurso;

/**
 *
 * @author Jhonatan
 */
public class SeccionCursoDAO implements ISeccionCurso {
    ConnectionDB cn = new ConnectionDB();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    SeccionCursoDAO scDAO = new SeccionCursoDAO();
    SeccionDAO sDAO = new SeccionDAO();
    CursoDAO cDAO = new CursoDAO();
    UsuarioDAO uDAO = new UsuarioDAO();

    @Override
    public boolean insert(SeccionCurso sc) {
        try {
            String sql = "insert into seccion_curso(curso, seccion, docente) values(?, ?, ?)";
            con = cn.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, sc.getCurso().getId());
            ps.setInt(2, sc.getSeccion().getId());
            ps.setInt(3, sc.getDocente().getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(SeccionCurso sc) {
        try {
            String sql = "update seccion_curso set curso =?, seccion = ?, docente = ? where id = ?";
            con = cn.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, sc.getCurso().getId());
            ps.setInt(2, sc.getSeccion().getId());
            ps.setInt(3, sc.getDocente().getId());
            ps.setInt(4, sc.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try {
            String sql = "delete from seccion_curso where id=?";
            con = cn.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public SeccionCurso selectById(int id) {
        try {
            String sql = "select * from seccion_curso where id=?";
            con = cn.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            SeccionCurso sc = new SeccionCurso();
            while (rs.next()) {
                sc.setId(rs.getInt("id"));
                Curso c = cDAO.selectById(rs.getInt("curso"));
                Seccion s = sDAO.selectById(rs.getInt("seccion"));
                Usuario docente = uDAO.selectById(rs.getInt("docente"));
                sc.setCurso(c);
                sc.setSeccion(s);
                sc.setDocente(docente);
            }
            return sc;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<SeccionCurso> selectAll() {
        ArrayList<SeccionCurso> lista = new ArrayList<>();
        try {
            String sql = "select * from seccion_curso";
            con = cn.getCon();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            SeccionCurso sc;
            while (rs.next()) {
                sc = new SeccionCurso();
                Curso c = cDAO.selectById(rs.getInt("curso"));
                Seccion s = sDAO.selectById(rs.getInt("seccion"));
                Usuario docente = uDAO.selectById(rs.getInt("docente"));
                sc.setCurso(c);
                sc.setSeccion(s);
                sc.setDocente(docente);
                lista.add(sc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
}
