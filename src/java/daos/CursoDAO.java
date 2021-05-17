/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import conexion.ConnectionDB;
import interfaces.ICurso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Curso;

/**
 *
 * @author Jhonatan
 */
public class CursoDAO implements ICurso{
    
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    @Override
    public boolean insert(Curso c) {
        try {
            String sql = "insert into curso(nombre) values(?)";
            con = ConnectionDB.newInstanceDB().getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1, c.getNombre());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CursoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(Curso c) {
        try {
            String sql = "update curso nombre = ? where id = ?";
            con = ConnectionDB.newInstanceDB().getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1, c.getNombre());
            ps.setInt(2, c.getId());
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
            String sql = "delete from curso where id=?";
            con = ConnectionDB.newInstanceDB().getCon();
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
    public Curso selectById(int id) {        
        try {
            String sql = "select * from curso where id=?";
            con = ConnectionDB.newInstanceDB().getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            Curso c = new Curso();
            while (rs.next()) {
                c.setId(rs.getInt("id"));
                c.setNombre(rs.getString("nombre"));
            }
            return c;
        } catch (SQLException ex) {
            Logger.getLogger(CursoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<Curso> selectAll() {        
        ArrayList<Curso> lista = new ArrayList<>();
        try {
            String sql = "select * from curso";
            con = ConnectionDB.newInstanceDB().getCon();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            Curso c;
            while (rs.next()) {
                c = new Curso();
                c.setId(rs.getInt("id"));
                c.setNombre(rs.getString("nombre"));
                lista.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CursoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
}
