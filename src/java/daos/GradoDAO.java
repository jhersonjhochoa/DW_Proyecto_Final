/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import conexion.ConnectionDB;
import interfaces.IGrado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Grado;
import models.NivelEducativo;

/**
 *
 * @author Jhonatan
 */
public class GradoDAO implements IGrado{
    ConnectionDB cn = new ConnectionDB();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    GradoDAO gDAO = new GradoDAO();
    NivelEducativoDAO neDAO = new NivelEducativoDAO();

    @Override
    public boolean insert(Grado g) {
        try {
            String sql = "insert into grado(nivel, grado) values(?, ?)";
            con = cn.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, g.getNivel().getId());
            ps.setInt(2, g.getGrado());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(Grado g) {
        try {
            String sql = "update grado set nivel=?, grado = ? where id = ?";
            con = cn.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, g.getNivel().getId());
            ps.setInt(2, g.getGrado());
            ps.setInt(3, g.getId());
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
            String sql = "delete from grado where id=?";
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
    public Grado selectById(int id) {
        try {
            String sql = "select * from grado where id=?";
            con = cn.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            Grado g = new Grado();
            while (rs.next()) {
                g.setId(rs.getInt("id"));
                NivelEducativo ne = neDAO.selectById(rs.getInt("nivel"));
                g.setNivel(ne);
                g.setGrado(rs.getInt("grado"));
            }
            return g;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<Grado> selectAll() {
        ArrayList<Grado> lista = new ArrayList<>();
        try {
            String sql = "select * from grado";
            con = cn.getCon();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            Grado g;
            while (rs.next()) {
                g = new Grado();
                g.setId(rs.getInt("id"));
                NivelEducativo ne = neDAO.selectById(rs.getInt("nivel"));
                g.setNivel(ne);
                g.setGrado(rs.getInt("grado"));
                lista.add(g);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
}
