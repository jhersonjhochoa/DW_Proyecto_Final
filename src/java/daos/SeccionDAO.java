/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import conexion.ConnectionDB;
import interfaces.ISeccion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Grado;
import models.Seccion;

/**
 *
 * @author Jhonatan
 */
public class SeccionDAO implements ISeccion {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    GradoDAO gDAO = new GradoDAO();

    @Override
    public boolean insert(Seccion c) {
        try {
            String sql = "insert into seccion(descripcion, grado, anio) values(?, ?, ?)";
            con = ConnectionDB.newInstanceDB().getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1, c.getDescripcion());
            ps.setInt(2, c.getGrado().getId());
            ps.setInt(3, c.getAnio());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(Seccion c) {
        try {
            String sql = "update seccion set descripcion =?, grado = ?, anio = ? where id = ?";
            con = ConnectionDB.newInstanceDB().getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1, c.getDescripcion());
            ps.setInt(2, c.getGrado().getId());
            ps.setInt(3, c.getAnio());
            ps.setInt(4, c.getId());
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
            String sql = "delete from seccion where id=?";
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
    public Seccion selectById(int id) {
        try {
            String sql = "select * from seccion where id=?";
            con = ConnectionDB.newInstanceDB().getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            Seccion c = new Seccion();
            while (rs.next()) {
                c.setId(rs.getInt("id"));
                c.setDescripcion(rs.getString("descripcion"));
                Grado g = gDAO.selectById(rs.getInt("grado"));
                c.setGrado(g);
                c.setAnio(rs.getInt("anio"));
            }
            return c;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<Seccion> selectAll() {
        ArrayList<Seccion> lista = new ArrayList<>();
        try {
            String sql = "select * from seccion";
            con = ConnectionDB.newInstanceDB().getCon();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            Seccion c;
            while (rs.next()) {
                c = new Seccion();
                c.setId(rs.getInt("id"));
                c.setDescripcion(rs.getString("descripcion"));
                Grado g = gDAO.selectById(rs.getInt("grado"));
                c.setGrado(g);
                c.setAnio(rs.getInt("anio"));
                lista.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    @Override
    public ArrayList<Seccion> selectByYears(int from, int to) {
                ArrayList<Seccion> lista = new ArrayList<>();
        try {
            String sql = "select * from seccion WHERE anio BETWEEN ? AND ?";
            con = ConnectionDB.newInstanceDB().getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, from);
            ps.setInt(2, to);
            rs = ps.executeQuery();
            Seccion c;
            while (rs.next()) {
                c = new Seccion();
                c.setId(rs.getInt("id"));
                c.setDescripcion(rs.getString("descripcion"));
                Grado g = gDAO.selectById(rs.getInt("grado"));
                c.setGrado(g);
                c.setAnio(rs.getInt("anio"));
                lista.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
}
