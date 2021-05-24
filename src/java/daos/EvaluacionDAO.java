/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import conexion.ConnectionDB;
import interfaces.IEvaluacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Evaluacion;
import models_relation.SeccionCurso;

/**
 *
 * @author Jhonatan
 */
public class EvaluacionDAO implements IEvaluacion {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    SeccionCursoDAO scDAO = new SeccionCursoDAO();

    @Override
    public boolean insert(Evaluacion e) {
        try {
            String sql = "insert into evaluacion(seccion_curso, descripcion, porcentaje, bonus) values(?, ?, ?, ?)";
            con = ConnectionDB.newInstanceDB().getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, e.getSeccion_curso().getId());
            ps.setString(2, e.getDescripcion());
            ps.setInt(3, e.getPorcentaje());
            ps.setBoolean(4, e.isBonus());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.ALL.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(Evaluacion e) {
        try {
            String sql = "update evaluacion set seccion_curso =?, descripcion = ?, porcentaje = ?, bonus = ? where id = ?";
            con = ConnectionDB.newInstanceDB().getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, e.getSeccion_curso().getId());
            ps.setString(2, e.getDescripcion());
            ps.setInt(3, e.getPorcentaje());
            ps.setBoolean(4, e.isBonus());
            ps.setInt(5, e.getId());
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
            String sql = "delete from evaluacion where id=?";
            con = ConnectionDB.newInstanceDB().getCon();
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
    public Evaluacion selectById(int id) {
        try {
            String sql = "select * from evaluacion where id=?";
            con = ConnectionDB.newInstanceDB().getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            Evaluacion e = new Evaluacion();
            while (rs.next()) {
                e.setId(rs.getInt("id"));
                SeccionCurso sc = scDAO.selectById(rs.getInt("seccion_curso"));
                e.setSeccion_curso(sc);
                e.setDescripcion(rs.getString("descripcion"));
                e.setPorcentaje(rs.getInt("porcentaje"));
                e.setBonus(rs.getBoolean("bonus"));
            }
            return e;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<Evaluacion> selectAll() {
        ArrayList<Evaluacion> lista = new ArrayList<>();
        try {
            String sql = "select * from evaluacion";
            con = ConnectionDB.newInstanceDB().getCon();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            Evaluacion e;
            while (rs.next()) {
                e = new Evaluacion();
                e.setId(rs.getInt("id"));
                SeccionCurso sc = scDAO.selectById(rs.getInt("seccion_curso"));
                e.setSeccion_curso(sc);
                e.setDescripcion(rs.getString("descripcion"));
                e.setPorcentaje(rs.getInt("porcentaje"));
                e.setBonus(rs.getBoolean("bonus"));
                lista.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    @Override
    public ArrayList<Evaluacion> selectBySc(int id_sc) {
                ArrayList<Evaluacion> lista = new ArrayList<>();
        try {
            String sql = "select * from evaluacion where seccion_curso = ?";
            con = ConnectionDB.newInstanceDB().getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id_sc);
            SeccionCurso sc = scDAO.selectById(id_sc);
            rs = ps.executeQuery();
            Evaluacion e;
            while (rs.next()) {
                e = new Evaluacion();
                e.setId(rs.getInt("id"));
                e.setSeccion_curso(sc);
                e.setDescripcion(rs.getString("descripcion"));
                e.setPorcentaje(rs.getInt("porcentaje"));
                e.setBonus(rs.getBoolean("bonus"));
                lista.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
}
