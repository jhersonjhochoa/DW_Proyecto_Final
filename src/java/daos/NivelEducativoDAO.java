/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import conexion.ConnectionDB;
import interfaces.INivelEducativo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.NivelEducativo;

/**
 *
 * @author Jhonatan
 */
public class NivelEducativoDAO implements INivelEducativo{
    ConnectionDB cn = new ConnectionDB();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    NivelEducativoDAO neDAO = new NivelEducativoDAO();

    @Override
    public boolean insert(NivelEducativo ne) {
        try {
            String sql = "insert into nivel_educativo(descripcion) values(?)";
            con = cn.getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1, ne.getDescripcion());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CursoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(NivelEducativo ne) {
        try {
            String sql = "update nivel_educativo descripcion = ? where id = ?";
            con = cn.getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1, ne.getDescripcion());
            ps.setInt(2, ne.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CursoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(int id) {        
        try {
            String sql = "delete from nivel_educativo where id=?";
            con = cn.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CursoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public NivelEducativo selectById(int id) {        
        try {
            String sql = "select * from nivel_educativo where id=?";
            con = cn.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            NivelEducativo ne = new NivelEducativo();
            while (rs.next()) {
                ne.setId(rs.getInt("id"));
                ne.setDescripcion(rs.getString("descripcion"));
            }
            return ne;
        } catch (SQLException ex) {
            Logger.getLogger(CursoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<NivelEducativo> selectAll() {        
        ArrayList<NivelEducativo> lista = new ArrayList<>();
        try {
            String sql = "select * from nivel_educativo";
            con = cn.getCon();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            NivelEducativo c;
            while (rs.next()) {
                c = new NivelEducativo();
                c.setId(rs.getInt("id"));
                c.setDescripcion(rs.getString("descripcion"));
                lista.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CursoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
}
